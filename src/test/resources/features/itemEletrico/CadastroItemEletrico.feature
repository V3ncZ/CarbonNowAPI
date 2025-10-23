#language: pt

  @regressivo
  Funcionalidade: Cadastro de novo item elétrico
    Como usuário da API
    Quero cadastrar um novo item elétrico
    Para que o registro seja salvo corretamente no sistema

    Cenário: Cadastro bem-sucedido de item elétrico
      Dado que eu tenha os seguintes dados do item elétrico:
        | campo            | valor              |
        | nome             | Bicicleta Elétrica |
        | consumoEmKw      | 100                |
        | dataUso          | 2025-02-10         |
        | emissaoDeCarbono | 8                  |
      Quando  eu enviar a requisição para o endpoint "/cadastrarItemEletrico" de cadastrar item elétrico
      Então o status code da resposta de item eletrico deve ser 201
      E o arquivo de contrato esperado é o "Cadastro de item eletrico bem sucedido" de item elétrico
      Então a resposta da requisição deve estar em conformidade com o contrato de item elétrico