package pe.edu.cibertec.proyecto_daw.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pacientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPaciente;

    private String nombres;

    private String apellidos;

    @Column(unique = true, nullable = false)
    private String dni;

    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    private String telefono;

    private String email;

    private String direccion;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Citas> citas;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistorialMedico> historialMedico;
}
