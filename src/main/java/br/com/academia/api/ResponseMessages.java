package br.com.academia.api;

public enum ResponseMessages {

	ERRO_REQUISICAO("[Validação da entrada: alguns dados não foram passados e/ou estão inválidos]"),
	ERRO_CONSULTA("[Erro ao consultar os registros]"),
	ERRO_INSERIR("[Erro ao inserir novo registro]"),
	ERRO_ATUALIZAR("[Erro ao inserir novo registro]"),
	ERRO_EXCLUIR("[Erro ao excluir o registro"),
	ERRO_EXCLUIR_INEXISTENTE("[Erro ao excluir: registro não existente]"),
	VIOLACAO_INTEGRIDADE("[Control de Integridade: Operação não permitida]"),
	REGISTRO_INEXISTENTE("[Registro não existente]"),
	ERRO_INTERNO("[Erro interno]"),
	REGISTRO_EM_USO("[Registro em uso]") ;

	private final String descricao;

	ResponseMessages(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return descricao;
	}

}
