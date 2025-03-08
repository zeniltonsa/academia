package br.com.academia.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import br.com.academia.domain.cliente.dto.response.ClienteDTO;
import br.com.academia.domain.cliente.model.EntCliente;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClienteMapper {

	void copy(EntCliente source, @MappingTarget EntCliente target);

	ClienteDTO toDto(EntCliente cliente);

}
