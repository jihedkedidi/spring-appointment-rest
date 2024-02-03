package com.agil.admin.model;

import javax.persistence.AttributeConverter;

public class EnumToIntValue implements AttributeConverter<EStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(final EStatus status) {
        //return status.getValue();
        return null;
    }

    @Override
    public EStatus convertToEntityAttribute(Integer integer) {
        if (integer == 0) {
            return EStatus
                    .PENDING;
        } else if (integer == 1) {
            return EStatus.CONFIRMED;
        } else if (integer == 2) {
            return EStatus.CANCELED;
        }
        return null;
    }

}

