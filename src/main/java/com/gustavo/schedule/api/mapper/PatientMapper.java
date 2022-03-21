package com.gustavo.schedule.api.mapper;

import com.gustavo.schedule.api.request.PatientRequest;
import com.gustavo.schedule.api.response.PatientResponse;
import com.gustavo.schedule.domain.entity.Patient;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PatientMapper {

    private final ModelMapper mapper;

    public Patient toPatient(PatientRequest request) {
        return mapper.map(request, Patient.class);
    }


    public PatientResponse toPatientResponse(Patient patient) {

        return mapper.map(patient, PatientResponse.class);

    }

    public List<PatientResponse> toPatientResponseList(List<Patient> patients) {
        return patients
                .stream()
                    .map(this::toPatientResponse)
                        .collect(Collectors.toList());
    }
}
