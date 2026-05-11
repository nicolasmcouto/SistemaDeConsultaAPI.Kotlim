package CRUD.PacientesAPI.Kotlim.Domain.DTOs

import CRUD.PacientesAPI.Kotlim.Domain.enums.Especialidade
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Past
import java.time.LocalDate

data class MedicoDTO(
    val id: Long? = null,
     @field: NotBlank(message = "nome é obrigatorio")
     val nome: String,
    @field: NotBlank(message = "crm é obrigatorio")
    val crm: String,
     @field:Email(message = "Email invalido")
     val email: String,
     @field:NotNull(message = "especialidade é obrigatorio")
     val especialidade: Especialidade? = null,
     val ativa: Boolean = true
)
