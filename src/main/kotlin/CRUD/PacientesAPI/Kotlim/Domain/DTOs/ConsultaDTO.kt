package CRUD.PacientesAPI.Kotlim.Domain.DTOs

import CRUD.PacientesAPI.Kotlim.Domain.Entity.MedicoEntity
import CRUD.PacientesAPI.Kotlim.Domain.Entity.PacienteEntity
import jakarta.persistence.Column
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

data class ConsultaDTO(
    val id: Long,
    val medicoId : MedicoEntity,
    val  pacienteId : PacienteEntity,
    val  data : LocalDateTime
)
