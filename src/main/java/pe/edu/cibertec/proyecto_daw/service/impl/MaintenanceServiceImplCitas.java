package pe.edu.cibertec.proyecto_daw.service.impl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.proyecto_daw.dto.CitasDto;
import pe.edu.cibertec.proyecto_daw.entity.Citas;
import pe.edu.cibertec.proyecto_daw.entity.Doctores;
import pe.edu.cibertec.proyecto_daw.entity.Pacientes;
import pe.edu.cibertec.proyecto_daw.repository.CitasRepository;
import pe.edu.cibertec.proyecto_daw.repository.DoctoresRepository;
import pe.edu.cibertec.proyecto_daw.repository.PacientesRepository;
import pe.edu.cibertec.proyecto_daw.service.MaintenanceServiceCitas;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceServiceImplCitas implements MaintenanceServiceCitas {

    @Autowired
    CitasRepository citasRepository;

    @Autowired
    DoctoresRepository doctoresRepository;

    @Autowired
    PacientesRepository pacientesRepository;

    @Override
    public List<CitasDto> getAllCitas() {
        List<CitasDto> citas = new ArrayList<CitasDto>();
        Iterable<Citas> iterable = citasRepository.findAll();
        iterable.forEach(cita ->{
            CitasDto citaDto = new CitasDto(cita.getIdCita(),
                                            cita.getPaciente().getIdPaciente(),
                                            cita.getPaciente().getNombres(),
                                            cita.getDoctor().getIdDoctor(),
                                            cita.getDoctor().getNombres(),
                                            cita.getFecha(),
                                            cita.getMotivo(),
                                            cita.getEstado());
            citas.add(citaDto);
        });
        return citas;
    }

    @Override
    public CitasDto getCitaById(int id) {
        Optional<Citas> optional = citasRepository.findById(id);
        return optional.map(cita -> new CitasDto(cita.getIdCita(),
                                                        cita.getPaciente().getIdPaciente(),
                                                        cita.getPaciente().getNombres(),
                                                        cita.getDoctor().getIdDoctor(),
                                                        cita.getDoctor().getNombres(),
                                                        cita.getFecha(),
                                                        cita.getMotivo(),
                                                        cita.getEstado())
        ).orElse(null);
    }

    @Override
    public CitasDto getCitaUpdateById(int id) {
        Optional<Citas> optional = citasRepository.findById(id);
        return optional.map(cita -> new CitasDto(cita.getIdCita(),
                                                        cita.getPaciente().getIdPaciente(),
                                                        cita.getPaciente().getNombres(),
                                                        cita.getDoctor().getIdDoctor(),
                                                        cita.getDoctor().getNombres(),
                                                        cita.getFecha(),
                                                        cita.getMotivo(),
                                                        cita.getEstado())
        ).orElse(null);
    }

    @Override
    public void updateCita(CitasDto cita) {
        Optional<Citas> optional = citasRepository.findById(cita.idCita());
        Doctores doctor = doctoresRepository.findById(cita.idDoctor()).orElseThrow(() -> new RuntimeException("Doctor no encontrado"));
        Pacientes paciente = pacientesRepository.findById(cita.idPaciente()).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        if(optional.isPresent()) {
            Citas citas = optional.get();
            citas.setPaciente(paciente);
            citas.setDoctor(doctor);
            citas.setFecha(cita.fecha());
            citas.setMotivo(cita.motivo());
            citas.setEstado(cita.estado());
            citasRepository.save(citas);
        }
    }

    @Transactional
    @Override
    public void deleteCita(int id) {
        Optional<Citas> optional = citasRepository.findById(id);

        if (optional.isPresent()){
            citasRepository.delete(optional.get());
        }else {
            throw new RuntimeException("Cita no encontrada");
        }
    }

    @Override
    public void registerCita(CitasDto cita) {

        Doctores doctor = doctoresRepository.findById(cita.idDoctor()).orElseThrow(() -> new RuntimeException("Doctor no encontrado"));
        Pacientes paciente = pacientesRepository.findById(cita.idPaciente()).orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        Citas citas = new Citas();
        citas.setPaciente(paciente);
        citas.setDoctor(doctor);
        citas.setFecha(cita.fecha());
        citas.setMotivo(cita.motivo());
        citas.setEstado(cita.estado());

        citasRepository.save(citas);
    }

    @Override
    public List<Doctores> getAllDoctores() {
        Iterable<Doctores> iterable = doctoresRepository.findAll();
        List<Doctores> doctores = new ArrayList<>();
        iterable.forEach(doctores::add);
        return doctores;
    }

    @Override
    public List<Pacientes> getAllPacientes() {
        Iterable<Pacientes> iterable = pacientesRepository.findAll();
        List<Pacientes> pacientes = new ArrayList<>();
        iterable.forEach(pacientes::add);
        return pacientes;
    }


}
