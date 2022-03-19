package com.gustavo.schedule.api.controller;

import com.gustavo.schedule.domain.entity.Patient;
import com.gustavo.schedule.domain.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService service;

    @PostMapping
    public ResponseEntity<Patient> save(@RequestBody Patient patient) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(patient));
    }

    @GetMapping
    public ResponseEntity<List<Patient>> listAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> searchForId(@PathVariable Long id) {
        Optional<Patient> optPatient = service.searchForId(id);

        if (optPatient.isEmpty()) {
            return  ResponseEntity.notFound().build();
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(optPatient.get());
    }

    @PutMapping
    public ResponseEntity<Patient> update(@RequestBody Patient patient) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.save(patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
