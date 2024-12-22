package pe.edu.cibertec.proyecto_daw.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Medicamentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMedicamento;

    private String nombre;
    private String descripcion;
    private Integer stock;
}
