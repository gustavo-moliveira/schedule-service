package com.gustavo.schedule.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponse {

    private Long id;
    private String name;
    private String lastname;
    private String email;
    private String cpf;
}
