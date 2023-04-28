package ru.yandex.yandexlavka.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "order")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name="weight")
    private String weight;

    @Column(name = "region")
    private Integer region;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "order_delivery_hours",
            joinColumns = @JoinColumn(name="order_id"))
    private List<TimeInterval> deliveryHours;

    @Column(name="cost")
    private Integer cost;

    @Column(name="completed_time")
    @Nullable
    private Date completedTime;
}
