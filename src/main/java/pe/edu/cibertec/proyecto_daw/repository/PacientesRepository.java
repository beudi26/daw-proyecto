package pe.edu.cibertec.proyecto_daw.repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.cibertec.proyecto_daw.entity.Pacientes;

public interface PacientesRepository extends CrudRepository<Pacientes, Integer> {
}
