# desafio
Desafio técnico - Criação de um blog

###############################<br>
Configuração de banco de dados<br>
###############################
<br>
Antes de realizar o deploy da aplicação no servidor, é necessário criar um novo banco de dados Postgres com o nome desafio.
<br>
A URL de acesso ao banco é a seguinte: jdbc:postgresql://localhost:5432/desafio <br>

=> ATENÇÃO aos dados de acesso  no arquivo application.properties<br>
No meu ambiente local, utilizo as seguintes credenciais:<br>
spring.datasource.username=postgres<br>
spring.datasource.password=postgres<br>
Observação: alterar para os dados de conexão do banco de dados instalado localmente<br>

Após a criação do banco de dados, a estrutura de tabelas será criada automaticamente ao realizar o deploy da aplicação.<br>
Após a criação das tabelas, é necessário criar um usuário para acessar o sistema. <br>
O arquivo "add_usuario.sql" contém o script para a criação de um novo usuário. <br>
O seguinte comando deve ser executado:<br>

INSERT INTO public.usuario(email, nome, senha)
	VALUES ('joao@frwk.com.br', 'João Oliveira', '$2a$10$mQlnGtJR6lry/SJtSyoRiubjfji0/cEiA6LtjNfjdeWPamQDU0CWe'); <br>

=> ATENÇÃO: a senha está criptografada! <br>
Ao acessar o sistema, utilizar as seguintes credenciais: <br>
Email=teste@frwk.com.br<br>
Senha=123<br>

############################### <br>
Ambiente de desenvolvimento <br>
############################### <br>
Esse projeto foi desenvolvido na IDE Spring Tool Suite 4 - Version: 4.4.0.RELEASE e não está otimizado para ambiente de produção.

