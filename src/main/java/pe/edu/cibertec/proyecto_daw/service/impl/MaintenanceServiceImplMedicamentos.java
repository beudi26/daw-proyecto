package pe.edu.cibertec.proyecto_daw.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.proyecto_daw.dto.MedicamentosDto;
import pe.edu.cibertec.proyecto_daw.entity.Medicamentos;
import pe.edu.cibertec.proyecto_daw.repository.MedicamentosRepository;
import pe.edu.cibertec.proyecto_daw.service.MaintenanceServiceMedicamentos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceServiceImplMedicamentos implements MaintenanceServiceMedicamentos {

    @Autowired
    MedicamentosRepository medicamentosRepository;

    @Override
    public List<MedicamentosDto> getAllMedicamentos() {
        List<MedicamentosDto> medicamentos = new ArrayList<MedicamentosDto>();
        Iterable<Medicamentos> iterable = medicamentosRepository.findAll();
        iterable.forEach(medicamento -> {
            MedicamentosDto medicamentosDto = new MedicamentosDto(medicamento.getIdMedicamento(),
                                                                    medicamento.getNombre(),
                                                                    medicamento.getDescripcion(),
                                                                    medicamento.getStock());
            medicamentos.add(medicamentosDto);
        });
        return medicamentos;
    }

    @Override
    public MedicamentosDto getMedicamentoById(int id) {
        Optional<Medicamentos> optional = medicamentosRepository.findById(id);
        return optional.map(
                medicamento -> new MedicamentosDto(medicamento.getIdMedicamento(),
                                                                medicamento.getNombre(),
                                                                medicamento.getDescripcion(),
                                                                medicamento.getStock())
        ).orElse(null);
    }

    @Override
    public MedicamentosDto getMedicamentoUpdateById(int id) {
        Optional<Medicamentos> optional = medicamentosRepository.findById(id);
        return optional.map(
                medicamento -> new MedicamentosDto(medicamento.getIdMedicamento(),
                        medicamento.getNombre(),
                        medicamento.getDescripcion(),
                        medicamento.getStock())
        ).orElse(null);
    }

    @Override
    public void updateMedicamentos(MedicamentosDto medicamento) {
        Optional<Medicamentos> optional = medicamentosRepository.findById(medicamento.idMedicamento());
        if (optional.isPresent()) {
            Medicamentos medicamentos = optional.get();
            medicamentos.setNombre(medicamento.nombre());
            medicamentos.setDescripcion(medicamento.descripcion());
            medicamentos.setStock(medicamento.stock());
            medicamentosRepository.save(medicamentos);
        }
    }

    @Override
    @Transactional
    public void deleteMedicamentos(int id) {
        Optional<Medicamentos> optional = medicamentosRepository.findById(id);
        if (optional.isPresent()) {
            medicamentosRepository.delete(optional.get());
        }else {
            throw new RuntimeException("Medicamento no encontrado.");
        }
    }

    @Override
    public void registerMedicamentos(MedicamentosDto medicamento) {
        Medicamentos medicamentos = new Medicamentos();
        medicamentos.setNombre(medicamento.nombre());
        medicamentos.setDescripcion(medicamento.descripcion());
        medicamentos.setStock(medicamento.stock());
        medicamentosRepository.save(medicamentos);
    }
}
