package ru.yandex.yandexlavka.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.yandex.yandexlavka.controller.constraints.Interval;
import ru.yandex.yandexlavka.controller.constraints.IsReal;
import ru.yandex.yandexlavka.controller.json.TimeIntervalDeserializer;
import ru.yandex.yandexlavka.controller.json.TimeIntervalSerializer;
import ru.yandex.yandexlavka.controller.request.CreateOrderDto;
import ru.yandex.yandexlavka.controller.json.StringNumberSerializer;
import ru.yandex.yandexlavka.entity.TimeInterval;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {
    @JsonProperty("order_id")
    @Min(1)
    private Long id;
    @JsonProperty("weight")
    @JsonSerialize(using = StringNumberSerializer.class)
    @IsReal
    private String weight;
    @JsonProperty("regions")
    private Integer region;

    @JsonProperty("delivery_hours")
    @JsonDeserialize(contentUsing = TimeIntervalDeserializer.class)
    @JsonSerialize(contentUsing = TimeIntervalSerializer.class)
    private List<@Interval TimeInterval> deliveryHours;

    @JsonProperty("cost")
    @Min(0)
    private Integer cost;

    @JsonProperty("completed_time")
    private String completedTime;

    public OrderDto(Long id, CreateOrderDto order) {
        this(id,order.getWeight(), order.getRegion(), order.getDeliveryHours(), order.getCost());
    }

    public OrderDto(Long id,
                    String weight,
                    Integer region,
                    List<TimeInterval> deliveryHours,
                    Integer cost) {
        this.id = id;
        this.weight = weight;
        this.region = region;
        this.deliveryHours = deliveryHours;
        this.cost = cost;
    }
}
