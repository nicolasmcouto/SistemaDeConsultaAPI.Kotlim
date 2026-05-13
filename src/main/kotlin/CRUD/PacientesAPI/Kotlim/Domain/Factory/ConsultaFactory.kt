package CRUD.PacientesAPI.Kotlim.Domain.Factory

import CRUD.PacientesAPI.Kotlim.Domain.DTOs.AgendamentoConsultaDTO
import CRUD.PacientesAPI.Kotlim.Domain.Entity.ConsultaEntity
import CRUD.PacientesAPI.Kotlim.Domain.Entity.MedicoEntity
import CRUD.PacientesAPI.Kotlim.Domain.Entity.PacienteEntity

object ConsultaFactory {

    fun create(dto: AgendamentoConsultaDTO, medico: MedicoEntity, paciente: PacienteEntity, )
    : ConsultaEntity {
        return ConsultaEntity(
            id = dto.id,
            medicoId = medico,
            pacienteId = paciente,
            data = dto.data
        )

    }
}