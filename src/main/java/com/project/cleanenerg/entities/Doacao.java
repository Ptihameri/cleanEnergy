package com.project.cleanenerg.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "doacao")
@EntityListeners(AuditingEntityListener.class)
public class Doacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "O valor da doação não pode ser nulo")
    @Positive(message = "O valor da doação deve ser positivo")
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "projeto_id", nullable = false)
    private Projeto projeto;

    @CreatedDate
    @Column(name = "data_doacao", nullable = false)
    private LocalDateTime dataDoacao = LocalDateTime.now();

    @CreatedBy
    @Column(name = "criado_por", updatable = false)
    private String criadoPor;
}
