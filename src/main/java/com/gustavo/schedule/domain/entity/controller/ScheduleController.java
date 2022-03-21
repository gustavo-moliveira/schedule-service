package com.gustavo.schedule.domain.entity.controller;

import com.gustavo.schedule.api.mapper.ScheduleMapper;
import com.gustavo.schedule.api.request.ScheduleRequest;
import com.gustavo.schedule.api.response.ScheduleResponse;
import com.gustavo.schedule.domain.entity.Schedule;
import com.gustavo.schedule.domain.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService service;
    private final ScheduleMapper mapper;

    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> listAll() {
        List<Schedule> schedules = service.listAll();
        List<ScheduleResponse> scheduleResponses = mapper.toScheduleResponseList(schedules);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(scheduleResponses);
    }

    @GetMapping("{id}")
    public ResponseEntity<ScheduleResponse> searchById(@PathVariable Long id) {
        Optional<Schedule> optSchedule = service.searchById(id);

        if (optSchedule.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ScheduleResponse scheduleResponse = mapper.toScheduleResponse(optSchedule.get());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(scheduleResponse);
    }

    @PostMapping
    public ResponseEntity<ScheduleResponse> save(@Valid @RequestBody ScheduleRequest request) {
        Schedule schedule = mapper.toSchedule(request);
        Schedule savedSchedule = service.save(schedule);
        ScheduleResponse scheduleResponse = mapper.toScheduleResponse(savedSchedule);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(scheduleResponse);
    }
}
