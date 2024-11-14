package com.project.cleanenerg.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "projeto")
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Nullable
    private String nome;
    @Nullable
    private String descricao;
    @Nullable
    private Double valorMeta;
    @Nullable
    private Double valorArrecadado = 0.0;

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

}