package CRUD.PacientesAPI.Kotlim.Infra.Validators

import CRUD.PacientesAPI.Kotlim.Domain.DTOs.AgendamentoConsultaDTO
import CRUD.PacientesAPI.Kotlim.Domain.Entity.MedicoEntity
import CRUD.PacientesAPI.Kotlim.Domain.Entity.PacienteEntity

interface ValidacaoAgendamento {

    fun validar(dto: AgendamentoConsultaDTO, medico: MedicoEntity, paciente: PacienteEntity)


}