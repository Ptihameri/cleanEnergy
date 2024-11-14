package com.project.cleanenerg.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;
    @NotNull
    @Column(unique = true)
    private String username;
    @NotNull
    private String password;
    @Email
    @NotNull
    @Column(unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 25)
    private ROLE role = ROLE.ROLE_CLIENTE;
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

    public enum ROLE{
        ROLE_CLIENTE,
        ROLE_ADMIN
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

}