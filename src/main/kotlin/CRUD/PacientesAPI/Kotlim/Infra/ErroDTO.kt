package CRUD.PacientesAPI.Kotlim.Infra

data class ErroValidacaoDTO(
    val campo: String,
    val mensagem: String)

data class MensagemDeErroDTO(val mensagem: String)