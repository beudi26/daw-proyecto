package pe.edu.cibertec.proyecto_daw.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Recetas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReceta;

    @ManyToOne
    @JoinColumn(name = "id_cita", nullable = false)
    private Citas cita;

    @ManyToOne
    @JoinColumn(name = "id_medicamento", nullable = false)
    private Medicamentos medicamento;

    private Integer cantidad;
    private String instrucciones;
}
