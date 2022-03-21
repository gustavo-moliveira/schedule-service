package com.gustavo.schedule.domain.entity.controller;

import com.gustavo.schedule.api.mapper.PatientMapper;
import com.gustavo.schedule.api.request.PatientRequest;
import com.gustavo.schedule.api.response.PatientResponse;
import com.gustavo.schedule.domain.entity.Patient;
import com.gustavo.schedule.domain.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService service;
    private final PatientMapper mapper;

    @PostMapping
    public ResponseEntity<PatientResponse> save(@Valid @RequestBody PatientRequest request) {

        Patient patient = mapper.toPatient(request);
        Patient savedPatient = service.save(patient);
        PatientResponse patientResponse = mapper.toPatientResponse(savedPatient);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(patientResponse);
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> listAll() {
        List<Patient> patients = service.listAll();
        List<PatientResponse> patientResponses = mapper.toPatientResponseList(patients);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(patientResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> searchForId(@PathVariable Long id) {
        Optional<Patient> optPatient = service.searchForId(id);

        if (optPatient.isEmpty()) {
            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mapper.toPatientResponse(optPatient.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponse> update(@PathVariable Long id, @RequestBody PatientRequest request) {
        Patient patient = mapper.toPatient(request);
        Patient savedPatient = service.update(id, patient);
        PatientResponse patientResponse = mapper.toPatientResponse(savedPatient);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(patientResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
