package CRUD.PacientesAPI.Kotlim.Domain.Entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "consultas")
class ConsultaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "medico_id")
    var medicoId : MedicoEntity,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paciente_id")
    var  pacienteId : PacienteEntity,

    @Column
    var  data : LocalDateTime
) {

}