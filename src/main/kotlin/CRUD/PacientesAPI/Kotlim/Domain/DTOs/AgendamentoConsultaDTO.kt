package CRUD.PacientesAPI.Kotlim.Domain.DTOs

import CRUD.PacientesAPI.Kotlim.Domain.Entity.MedicoEntity
import CRUD.PacientesAPI.Kotlim.Domain.Entity.PacienteEntity
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

data class AgendamentoConsultaDTO(

    val id: Long? = null,

    @field:NotNull(message = "O id do médico é obrigatório")
    val medicoId: Long,

    @field:NotNull(message = "O id do paciente é obrigatório")
    val pacienteId: Long,

    @field:NotNull(message = "A data da consulta é obrigatória")
    @field:Future
    val data: LocalDateTime
)