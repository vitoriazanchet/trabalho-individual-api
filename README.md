# 🏥 Clinica Médica Popular API - Sistema de Gestão de Consultas

API Restful desenvolvida em Java com Spring Boot para o gerenciamento de uma clínica médica. O sistema permite o cadastro de pacientes, médicos, especialidades e o agendamento/histórico de consultas médicas com regras de negócio automatizadas.

---

## 🚀 Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 4.0.6**
  * Spring Data JPA
  * Spring Web
  * Validation (Bean Validation)
* **PostgreSQL** (Banco de dados relacional)
* **Maven** (Gerenciador de dependências)

---

## 🏗️ Arquitetura do Projeto

O projeto segue o modelo de camadas padrão de mercado, garantindo separação de conceitos e facilidade de manutenção:

* **Domain (Entities):** Representação das tabelas do banco de dados (Médico, Paciente, Consulta, Especialidade, Prontuário, StatusConculta).
* **Repository:** Interface de comunicação direta com o banco via Spring Data JPA.
* **Service:** Camada de negócios, onde residem as validações e lógica algorítmica.
* **Controller:** Pontos de entrada da API (Endpoints REST) que manipulam as requisições HTTP.
* **DTO (Data Transfer Object):** Divisão estrita entre dados de entrada (`Request`) e dados de saída (`Response`).

---

## 🗺️ Endpoints da API

### 🧑‍⚕️ Médicos (`/medicos`)
* `GET /medicos` - Lista todos os médicos cadastrados.
* `GET /medicos/{id}` - Busca os detalhes de um médico específico pelo id.
* `GET /medicos/buscar?nome={nome}` - Busca os detalhes de um médico específico pelo nome.
* `POST /medicos` - Cadastra um novo médico (Campos validados: Nome, CRM único).
* `PUT /medicos/{id}` - Atualiza os dados de um médico existente.
* `DELETE /medicos/{id}` - Remove um médico do sistema.

### 👥 Pacientes (`/pacientes`)
* `GET /pacientes` - Lista todos os pacientes.
* `GET /pacientes/{id}` - Busca os detalhes de um paciente específico pelo id.
* `GET /pacientes/buscar?nome={nome}` - Busca os detalhes de um paciente específico pelo nome.
* `GET /pacientes/{id}/prontuario` - Exibe o histórico clínico e consultas daquele paciente.
* `POST /pacientes` - Cadastra um novo paciente (Campos validados: Nome, CPF único, data de nascimento, Email, telefone).
* `PUT /pacientes/{id}` - Atualiza os dados de um paciente existente.
* `DELETE /pacientes/{id}` - Remove um paciente do sistema.


### 📅 Consultas (`/consultas`)
* `POST /consultas` - Agenda uma nova consulta médica.
  * *Regra de Negócio:* Impede agendamentos duplicados para o mesmo médico ou paciente no mesmo intervalo de horário.

---

## 🛡️ Regras de Validação Aplicadas

* **Não Duplicidade:** CPF de pacientes e CRM de médicos são chaves únicas no sistema.
* **Consistência de Agenda:** Validação de choque de horários na agenda dos profissionais de saúde.
* **Tratamento de Erros:** Erros de validação retornam HTTP 400 Bad Request estruturado. IDs inexistentes retornam HTTP 404 Not Found.

---

## 🏃 Como Executar o Projeto

1. Clone este repositório em sua máquina local:
   ```bash
   git clone https://github.com/vitoriazanchet/trabalho-individual-api.git

2. No Postman ou navegador, utilize as url: 
* GET http://localhost:8080/pacientes - para listar todos os pacientes.
* GET http://localhost:8080/médicos - para listar todos os médicos.
* GET http://localhost:8080/especielidades - para listar todas as especielidades.
* GET http://localhost:8080/consultas - para listar todas as consultas.

3. Edite conforme seu objetivo seguindo os endpoints.

👩‍💻 Aluna Responsável:

**Vitória de Paula Zanchet** - [GitHub](https://github.com/vitoriazanchet/) | [LinkedIn](https://www.linkedin.com/in/vitoria-zanchet/)

