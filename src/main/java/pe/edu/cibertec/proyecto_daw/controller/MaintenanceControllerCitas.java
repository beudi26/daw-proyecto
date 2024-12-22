package pe.edu.cibertec.proyecto_daw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.proyecto_daw.dto.CitasDto;
import pe.edu.cibertec.proyecto_daw.dto.PacientesDto;
import pe.edu.cibertec.proyecto_daw.entity.Doctores;
import pe.edu.cibertec.proyecto_daw.entity.Pacientes;
import pe.edu.cibertec.proyecto_daw.service.MaintenanceServiceCitas;
import pe.edu.cibertec.proyecto_daw.service.MaintenanceServiceDoctores;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/maintenance-citas")
public class MaintenanceControllerCitas {

    @Autowired
    MaintenanceServiceCitas maintenanceServiceCitas;

    @Autowired
    MaintenanceServiceDoctores maintenanceServiceDoctores;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    @GetMapping("/start")
    public String start(Model model) throws Exception {
        List<CitasDto> citas = maintenanceServiceCitas.getAllCitas();
        model.addAttribute("citas", citas);

        return "maintenance-citas";
    }

    @GetMapping("/detail/{id}")
    public String details(@PathVariable Integer id, Model model) throws Exception {
        CitasDto cita = maintenanceServiceCitas.getCitaById(id);
        model.addAttribute("cita", cita);
        return "maintenance-citas-detail";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model){
        CitasDto citasDto = maintenanceServiceCitas.getCitaUpdateById(id);
        model.addAttribute("citasDto", citasDto);

        // Agregar la lista de doctores al modelo
        List<Doctores> doctores = maintenanceServiceCitas.getAllDoctores();
        model.addAttribute("doctores", doctores);

        // Agregar la lista de pacientes al modelo
        List<Pacientes> pacientes = maintenanceServiceCitas.getAllPacientes();
        model.addAttribute("pacientes", pacientes);

        return "maintenance-citas-update";
    }

    @PostMapping("/update")
    public String updateFilm(CitasDto citasDto) {
        maintenanceServiceCitas.updateCita(citasDto);
        return "redirect:/maintenance-citas/start";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            maintenanceServiceCitas.deleteCita(id);
            return ResponseEntity.ok("Cita eliminada con éxito");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar cita con ID " + id + ": " + e.getMessage());
        }
    }

    @GetMapping("/register")
    public String register(Model model) {
        CitasDto citasDto = new CitasDto(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        model.addAttribute("citasDto", citasDto);

        List<Doctores> doctores = maintenanceServiceCitas.getAllDoctores();
        model.addAttribute("doctores", doctores);

        List<Pacientes> pacientes = maintenanceServiceCitas.getAllPacientes();
        model.addAttribute("pacientes", pacientes);

        return "maintenance-citas-register";
    }

    @PostMapping("/register")
    public String registerCita(@ModelAttribute CitasDto citasDto) {
        maintenanceServiceCitas.registerCita(citasDto);
        return "redirect:/maintenance-citas/start";
    }

    @GetMapping("/especialidad/{id}")
    public ResponseEntity<String> getEspecialidad(@PathVariable Integer id) {
        try {
            String especialidad = maintenanceServiceDoctores.getEspecialidad(id);
            if (especialidad != null) {
                return ResponseEntity.ok(especialidad);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Especialidad no encontrada");
            }
        } catch (Exception e) {
            // Manejo de errores si ocurre alguna excepción
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno al obtener especialidad: " + e.getMessage());
        }
    }

}
