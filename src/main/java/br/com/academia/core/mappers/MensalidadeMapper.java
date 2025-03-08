package br.com.academia.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import br.com.academia.domain.financeiro.RequestMensalidade;
import br.com.academia.domain.financeiro.model.EntMensalidade;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MensalidadeMapper {

	void copy(EntMensalidade source, @MappingTarget EntMensalidade target);

	@Mapping(source = "dataDeVencimento", target = "dataVencimento")
	@Mapping(source = "dataDePagamento", target = "dataPagamento")
	EntMensalidade fromDTO(RequestMensalidade mensalidade);

	RequestMensalidade toDto(EntMensalidade entity);
}
