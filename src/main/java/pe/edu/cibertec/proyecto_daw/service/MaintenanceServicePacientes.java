package pe.edu.cibertec.proyecto_daw.service;

import pe.edu.cibertec.proyecto_daw.dto.PacientesDto;

import java.util.List;

public interface MaintenanceServicePacientes {

    List<PacientesDto> getAllPacientes();
    PacientesDto getPacienteById(int id);
    PacientesDto getPacienteUpdateById(int id);
    void updatePaciente(PacientesDto paciente);
    void deletePaciente(int id);
    void insertPaciente(PacientesDto paciente);

}
