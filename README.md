TRABALHO API GATEWAY & INTEGRATION

Para configuração do Docker/Kong Service, disponibilizei 2 arquivos de configuração que podem ser utilizados (na pasta docker):
- docker-compose.yaml
- start-kong.sh

Ambos realizarão as configurações no Docker:
- do Kong
- do Konga
- do Kong-database (postgres - 15)

Devido a configuração 'host.docker.internal:host-gateway', para acesso local do Kong, alterei na minha máquina, em modo admin, o arquivo host (c:\windows\system32\drivers\etc\hosts), acrescentando a seguinte linha:
- 127.0.0.1 host.docker.internal

A API foi desenvolvida em Java - versão 17, e o Maven versão 3.50 como gerenciador de dependências (as dependências necessárias para execução do projeto estão no arquivo pom.xml).

Para realização do CRUD da API foi utilizado, localmente, o banco de dados PostgreSQL Server - versão 17.
Foi utilizado as configurações padrão para conexão/transações com o banco de dados, conforme abaixo:

#application.properties

spring.application.name=api
server.port=8080

spring.datasource.url=jdbc:postgresql://localhost:5432/db_api
spring.datasource.username=postgres
spring.datasource.password="sua senha"
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

#opções de criação do DB, SQL e reload
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.generate-ddl=true
spring.jpa.properties.hibernate.format_sql=true
spring.sql.init.mode=always
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

Na pasta "collection postman" há 02 arquivos:
 - [API Gateway.postman_collection.json] (contém a coleção Postman para teste dos endpoints)
 - [API Gateway Kong Services & Routes.txt] (contém os Json de criação dos services e routes no Kong)