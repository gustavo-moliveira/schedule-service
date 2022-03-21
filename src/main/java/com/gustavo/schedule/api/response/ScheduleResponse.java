package com.gustavo.schedule.api.response;


import com.gustavo.schedule.domain.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleResponse {

    private Long id;
    private String description;
    private LocalDateTime dateTime;
    private PatientResponse patient;

}
