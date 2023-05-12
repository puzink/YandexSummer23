package ru.yandex.yandexlavka.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.yandex.yandexlavka.dto.CourierType;
import ru.yandex.yandexlavka.entity.TimeInterval;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class CourierMetaInfoResponse {

    @JsonProperty("courier_id")
    private Long courierId;

    @JsonProperty("courier_type")
    private CourierType type;
    private List<Integer> regions;
    @JsonProperty("working_hours")
    private List<TimeInterval> workingHours;
    private Integer rating;
    private Long earnings;

}
