package pe.edu.cibertec.proyecto_daw.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import pe.edu.cibertec.proyecto_daw.dto.DoctoresDto;
import pe.edu.cibertec.proyecto_daw.entity.Doctores;
import pe.edu.cibertec.proyecto_daw.repository.DoctoresRepository;
import pe.edu.cibertec.proyecto_daw.service.MaintenanceServiceDoctores;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class MaintenanceServiceImplDoctores implements MaintenanceServiceDoctores {

    @Autowired
    DoctoresRepository doctoresRepository;

    @Override
    public List<DoctoresDto> getAllDoctores() {
        List<DoctoresDto> doctores = new ArrayList<DoctoresDto>();
        Iterable<Doctores> iterable = doctoresRepository.findAll();
        iterable.forEach(doctor -> {
            DoctoresDto doctoresDto = new DoctoresDto(doctor.getIdDoctor(),
                                                        doctor.getNombres(),
                                                        doctor.getApellidos(),
                                                        doctor.getEspecialidad(),
                                                        doctor.getTelefono(),
                                                        doctor.getEmail());
            doctores.add(doctoresDto);
        });
        return doctores;
    }

    @Override
    public DoctoresDto getDoctoresById(int id) {
        Optional<Doctores> optional = doctoresRepository.findById(id);
        return optional.map(
                doctor -> new DoctoresDto(doctor.getIdDoctor(),
                        doctor.getNombres(),
                        doctor.getApellidos(),
                        doctor.getEspecialidad(),
                        doctor.getTelefono(),
                        doctor.getEmail())
        ).orElse(null);
    }

    @Override
    public DoctoresDto getDoctoresUpdateById(int id) {
        Optional<Doctores> optional = doctoresRepository.findById(id);
        return optional.map(
                doctor -> new DoctoresDto(doctor.getIdDoctor(),
                        doctor.getNombres(),
                        doctor.getApellidos(),
                        doctor.getEspecialidad(),
                        doctor.getTelefono(),
                        doctor.getEmail())
        ).orElse(null);
    }

    @Override
    public void updateDoctores(DoctoresDto doctor) {
        Optional<Doctores> optional = doctoresRepository.findById(doctor.idDoctor());
        if (optional.isPresent()) {
            Doctores doctores = optional.get();
            doctores.setNombres(doctor.nombres());
            doctores.setApellidos(doctor.apellidos());
            doctores.setEspecialidad(doctor.especialidad());
            doctores.setTelefono(doctor.telefono());
            doctores.setEmail(doctor.email());

            doctoresRepository.save(doctores);
        }
    }

    @Override
    @Transactional
    public void deleteDoctores(int id) {
        Optional<Doctores> optional = doctoresRepository.findById(id);

        if (optional.isPresent()) {
            doctoresRepository.delete(optional.get());
        }else {
            throw new RuntimeException("Doctor no encontrado.");
        }
    }

    @Override
    public void registerDoctores(DoctoresDto doctor) {
        Doctores doctores = new Doctores();
        doctores.setNombres(doctor.nombres());
        doctores.setApellidos(doctor.apellidos());
        doctores.setEspecialidad(doctor.especialidad());
        doctores.setTelefono(doctor.telefono());
        doctores.setEmail(doctor.email());

        doctoresRepository.save(doctores);
    }

    @Override
    public String getEspecialidad(int id) {
        return doctoresRepository.findById(id).map(doctor -> doctor.getEspecialidad()).orElse(null);
    }


}
