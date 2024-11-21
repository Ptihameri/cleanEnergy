package com.project.cleanenerg.consumer;

import java.util.Random;

import com.project.cleanenerg.entities.Projeto;
import com.project.cleanenerg.repository.ProjetoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.project.cleanenerg.entities.Doacao;
import com.project.cleanenerg.enums.StatusPagamento;
import com.project.cleanenerg.exception.NaoEmcontradoException;
import com.project.cleanenerg.repository.DoacaoRepository;
import com.project.cleanenerg.web.DTO.DoacaoCreateDTO;

@Component
public class DoacaoConsumer {

    private final DoacaoRepository doacaoRepository;
    private final ProjetoRepository projetoRepository;

    public DoacaoConsumer(DoacaoRepository doacaoRepository,
                          ProjetoRepository projetoRepository) {
        this.doacaoRepository = doacaoRepository;
        this.projetoRepository = projetoRepository;
    }

    @RabbitListener(queues = "${broker.queue.process.name}")
    public void listenEmailQueue(@Payload DoacaoCreateDTO doacaoDto) {
        // Busca a doação no banco de dados
        Doacao doacao = doacaoRepository.findById(doacaoDto.getProjeto())
                .orElseThrow(() -> new NaoEmcontradoException("Doação não encontrada"));

        // Atualiza o status da doação aleatoriamente
        atualizarStatusAleatorio(doacao);

        // Salva a doação no banco de dados
        if (doacao.getStatusPagamento() ==StatusPagamento.EFETUADO ) {
            doacaoRepository.save(doacao);
        }else {
            Projeto projeto = doacao.getProjeto();
            if (projeto != null) {
                projeto.setValorArrecadado(projeto.getValorArrecadado() - doacao.getValor());
                projetoRepository.save(projeto);
            }
            throw new RuntimeException("Nao foi concluido" + doacao.getStatusPagamento());
        }
    }

    private void atualizarStatusAleatorio(Doacao doacao) {
        StatusPagamento[] valores = {StatusPagamento.EFETUADO, StatusPagamento.RECUSADO, StatusPagamento.ESTORNADO};
        Random random = new Random();
        StatusPagamento statusAleatorio = valores[random.nextInt(valores.length)];

        doacao.setStatusPagamento(statusAleatorio);
    }
}