package pe.edu.cibertec.proyecto_daw.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "historialmedico")
public class HistorialMedico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHistorial;

    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Pacientes paciente;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    private String descripcion;
    private String tratamiento;
}
