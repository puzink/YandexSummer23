package ru.yandex.yandexlavka.dto;

public enum CourierType {
    FOOT(2,3),BYKE(3,2),AUTO(4,1);

    private final Integer earningCoef;
    private final Integer ratingCoef;

    CourierType(Integer earningCoef,
                Integer ratingCoef){
        this.earningCoef = earningCoef;
        this.ratingCoef = ratingCoef;
    }

    public Integer getEarningCoef(){
        return earningCoef;
    }

    public Integer getRatingCoef() {
        return ratingCoef;
    }
}
