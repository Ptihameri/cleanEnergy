package com.project.cleanenerg.web.DTO;

public class ProjetoCreateDTO {
    private String nome;
    private String descricao;
    private Double valorMeta;
    private Double valorArrecadado;

    public ProjetoCreateDTO() {
    }

    public ProjetoCreateDTO(String nome, String descricao, Double valorMeta, Double valorArrecadado) {
        this.nome = nome;
        this.descricao = descricao;
        this.valorMeta = valorMeta;
        this.valorArrecadado = valorArrecadado;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getValorMeta() {
        return valorMeta;
    }

    public Double getValorArrecadado() {
        return valorArrecadado;
    }
}
