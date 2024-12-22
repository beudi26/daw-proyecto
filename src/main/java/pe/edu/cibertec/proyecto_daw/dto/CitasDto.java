package pe.edu.cibertec.proyecto_daw.dto;

import pe.edu.cibertec.proyecto_daw.entity.Citas;

import java.util.Date;

public record CitasDto(Integer idCita,
                       Integer idPaciente,
                       String paciente,
                       Integer idDoctor,
                       String doctor,
                       Date fecha,
                       String motivo,
                       String estado) {
}
