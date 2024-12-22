package pe.edu.cibertec.proyecto_daw.dto;

import java.util.Date;

public record PacientesDto(Integer idPaciente,
                           String nombres,
                           String apellidos,
                           String dni,
                           Date fechaNacimiento,
                           String telefono,
                           String email,
                           String direccion) {
}
