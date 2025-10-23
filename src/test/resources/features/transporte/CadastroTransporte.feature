#language: pt

    @regressivo
    Funcionalidade: Cadastro de novo transporte
        Como usuário da API
        Quero cadastrar um novo transporte
        Para que o registro seja salvo corretamente no sistema

        Cenário: Cadastro bem-sucedido de transporte
        Dado que eu tenha os seguintes dados do transporte:
            | campo               | valor         |
            | nome                | Caminhão      |
            | distanciaEmKm       | 120           |
            | dataDeUso           | 2025-02-10    |
            | emissaoDeCarbono    | 100           |
            | emissaoPermitidaIso | 100           |
            | conformeIso         | true          |
        Quando  eu enviar a requisição para o endpoint "/cadastrarTransporte" de cadastrar transporte
        Então o status code da resposta de transporte deve ser 201
            E o arquivo de contrato esperado é o "Cadastro de transporte bem sucedido" de transporte
            Então a resposta da requisição deve estar em conformidade com o contrato de transporte