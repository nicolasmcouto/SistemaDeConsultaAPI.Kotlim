package CRUD.PacientesAPI.Kotlim.Domain.Mapper

import CRUD.PacientesAPI.Kotlim.Domain.DTOs.AgendamentoConsultaDTO
import CRUD.PacientesAPI.Kotlim.Domain.DTOs.ConsultaDTO
import CRUD.PacientesAPI.Kotlim.Domain.Entity.ConsultaEntity


fun ConsultaEntity.toAgendamentoDTO() = AgendamentoConsultaDTO(
    id = this.id!!,
    medicoId = this.medicoId.id!!,
    pacienteId = this.pacienteId.id!!,
    data = this.data
)

fun ConsultaEntity.toDTO() = ConsultaDTO(
    id = this.id!!,
    medicoId = this.medicoId,
    pacienteId = this.pacienteId,
    data = this.data
)