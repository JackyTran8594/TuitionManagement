package com.hta.tuitionmanagement.config.formatdate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hta.tuitionmanagement.utils.DataUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    public static final String YYYY_MM_DD_T_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss";

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        try {
            String result = localDateTimeToString(localDateTime, YYYY_MM_DD_T_HH_MM_SS);
            jsonGenerator.writeString(result);
        } catch (DateTimeParseException exception) {
            log.error(exception.getMessage(), exception);
            jsonGenerator.writeString("");
        }
    }

    private static String localDateTimeToString(LocalDateTime value, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        if (DataUtils.isNullOrEmpty(value)) {
            return null;
        }
        return value.format(formatter);
    }
}
