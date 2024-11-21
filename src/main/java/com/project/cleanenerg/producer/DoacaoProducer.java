package com.project.cleanenerg.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.project.cleanenerg.web.DTO.DoacaoCreateDTO;

@Component
public class DoacaoProducer {

    final RabbitTemplate rabbitTemplate;

    public DoacaoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.process.name}")
    private String routingKey;

    //	Metodo por publicar ás mensagens na fila
    public void processamentoDoacao(DoacaoCreateDTO doacaoDto) {
        DoacaoCreateDTO doacao = new DoacaoCreateDTO();
        doacao.setValor(doacaoDto.getValor());
        doacao.setUsuario(doacaoDto.getUsuario());
        doacao.setProjeto(doacaoDto.getProjeto());

        this.rabbitTemplate.convertAndSend("", this.routingKey, doacao);
    }
}