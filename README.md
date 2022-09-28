# vet_clinicawszd
API de uma clinica veterinaria, contendo três entidades tutor, pet e agendamento.

Utilizado o banco de dados MYSQL para criar as tabelas, em anexo deste contem o script para criar a tabela e popular a mesma.

Para instalar o projeto: mvn clean install

Para executar o projeto: mvn spring-boot:run

### Acesso local com o swagger: http://localhost:8080/swagger-ui.html#/

### Tutor
* GET  /api/v1/tutor      - Retorna uma lista de tutores
* GET  /api/v1/tutor/{id} - Retorna um tutor
* POST /api/v1/tutor      - Adiciona um tutor
* PUT  /api/v1/tutor      - Altera um tutor
* DEL  /api/v1/tutor/{id} - Deleta um Tutor

### Pet
* GET  /api/v1/pet      - Retorna uma lista de pets
* GET  /api/v1/pet/{id} - Retorna um pet
* POST /api/v1/pet      - Adiciona um pet
* PUT  /api/v1/pet      - Altera um pet
* DEL  /api/v1/pet/{id} - Deleta um pet

### Agendamento
* GET  /api/v1/agendamento          - Retorna uma lista de agendamentos
* GET  /api/v1/agendamento/{id}     - Retorna um agendamento
* GET  /api/v1/agendamento/ano/mes  - Retorna uma lista de agendamentos por ano e mês
* POST /api/v1/agendamento          - Adiciona um agendamento
* PUT  /api/v1/agendamento          - Altera um agendamento
* PATC /api/v1/agendamento          - Altera o status de um agendamento
* DEL  /api/v1/agendamento/{id}     - Deleta um agendamento

[ScriptCreateTabelasTutorPetAgendamento.txt](https://github.com/wesolsv/vet_clinicawszd/files/9660570/ScriptCreateTabelasTutorPetAgendamento.txt)

[Script para popular as tabelas.txt](https://github.com/wesolsv/vet_clinicawszd/files/9660571/Script.para.popular.as.tabelas.txt)
