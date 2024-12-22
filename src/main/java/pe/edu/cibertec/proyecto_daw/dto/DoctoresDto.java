package pe.edu.cibertec.proyecto_daw.dto;

public record DoctoresDto(Integer idDoctor,
                          String nombres,
                          String apellidos,
                          String especialidad,
                          String telefono,
                          String email) {
}
