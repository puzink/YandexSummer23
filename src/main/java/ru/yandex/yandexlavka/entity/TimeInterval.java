package ru.yandex.yandexlavka.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Embeddable
@Data
@NoArgsConstructor
public class TimeInterval {

    private LocalTime from;
    private LocalTime to;

    public TimeInterval(LocalTime from, LocalTime to) {
        this.from = from;
        this.to = to;
    }

    public String toString(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        return String.format("%s-%s", from.format(dtf), to.format(dtf));
    }

}
