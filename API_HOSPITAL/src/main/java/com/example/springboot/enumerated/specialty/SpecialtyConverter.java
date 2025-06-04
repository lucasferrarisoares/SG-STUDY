package com.example.springboot.enumerated.specialty;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SpecialtyConverter implements AttributeConverter<Specialty, Integer> {
        @Override
        public Integer convertToDatabaseColumn(Specialty specialty) {
            return specialty != null ? specialty.getCdSpecialty() : null;
        }

        @Override
        public Specialty convertToEntityAttribute(Integer cdSpecialty) {
            return cdSpecialty != null ? Specialty.fromcdSpecialty(cdSpecialty) : null;
        }
}
