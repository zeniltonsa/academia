package br.com.academia.core.mappers;

import br.com.academia.domain.aluno.model.EntAluno;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-11T18:40:32-0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.37.0.v20240215-1558, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class AlunoMapperImpl implements AlunoMapper {

    @Override
    public void copy(EntAluno source, EntAluno target) {

        if ( source != null ) {
            if ( source.getId() != null ) {
                target.setId( source.getId() );
            }
            target.setVersion( source.getVersion() );
            if ( source.getNomeCompleto() != null ) {
                target.setNomeCompleto( source.getNomeCompleto() );
            }
            if ( source.getDataNascimento() != null ) {
                target.setDataNascimento( source.getDataNascimento() );
            }
            if ( source.getDataCadastro() != null ) {
                target.setDataCadastro( source.getDataCadastro() );
            }
        }
    }
}
