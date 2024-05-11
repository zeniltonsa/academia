package br.com.academia.domain.auth.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "TB_USUARIO_PERMISSAO", uniqueConstraints = @UniqueConstraint(columnNames = { "PERMISSAO_ID",
		"USUARIO_ID" }))
public class EntUsuarioPermissao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8062459839164614687L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", length = 100, nullable = false)
	private Long id;

	@Column(name = "PERMISSAO_ID")
	private Long permissao;

	@Column(name = "USUARIO_ID")
	private Long usuario;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPermissao() {
		return permissao;
	}

	public void setPermissao(Long permissao) {
		this.permissao = permissao;
	}

	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}

}
