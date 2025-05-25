package com.uninassau.periodo3.backend.atv_01.agendamento.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "agendamento")
@Data
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false, length = 14)
    private String telefone;
}
