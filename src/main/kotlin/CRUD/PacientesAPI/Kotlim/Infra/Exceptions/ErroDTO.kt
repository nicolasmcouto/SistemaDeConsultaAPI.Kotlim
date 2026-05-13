package CRUD.PacientesAPI.Kotlim.Infra.Exceptions

data class ErroValidacaoDTO(
    val campo: String,
    val mensagem: String)

data class MensagemDeErroDTO(val mensagem: String)