package br.com.academia.core.converters;

import java.time.LocalDate;

import org.springframework.core.convert.converter.Converter;

public class LocalDateConverter implements Converter<String, LocalDate> {

	@Override
	public LocalDate convert(String source) {
		
		if (source == null || source.isEmpty() || source.equals("null")) {
			return null;
		}

		return LocalDate.parse(source);
	}

}
