#language: pt

  @regressivo
  Funcionalidade: Cadastro de novo usuário
    Como usuário da API
    Quero cadastrar um novo usuário
    Para que o registro seja salvo corretamente no sistema

    Cenário: Cadastro bem-sucedido de usuário
      Dado que eu tenha os seguintes dados do usuário:
        | campo     | valor                   |
        | nome      | Vinicius Cesar          |
        | email     | viniciuscesar@gmail.com |
        | senha     | Mudar@123456            |
        | role      | ADMIN                   |
      Quando  eu enviar a requisição para o endpoint "/cadastrarUsuario" de cadastrar usuario
      Então o status code da resposta deve ser 201