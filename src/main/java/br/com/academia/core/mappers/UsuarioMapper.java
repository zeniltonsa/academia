package br.com.academia.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import br.com.academia.domain.auth.domain.EntUsuario;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapper {

	void copy(EntUsuario source, @MappingTarget EntUsuario target);

}
