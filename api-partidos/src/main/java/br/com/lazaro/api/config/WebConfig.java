package br.com.lazaro.api.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import com.fasterxml.jackson.core.Version;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    private final ObjectMapper objectMapper;

    public WebConfig(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

    private MappingJackson2HttpMessageConverter customJackson2HttpJsonMessageConverter() {
        SimpleModule module = new SimpleModule("blog", Version.unknownVersion());
		module.addSerializer(LocalDate.class, new LocalDateSerializer());
		module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
		objectMapper.registerModule(module);

		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(objectMapper);
		return converter;
	}

    @Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new StringHttpMessageConverter());
		converters.add(customJackson2HttpJsonMessageConverter());
		converters.add(new FormHttpMessageConverter());
	}
}
