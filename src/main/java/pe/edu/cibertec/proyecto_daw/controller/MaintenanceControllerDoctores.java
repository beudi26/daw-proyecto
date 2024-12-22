package pe.edu.cibertec.proyecto_daw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.proyecto_daw.dto.DoctoresDto;
import pe.edu.cibertec.proyecto_daw.dto.PacientesDto;
import pe.edu.cibertec.proyecto_daw.service.MaintenanceServiceDoctores;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/maintenance-doctores")
public class MaintenanceControllerDoctores {

    @Autowired
    MaintenanceServiceDoctores maintenanceServiceDoctores;

    @GetMapping("/start")
    public String start(Model model) throws Exception {
        List<DoctoresDto> doctores = maintenanceServiceDoctores.getAllDoctores();
        model.addAttribute("doctores", doctores);

        return "maintenance-doctores";
    }

    @GetMapping("/detail/{id}")
    public String details(@PathVariable Integer id, Model model) throws Exception {
        DoctoresDto doctor = maintenanceServiceDoctores.getDoctoresById(id);
        model.addAttribute("doctor", doctor);
        return "maintenance-doctores-detail";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model){
        DoctoresDto doctoresDto = maintenanceServiceDoctores.getDoctoresUpdateById(id);
        model.addAttribute("doctoresDto", doctoresDto);
        return "maintenance-doctores-update";
    }

    @PostMapping("/update")
    public String updateFilm(DoctoresDto doctoresDto) {
        maintenanceServiceDoctores.updateDoctores(doctoresDto);
        return "redirect:/maintenance-doctores/start";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            maintenanceServiceDoctores.deleteDoctores(id);
            return ResponseEntity.ok("Doctor eliminado con éxito");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar doctor con ID " + id + ": " + e.getMessage());
        }
    }

    @GetMapping("/register")
    public String register(Model model) {
        DoctoresDto doctoresDto = new DoctoresDto(null,null,null,null,null,null);
        model.addAttribute("doctoresDto", doctoresDto);

        return "maintenance-doctores-register";
    }

    @PostMapping("/register")
    public String registerDoctor(@ModelAttribute DoctoresDto doctoresDto) {
        maintenanceServiceDoctores.registerDoctores(doctoresDto);
        return "redirect:/maintenance-doctores/start";
    }

}
