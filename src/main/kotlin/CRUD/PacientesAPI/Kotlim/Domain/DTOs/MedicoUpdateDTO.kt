package CRUD.PacientesAPI.Kotlim.Domain.DTOs

import CRUD.PacientesAPI.Kotlim.Domain.enums.Especialidade

data class MedicoUpdateDTO(
    val id: Long? = null,
    val nome: String? = null,
    val crm: String? = null,
    val email: String? = null,
    val especialidade: Especialidade? = null,
    val ativa: Boolean? = null
)
