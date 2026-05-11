package CRUD.PacientesAPI.Kotlim.Domain.DTOs

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Past
import java.time.LocalDate

data class PacienteDTO(
    val id: Long? = null,
    @field: NotBlank(message = "nome é obrigatorio")
    val nome: String,
    @field: Email(message = "Email invalido")
    val email: String? = null,
    @field: Past(message = "Data invalida")
    val dataDeNascimento: LocalDate? = null,
    val ativa: Boolean = true
)