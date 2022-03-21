package com.gustavo.schedule.api.mapper;

import com.gustavo.schedule.api.request.ScheduleRequest;
import com.gustavo.schedule.api.response.ScheduleResponse;
import com.gustavo.schedule.domain.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScheduleMapper {

    private final ModelMapper mapper;

    public Schedule toSchedule(ScheduleRequest request) {
        return mapper.map(request, Schedule.class);
    }

    public ScheduleResponse toScheduleResponse(Schedule schedule) {

        return mapper.map(schedule, ScheduleResponse.class);

    }

    public List<ScheduleResponse> toScheduleResponseList(List<Schedule> schedules) {
        return schedules
                .stream()
                .map(this::toScheduleResponse)
                .collect(Collectors.toList());
    }
}
