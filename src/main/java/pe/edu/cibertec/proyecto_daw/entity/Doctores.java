package pe.edu.cibertec.proyecto_daw.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Doctores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDoctor;

    private String nombres;
    private String apellidos;
    private String especialidad;
    private String telefono;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Citas> citas;
}
