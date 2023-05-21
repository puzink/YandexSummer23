package ru.yandex.yandexlavka.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.yandex.yandexlavka.controller.constraints.Interval;
import ru.yandex.yandexlavka.controller.constraints.IsReal;
import ru.yandex.yandexlavka.controller.json.StringNumberSerializer;
import ru.yandex.yandexlavka.controller.json.TimeIntervalDeserializer;
import ru.yandex.yandexlavka.controller.json.TimeIntervalSerializer;
import ru.yandex.yandexlavka.controller.request.CreateOrderDto;
import ru.yandex.yandexlavka.entity.TimeInterval;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {
    @JsonProperty("order_id")
    @Min(1)
    private Long id;

    @JsonProperty(value = "weight",required = true)
    @JsonSerialize(using = StringNumberSerializer.class)
    @IsReal
    private String weight;

    @JsonProperty(value = "regions",required = true)
    private Integer region;

    @JsonProperty(value = "delivery_hours",required = true)
    @JsonDeserialize(contentUsing = TimeIntervalDeserializer.class)
    @JsonSerialize(contentUsing = TimeIntervalSerializer.class)
    private List<@Interval TimeInterval> deliveryHours;

    @JsonProperty(value = "cost",required = true)
    @Min(0)
    private Integer cost;

    @JsonProperty(value = "completed_time")
    private LocalDateTime completedTime;

    public OrderDto(Long id, CreateOrderDto order) {
        this(id,order.getWeight(),
                order.getRegion(), order.getDeliveryHours(),
                order.getCost(), null);
    }

    public OrderDto(Long id,
                    String weight,
                    Integer region,
                    List<TimeInterval> deliveryHours,
                    Integer cost,
                    LocalDateTime completedTime) {
        this.id = id;
        this.weight = weight;
        this.region = region;
        this.deliveryHours = deliveryHours;
        this.cost = cost;
        this.completedTime = completedTime;
    }
}
