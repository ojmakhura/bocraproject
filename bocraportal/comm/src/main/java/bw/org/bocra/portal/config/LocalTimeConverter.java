package bw.org.bocra.portal.config;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LocalTimeConverter implements Converter<String, LocalTime> {
    private static final List<String> SUPPORTED_FORMATS = Arrays.asList("HH:mm:ss", "HH:mm", "HH:mm:ss.SSSXXX");
    private static final List<DateTimeFormatter> DATE_TIME_FORMATTERS = SUPPORTED_FORMATS
            .stream()
            .map(DateTimeFormatter::ofPattern)
            .collect(Collectors.toList());

    @Override
    public LocalTime convert(String s) {

        for (DateTimeFormatter dateTimeFormatter : DATE_TIME_FORMATTERS) {
            try {
                return LocalTime.parse(s, dateTimeFormatter);
            } catch (DateTimeParseException ex) {
                // deliberate empty block so that all parsers run
            }
        }

        throw new DateTimeException(String.format("unable to parse (%s) supported formats are %s",
                s, String.join(", ", SUPPORTED_FORMATS)));
    }
}
