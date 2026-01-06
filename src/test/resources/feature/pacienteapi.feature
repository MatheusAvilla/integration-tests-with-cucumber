# language: pt

Funcionalidade: Cadastrar Paciente

  Como um cliente externo da API
  Quero cadastrar um novo paciente
  Para que ele fique disponível nas listagens futuras

  Cenário: Cadastrar paciente com sucesso
    Dado que eu possuo os seguintes dados de paciente:
      | nome          | email                | cpf         |
      | João da Silva | joao.silva@email.com | 12345678900 |
    Quando eu faço uma requisição POST para cadastrar o paciente
    Então a resposta deve ter o status code 201
    E a resposta deve conter o nome "João da Silva"
    E o paciente deve estar salvo no banco de dados