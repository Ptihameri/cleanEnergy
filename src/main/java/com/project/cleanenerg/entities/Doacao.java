package com.project.cleanenerg.entities;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "doacao")
public class Doacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double valor;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;
    @CreatedDate
    @Column(name = "dataCriacao")
    private LocalDateTime dataCriacao;
    @LastModifiedDate
    @Column(name = "dataModificacao")
    private LocalDateTime dataModificacao;
    @CreatedBy
    @Column(name = "criadoPor")
    private String criadoPor;
    @LastModifiedBy
    @Column(name = "modificadoPor")
    private String modificadoPor;

    private LocalDateTime dataDoacao = LocalDateTime.now();

}