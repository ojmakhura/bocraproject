package bw.org.bocra.portal.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

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
    public Queue createEmailQueue() {
        
        return QueueBuilder.durable(rabbitProperties.getEmailQueue())
                .build();
    }

    @Bean
    public Declarables createEmailQueueSchema() {
        
        return new Declarables(
            new DirectExchange(rabbitProperties.getEmailQueueExchange()),
            emailQueue(),
            emailQueueBinding()
        );
    }

    @Bean
	Queue emailQueue() {
		return new Queue(rabbitProperties.getEmailQueue(), true);
	}

	@Bean
	DirectExchange emailQueueExchange() {
		return new DirectExchange(rabbitProperties.getEmailQueueExchange());
	}

	@Bean
	Binding emailQueueBinding() {
		return BindingBuilder.bind(emailQueue()).to(emailQueueExchange()).with(rabbitProperties.getEmailQueueRoutingKey());
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
    
    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        
        return new ObjectMapper()
                    .registerModule(new ParameterNamesModule())
                    .registerModule(new Jdk8Module())
                    .registerModule(new JavaTimeModule())
                    .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                    .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
}
