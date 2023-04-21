package ru.yandex.yandexlavka.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.yandex.yandexlavka.dto.CourierType;

import java.util.List;

@Entity
@Table(name="courier")
@Data
public class Courier {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private CourierType type;

    @ElementCollection
    @CollectionTable(name="courier_regions",
            joinColumns = @JoinColumn(name="courier_id"))
    @Column(name="region")
    private List<Integer> regions;

    @ElementCollection
    @CollectionTable(name = "courier_working_hours",
            joinColumns = @JoinColumn(name="courier_id"))
    private List<TimeInterval> workingHours;

}
