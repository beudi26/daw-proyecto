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
public class Citas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCita;

    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Pacientes paciente;

    @ManyToOne
    @JoinColumn(name = "id_doctor", nullable = false)
    private Doctores doctor;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    private String motivo;

    private String estado;

    @OneToMany(mappedBy = "cita", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recetas> recetas;

}
