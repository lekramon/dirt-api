package com.dirt.api.domain.enums.converter;

import com.dirt.api.domain.enums.CaptureMethodEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class CaptureMethodEnumConverter implements AttributeConverter<CaptureMethodEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(CaptureMethodEnum captureMethodEnum) {
        if (captureMethodEnum == null) {
            return null;
        }
        return captureMethodEnum.getCaptureMethodType();
    }

    @Override
    public CaptureMethodEnum convertToEntityAttribute(Integer captureMethod) {
        if (captureMethod == null) {
            return null;
        }

        return CaptureMethodEnum.fromCode(captureMethod);
    }
}
