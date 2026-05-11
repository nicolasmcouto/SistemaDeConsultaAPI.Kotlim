package CRUD.PacientesAPI.Kotlim.Domain.DTOs

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Past
import java.time.LocalDate

data class PacienteUpdateDTO(
    val id: Long? = null,
    val nome: String? = null,
    val email: String? = null,
    val dataDeNascimento: LocalDate? = null,
    val ativa: Boolean? = null
) {
}