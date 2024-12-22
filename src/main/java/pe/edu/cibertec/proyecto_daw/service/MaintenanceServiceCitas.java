package pe.edu.cibertec.proyecto_daw.service;

import pe.edu.cibertec.proyecto_daw.dto.CitasDto;
import pe.edu.cibertec.proyecto_daw.entity.Doctores;
import pe.edu.cibertec.proyecto_daw.entity.Pacientes;

import java.util.List;

public interface MaintenanceServiceCitas {
    List<CitasDto> getAllCitas();
    CitasDto getCitaById(int id);
    CitasDto getCitaUpdateById(int id);
    void updateCita(CitasDto cita);
    void deleteCita(int id);
    void registerCita(CitasDto cita);
    List<Doctores> getAllDoctores();
    List<Pacientes> getAllPacientes();
}
