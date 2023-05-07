package ru.yandex.yandexlavka.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.yandex.yandexlavka.controller.constraints.Interval;
import ru.yandex.yandexlavka.controller.constraints.IsReal;
import ru.yandex.yandexlavka.controller.json.TimeIntervalDeserializer;
import ru.yandex.yandexlavka.entity.TimeInterval;

import java.util.List;

@Data
public class CreateOrderDto {

    @JsonProperty(required = true)
    @NotNull
    @IsReal
    private String weight;

    @JsonProperty(value = "regions", required = true)
    @NotNull
    @Min(0)
    private Integer region;

    @JsonProperty(value = "delivery_hours",required = true)
    @JsonDeserialize(contentUsing = TimeIntervalDeserializer.class)
    @NotNull
    private List<@Interval TimeInterval> deliveryHours;

    @JsonProperty(required = true)
    @NotNull
    @Min(0)
    private Integer cost;

}
