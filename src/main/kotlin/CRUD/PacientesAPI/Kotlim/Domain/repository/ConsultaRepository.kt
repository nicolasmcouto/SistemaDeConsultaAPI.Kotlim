package CRUD.PacientesAPI.Kotlim.Domain.repository

import CRUD.PacientesAPI.Kotlim.Domain.Entity.ConsultaEntity
import CRUD.PacientesAPI.Kotlim.Domain.Entity.MedicoEntity
import CRUD.PacientesAPI.Kotlim.Domain.Entity.PacienteEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface ConsultaRepository: JpaRepository<ConsultaEntity, Long> {

    fun existsByMedicoIdAndData(medico: MedicoEntity, data: LocalDateTime): Boolean

    fun existsByPacienteIdAndData(paciente: PacienteEntity, data: LocalDateTime): Boolean
}
