package CRUD.PacientesAPI.Kotlim.Domain.Entity

import CRUD.PacientesAPI.Kotlim.Domain.DTOs.PacienteDTO
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "pacientes")
class PacienteEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var nome: String,

    @Column(unique = true)
    var email: String? = null,

    @Column
    var dataDeNascimento: LocalDate? = null,

    @Column(nullable = false)
    var ativa: Boolean = true

) {
    fun PacienteEntity.toDTO() = PacienteDTO(
        id = id,
        nome = nome,
        email = email,
        dataDeNascimento = dataDeNascimento,
        ativa = ativa
    )

    fun PacienteDTO.toEntity() = PacienteEntity(
        id = id?: 0,
        nome = nome,
        email = email,
        dataDeNascimento = dataDeNascimento,
        ativa = ativa

    )
}