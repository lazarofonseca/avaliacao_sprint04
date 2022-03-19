package br.com.lazaro.api.config;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate>{

	 @Override
	    public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {
	        String value = parser.getValueAsString();
	        return LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE);
	    }
}
