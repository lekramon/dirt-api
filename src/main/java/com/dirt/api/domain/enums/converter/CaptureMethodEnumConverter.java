package com.dirt.api.domain.enums.converter;

import com.dirt.api.domain.enums.CaptureMethodEnum;
import com.dirt.api.domain.exception.EnumNotExistException;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class CaptureMethodEnumConverter implements AttributeConverter<CaptureMethodEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(CaptureMethodEnum captureMethodEnum) {
        switch (captureMethodEnum) {
            case WEB:
                return 1;
            case APP:
                return 2;
            case ATM:
                return 3;
            default:
                throw new EnumNotExistException("The capture method: " + captureMethodEnum + " doesn't exist");
        }
    }

    @Override
    public CaptureMethodEnum convertToEntityAttribute(Integer captureMethod) {
        switch (captureMethod) {
            case 1:
                return CaptureMethodEnum.WEB;
            case 2:
                return CaptureMethodEnum.APP;
            case 3:
                return CaptureMethodEnum.ATM;
            default:
                throw new EnumNotExistException("The capture method: " + captureMethod + " doesn't exist");
        }
    }
}