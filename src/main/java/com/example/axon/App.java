package com.example.axon;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	private static final String COMPLAINTS = "complaints";

	 @Bean
	 Exchange exchange() {
	  return ExchangeBuilder.fanoutExchange(COMPLAINTS).build();
	 }

	 @Bean
	 Queue queue() {
	  return QueueBuilder.durable(COMPLAINTS).build();
	 }	

	 @Bean
	 Binding binding() {
	  return BindingBuilder.bind(queue()) //
	   .to(exchange()).with("*").noargs();
	 }

	 @Autowired
	 public void configure(AmqpAdmin admin) {
	  admin.declareExchange(exchange());
	  admin.declareQueue(queue());
	  admin.declareBinding(binding());
	 }
}
