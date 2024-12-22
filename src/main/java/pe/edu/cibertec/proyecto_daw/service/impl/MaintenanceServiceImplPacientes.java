package pe.edu.cibertec.proyecto_daw.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import pe.edu.cibertec.proyecto_daw.dto.PacientesDto;
import pe.edu.cibertec.proyecto_daw.entity.Pacientes;
import pe.edu.cibertec.proyecto_daw.repository.PacientesRepository;
import pe.edu.cibertec.proyecto_daw.service.MaintenanceServicePacientes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class MaintenanceServiceImplPacientes implements MaintenanceServicePacientes {

    @Autowired
    PacientesRepository PacientesRepository;
    @Autowired
    private PacientesRepository pacientesRepository;

    @Override
    public List<PacientesDto> getAllPacientes(){
        List<PacientesDto> pacientes = new ArrayList<PacientesDto>();
        Iterable<Pacientes> iterable = PacientesRepository.findAll();
        iterable.forEach(paciente ->{
            PacientesDto pacienteDto = new PacientesDto(paciente.getIdPaciente(),
                                                        paciente.getNombres(),
                                                        paciente.getApellidos(),
                                                        paciente.getDni(),
                                                        paciente.getFechaNacimiento(),
                                                        paciente.getTelefono(),
                                                        paciente.getEmail(),
                                                        paciente.getDireccion());
            pacientes.add(pacienteDto);
        });
        return pacientes;
    }

    @Override
    public PacientesDto getPacienteById(int id){
        Optional<Pacientes> optional = PacientesRepository.findById(id);
        return optional.map(
                paciente -> new PacientesDto(paciente.getIdPaciente(),
                                            paciente.getNombres(),
                                            paciente.getApellidos(),
                                            paciente.getDni(),
                                            paciente.getFechaNacimiento(),
                                            paciente.getTelefono(),
                                            paciente.getEmail(),
                                            paciente.getDireccion())
        ).orElse(null);
    }

    @Override
    public PacientesDto getPacienteUpdateById(int id){
        Optional<Pacientes> optional = PacientesRepository.findById(id);
        return optional.map(
                paciente -> new PacientesDto(paciente.getIdPaciente(),
                        paciente.getNombres(),
                        paciente.getApellidos(),
                        paciente.getDni(),
                        paciente.getFechaNacimiento(),
                        paciente.getTelefono(),
                        paciente.getEmail(),
                        paciente.getDireccion())
        ).orElse(null);
    }

    @Override
    public void updatePaciente(PacientesDto paciente){
        Optional<Pacientes> optional = PacientesRepository.findById(paciente.idPaciente());

        if(optional.isPresent()){
            Pacientes pacientes = optional.get();
            pacientes.setNombres(paciente.nombres());
            pacientes.setApellidos(paciente.apellidos());
            pacientes.setDni(paciente.dni());
            pacientes.setFechaNacimiento(paciente.fechaNacimiento());
            pacientes.setTelefono(paciente.telefono());
            pacientes.setEmail(paciente.email());
            pacientes.setDireccion(paciente.direccion());

            PacientesRepository.save(pacientes);
        }

    }

    @Override
    @Transactional
    public void deletePaciente(int id){
        Optional<Pacientes> optional = PacientesRepository.findById(id);

        // Verificar si el paciente existe
        if (optional.isPresent()) {
            // Eliminar el paciente
            PacientesRepository.delete(optional.get());
        } else {
            // Si el paciente no existe, lanzar una excepción o manejar el caso
            throw new RuntimeException("Paciente no encontrado.");
        }
    }

    @Override
    public void insertPaciente(PacientesDto paciente){
        Pacientes pacientes = new Pacientes();
        pacientes.setNombres(paciente.nombres());
        pacientes.setApellidos(paciente.apellidos());
        pacientes.setDni(paciente.dni());
        pacientes.setFechaNacimiento(paciente.fechaNacimiento());
        pacientes.setTelefono(paciente.telefono());
        pacientes.setEmail(paciente.email());
        pacientes.setDireccion(paciente.direccion());

        pacientesRepository.save(pacientes);
    }
}
