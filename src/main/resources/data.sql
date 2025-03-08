INSERT INTO lily_36962a6e1e5cf75e.tb_permissao (descricao) VALUES('CLIENTE_ALTERAR');
INSERT INTO lily_36962a6e1e5cf75e.tb_permissao (descricao) VALUES('CLIENTE_CADASTRAR');
INSERT INTO lily_36962a6e1e5cf75e.tb_permissao (descricao) VALUES('CLIENTE_DELETAR');
INSERT INTO lily_36962a6e1e5cf75e.tb_permissao (descricao) VALUES('CLIENTE_LISTAR');
INSERT INTO lily_36962a6e1e5cf75e.tb_permissao (descricao) VALUES('MENSALIDADE_ALTERAR');
INSERT INTO lily_36962a6e1e5cf75e.tb_permissao (descricao) VALUES('MENSALIDADE_CADASTRAR');
INSERT INTO lily_36962a6e1e5cf75e.tb_permissao (descricao) VALUES('MENSALIDADE_DELETAR');
INSERT INTO lily_36962a6e1e5cf75e.tb_permissao (descricao) VALUES('MENSALIDADE_LISTAR');
INSERT INTO lily_36962a6e1e5cf75e.tb_permissao (descricao) VALUES('USUARIO_ALTERAR');
INSERT INTO lily_36962a6e1e5cf75e.tb_permissao (descricao) VALUES('USUARIO_CADASTRAR');
INSERT INTO lily_36962a6e1e5cf75e.tb_permissao (descricao) VALUES('USUARIO_DELETAR');
INSERT INTO lily_36962a6e1e5cf75e.tb_permissao (descricao) VALUES('USUARIO_LISTAR');

INSERT INTO lily_36962a6e1e5cf75e.tb_usuario
(login, nome, senha)
VALUES('admin', 'Administrador', '9928');

INSERT INTO lily_36962a6e1e5cf75e.tb_cliente
(dt_cadastro, dt_nascimento, nome_completo, nr_telefone, usuario_id)
VALUES('2024-11-15 14:49:29', '2024-11-15', 'JOÃO DAS COUVES', '82999999999', 1);