package com.project.cleanenerg.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

	final RabbitTemplate rabbitTemplate;

	public UserProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Value(value = "${broker.queue.email.name}")
	private String routingKey;


//	Metodo por publicar ás mensagens na fila
//	public void publishCodeEmailUpdatePassword(AuthNewCode email, VerificationCodeValidate code) {
//		EmailDto emailDto = new EmailDto();
//		emailDto.setEmailTo(email.getEmail());
//		emailDto.setSubject("Esqueceu a senha?");
//		emailDto.setText("Para resetar a senha, utilize o seguinte codigo: " + code.getCode());
//
//		this.rabbitTemplate.convertAndSend("", this.routingKey, emailDto);
//	}
}
