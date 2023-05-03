package ru.yandex.yandexlavka.entity.converter;

public interface EntityConverter<T,K> {

    T toEntity(K dto);
    K toDto(T entity);

}
