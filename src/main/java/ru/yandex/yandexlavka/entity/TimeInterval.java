package ru.yandex.yandexlavka.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.time.LocalTime;

@Embeddable
@Data
public class TimeInterval {

    @Column(name = "time_from")
    private LocalTime from;

    @Column(name = "time_to")
    private LocalTime to;

}
