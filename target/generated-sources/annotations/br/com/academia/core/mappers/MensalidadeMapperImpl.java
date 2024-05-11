package br.com.academia.core.mappers;

import br.com.academia.domain.financeiro.model.EntMensalidade;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-11T18:40:32-0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.37.0.v20240215-1558, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class MensalidadeMapperImpl implements MensalidadeMapper {

    @Override
    public void copy(EntMensalidade source, EntMensalidade target) {

        if ( source != null ) {
            if ( source.getId() != null ) {
                target.setId( source.getId() );
            }
            target.setVersion( source.getVersion() );
            if ( source.getValor() != null ) {
                target.setValor( source.getValor() );
            }
            if ( source.getValorRecebido() != null ) {
                target.setValorRecebido( source.getValorRecebido() );
            }
            if ( source.getDataVencimento() != null ) {
                target.setDataVencimento( source.getDataVencimento() );
            }
            if ( source.getDataPagamento() != null ) {
                target.setDataPagamento( source.getDataPagamento() );
            }
            if ( source.getAluno() != null ) {
                target.setAluno( source.getAluno() );
            }
        }
    }
}
