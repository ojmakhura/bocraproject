package bw.org.bocra.portal.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import bw.org.bocra.portal.properties.RabbitProperties;

@Configuration
public class RabbitMQConfig {

    private final RabbitProperties rabbitProperties;
    private final CachingConnectionFactory cachingConnectionFactory;

    public RabbitMQConfig(RabbitProperties rabbitProperties, CachingConnectionFactory cachingConnectionFactory) {
        this.rabbitProperties = rabbitProperties;
        this.cachingConnectionFactory = cachingConnectionFactory;
    }

    @Bean
	Queue emailQueue() {
		return new Queue(rabbitProperties.getQueue(), false);
	}

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(rabbitProperties.getExchange());
	}

	@Bean
	Binding binding(Queue emailQueue, DirectExchange exchange) {
		return BindingBuilder.bind(emailQueue()).to(exchange()).with(rabbitProperties.getRoutingkey());
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
    
    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        JavaTimeModule module = new JavaTimeModule();
        return new ObjectMapper().registerModule(module);
    }
	
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
}
