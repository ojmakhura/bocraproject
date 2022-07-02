package bw.org.bocra.portal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Configuration
public class SpringRestConfiguration {
	// @Bean
    // @Override
    // public FormattingConversionService mvcConversionService() {
    //     DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);

    //     DateTimeFormatterRegistrar dateTimeRegistrar = new DateTimeFormatterRegistrar();
    //     dateTimeRegistrar.setDateFormatter(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    //     dateTimeRegistrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
    //     dateTimeRegistrar.registerFormatters(conversionService);

    //     DateFormatterRegistrar dateRegistrar = new DateFormatterRegistrar();
    //     dateRegistrar.setFormatter(new DateFormatter("dd.MM.yyyy"));
    //     dateRegistrar.registerFormatters(conversionService);

    //     return conversionService;
    // }

    // @Bean
    // public ObjectMapper jsonObjectMapper() {
    //     final ObjectMapper jsonMapper = new ObjectMapper();
    //     jsonMapper.registerModule(new JavaTimeModule());
    //     jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    //     //some other configuration like:
    //     jsonMapper.registerModule(new Jdk8Module());
    //     jsonMapper.disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS);

    //     return jsonMapper;
    // }
}
