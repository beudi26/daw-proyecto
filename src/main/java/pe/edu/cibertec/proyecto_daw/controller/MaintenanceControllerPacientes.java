package pe.edu.cibertec.proyecto_daw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.proyecto_daw.dto.PacientesDto;
import pe.edu.cibertec.proyecto_daw.service.MaintenanceServicePacientes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/maintenance-pacientes")
public class MaintenanceControllerPacientes {

    @Autowired
    MaintenanceServicePacientes maintenanceServicePacientes;

    @GetMapping("/start")
    public String start(Model model) throws Exception {
        List<PacientesDto> pacientes = maintenanceServicePacientes.getAllPacientes();
        model.addAttribute("pacientes", pacientes);

        return "maintenance-pacientes";
    }

    @GetMapping("/detail/{id}")
    public String details(@PathVariable Integer id, Model model) throws Exception {
        PacientesDto paciente = maintenanceServicePacientes.getPacienteById(id);
        model.addAttribute("paciente", paciente);
        return "maintenance-pacientes-detail";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model){
        PacientesDto pacientesDto = maintenanceServicePacientes.getPacienteUpdateById(id);
        model.addAttribute("pacientesDto", pacientesDto);
        return "maintenance-pacientes-update";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    @PostMapping("/update")
    public String updateFilm(PacientesDto pacientesDto) {
        maintenanceServicePacientes.updatePaciente(pacientesDto);
        return "redirect:/maintenance-pacientes/start";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            maintenanceServicePacientes.deletePaciente(id);
            return ResponseEntity.ok("Paciente eliminado con éxito");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar paciente con ID " + id + ": " + e.getMessage());
        }
    }

    @GetMapping("/register")
    public String register(Model model) {
        PacientesDto pacientesDto = new PacientesDto(null,null,null,null,null,null,null,null);
        model.addAttribute("pacientesDto", pacientesDto);

        return "maintenance-pacientes-register";
    }

    @PostMapping("/register")
    public String registerPaciente(@ModelAttribute PacientesDto pacientesDto) {
        maintenanceServicePacientes.insertPaciente(pacientesDto);
        return "redirect:/maintenance-pacientes/start";
    }

}
