package com.gustavo.schedule.domain.service;

import com.gustavo.schedule.domain.entity.Patient;
import com.gustavo.schedule.domain.entity.Schedule;
import com.gustavo.schedule.domain.repository.ScheduleRepository;
import com.gustavo.schedule.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository repository;
    private final PatientService patientService;

    public List<Schedule> listAll() {
        return repository.findAll();
    }

    public Optional<Schedule> searchById(Long id) {
        return repository.findById(id);
    }

    public Schedule save(Schedule schedule) {

        Optional<Patient> optPatient = patientService.searchForId(schedule.getPatient().getId());

        if (optPatient.isEmpty()) {
            throw new BusinessException("Patient not found!");
        }

        Optional<Schedule> optDateTime = repository.findByDateTime(schedule.getDateTime());

        if (optDateTime.isPresent()) {
            throw new BusinessException("There is a scheduling for this time!");
        }

        schedule.setPatient(optPatient.get());
        schedule.setCreationDate(LocalDateTime.now());

        return repository.save(schedule);
    }
}
