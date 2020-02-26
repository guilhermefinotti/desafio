# desafio
Desafio técnico - Criação de um blog

###############################
Configuração de banco de dados
###############################
Antes de realizar o deploy da aplicação no servidor, é necessário criar um novo banco de dados Postgres com o nome desafio.
A URL de acesso ao banco é a seguinte: jdbc:postgresql://localhost:5432/desafio

=> ATENÇÃO aos dados de acesso  no arquivo application.properties
No meu ambiente local, utilizo as seguintes credenciais:
spring.datasource.username=postgres
spring.datasource.password=postgres
Observação: alterar para os dados de conexão do banco de dados instalado localmente

Após a criação do banco de dados, a estrutura de tabelas será criada automaticamente ao realizar o deploy da aplicação.
Após a criação das tabelas, é necessário criar um usuário para acessar o sistema. 
O arquivo "add_usuario.sql" contém o script para a criação de um novo usuário. O seguinte comando deve ser executado:

INSERT INTO public.usuario(email, nome, senha)
	VALUES ('joao@frwk.com.br', 'João Oliveira', '$2a$10$mQlnGtJR6lry/SJtSyoRiubjfji0/cEiA6LtjNfjdeWPamQDU0CWe');
  
=> ATENÇÃO: a senha está criptografada!
Ao acessar o sistema, utilizar as seguintes credenciais:
Email=teste@frwk.com.br
Senha=123

###############################
Ambiente de desenvolvimento
###############################
Esse projeto foi desenvolvido na IDE Spring Tool Suite 4 - Version: 4.4.0.RELEASE e não está otimizado para ambiente de produção.

