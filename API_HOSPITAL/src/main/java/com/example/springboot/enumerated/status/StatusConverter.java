package com.example.springboot.enumerated.status;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, Integer> {
        @Override
        public Integer convertToDatabaseColumn(Status status) {
            return status != null ? status.getCdStatus() : null;
        }

        @Override
        public Status convertToEntityAttribute(Integer cdStatus) {
            return cdStatus != null ? Status.fromcdStatus(cdStatus) : null;
        }
}
