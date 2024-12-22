package pe.edu.cibertec.proyecto_daw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.proyecto_daw.dto.MedicamentosDto;
import pe.edu.cibertec.proyecto_daw.service.MaintenanceServiceMedicamentos;

import java.util.List;

@Controller
@RequestMapping("/maintenance-medicamentos")
public class MaintenanceControllerMedicamentos {

    @Autowired
    MaintenanceServiceMedicamentos maintenanceServiceMedicamentos;

    @GetMapping("/start")
    public String start(Model model) throws Exception {
        List<MedicamentosDto> medicamentos = maintenanceServiceMedicamentos.getAllMedicamentos();
        model.addAttribute("medicamentos", medicamentos);

        return "maintenance-medicamentos";
    }

    @GetMapping("/detail/{id}")
    public String details(@PathVariable Integer id, Model model) throws Exception {
        MedicamentosDto medicamento = maintenanceServiceMedicamentos.getMedicamentoById(id);
        model.addAttribute("medicamento", medicamento);
        return "maintenance-medicamentos-detail";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model){
        MedicamentosDto medicamentosDto = maintenanceServiceMedicamentos.getMedicamentoById(id);
        model.addAttribute("medicamentosDto", medicamentosDto);
        return "maintenance-medicamentos-update";
    }

    @PostMapping("/update")
    public String updateFilm(MedicamentosDto medicamentosDto) {
        maintenanceServiceMedicamentos.updateMedicamentos(medicamentosDto);
        return "redirect:/maintenance-medicamentos/start";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            maintenanceServiceMedicamentos.deleteMedicamentos(id);
            return ResponseEntity.ok("Medicamento eliminado con éxito");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar medicamento con ID " + id + ": " + e.getMessage());
        }
    }

    @GetMapping("/register")
    public String register(Model model) {
        MedicamentosDto medicamentosDto = new MedicamentosDto(null,null,null,null);
        model.addAttribute("medicamentosDto", medicamentosDto);

        return "maintenance-medicamentos-register";
    }

    @PostMapping("/register")
    public String registerMedicamento(@ModelAttribute MedicamentosDto medicamentosDto) {
        maintenanceServiceMedicamentos.registerMedicamentos(medicamentosDto);
        return "redirect:/maintenance-medicamentos/start";
    }

}
