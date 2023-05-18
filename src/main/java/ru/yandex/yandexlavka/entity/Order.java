package ru.yandex.yandexlavka.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "order", schema = "public")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="weight")
    private String weight;

    @Column(name = "region")
    private Integer region;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "order_delivery_hours",
            joinColumns = @JoinColumn(name="order_id")
    )
    @AttributeOverrides({
            @AttributeOverride(name="to",
                column = @Column(name="time_to")),
            @AttributeOverride(name="from",
                column = @Column(name="time_from"))
    })
    private List<TimeInterval> deliveryHours;

    @Column(name="cost")
    private Integer cost;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="courier_id")
    private Courier courier;

    @Column(name="completed_time")
    private LocalDateTime completedTime;

    public boolean isCompleted(){
        return !(Objects.isNull(completedTime) && Objects.isNull(courier));
    }
}
