package pe.edu.cibertec.proyecto_daw.service;

import pe.edu.cibertec.proyecto_daw.dto.MedicamentosDto;

import java.util.List;

public interface MaintenanceServiceMedicamentos {
    List<MedicamentosDto> getAllMedicamentos();
    MedicamentosDto getMedicamentoById(int id);
    MedicamentosDto getMedicamentoUpdateById(int id);
    void updateMedicamentos(MedicamentosDto medicamento);
    void deleteMedicamentos(int id);
    void registerMedicamentos(MedicamentosDto medicamento);
}
