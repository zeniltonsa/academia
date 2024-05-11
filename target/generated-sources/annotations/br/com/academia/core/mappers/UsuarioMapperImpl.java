package br.com.academia.core.mappers;

import br.com.academia.domain.auth.domain.EntPermissao;
import br.com.academia.domain.auth.domain.EntUsuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-11T18:50:38-0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.37.0.v20240215-1558, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public void copy(EntUsuario source, EntUsuario target) {

        if ( source != null ) {
            if ( source.getId() != null ) {
                target.setId( source.getId() );
            }
            if ( source.getLogin() != null ) {
                target.setLogin( source.getLogin() );
            }
            if ( source.getNome() != null ) {
                target.setNome( source.getNome() );
            }
            if ( target.getPermissoes() != null ) {
                List<EntPermissao> list = source.getPermissoes();
                if ( list != null ) {
                    target.getPermissoes().clear();
                    target.getPermissoes().addAll( list );
                }
            }
            else {
                List<EntPermissao> list = source.getPermissoes();
                if ( list != null ) {
                    target.setPermissoes( new ArrayList<EntPermissao>( list ) );
                }
            }
            if ( source.getSenha() != null ) {
                target.setSenha( source.getSenha() );
            }
        }
    }
}
