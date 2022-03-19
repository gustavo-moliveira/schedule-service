package com.gustavo.schedule.domain.service;

import com.gustavo.schedule.domain.entity.Patient;
import com.gustavo.schedule.domain.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PatientService {

    @Autowired
    private final PatientRepository repository;

    public Patient save(Patient patient) {
        //TODO: validar se o CPF já existe.

        return repository.save(patient);
    }

    public List<Patient> listAll() {
        return repository.findAll();

    }

    public Optional<Patient> searchForId(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
