package com.gustavo.schedule.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequest {

    @NotBlank(message = "patient name is required!")
    private String name;

    @NotBlank(message = "patient lastname is required!")
    private String lastname;

    private String email;

    @NotBlank(message = "patient cpf is required!")
    private String cpf;
}