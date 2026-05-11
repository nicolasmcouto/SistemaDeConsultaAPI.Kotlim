package CRUD.PacientesAPI.Kotlim.Domain.Mapper

import CRUD.PacientesAPI.Kotlim.Domain.DTOs.AgendamentoConsultaDTO
import CRUD.PacientesAPI.Kotlim.Domain.Entity.ConsultaEntity


fun ConsultaEntity.toDTO() = AgendamentoConsultaDTO(
    id = this.id!!,
    medicoId = this.medicoId.id!!,
    pacienteId = this.pacienteId.id!!,
    data = this.data
)