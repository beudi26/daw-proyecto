package pe.edu.cibertec.proyecto_daw.service;

import pe.edu.cibertec.proyecto_daw.dto.DoctoresDto;

import java.util.List;

public interface MaintenanceServiceDoctores {

    List<DoctoresDto> getAllDoctores();
    DoctoresDto getDoctoresById(int id);
    DoctoresDto getDoctoresUpdateById(int id);
    void updateDoctores(DoctoresDto doctor);
    void deleteDoctores(int id);
    void registerDoctores(DoctoresDto doctor);
    String getEspecialidad(int id);

}
