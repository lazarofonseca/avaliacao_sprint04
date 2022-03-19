package br.com.lazaro.api.config;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LocalDateSerializer extends JsonSerializer<LocalDate>{

	@Override
	public void serialize(LocalDate value, JsonGenerator generator, SerializerProvider serializers) throws IOException {
		 String formattedDate = value.format(DateTimeFormatter.ISO_LOCAL_DATE);
		
	        generator.writeString(formattedDate);
		
	}

}
