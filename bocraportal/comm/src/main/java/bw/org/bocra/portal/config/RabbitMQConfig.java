package bw.org.bocra.portal.config;

// import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;
import org.springframework.retry.interceptor.StatefulRetryOperationsInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import bw.org.bocra.portal.properties.RabbitProperties;

@Configuration
// @Slf4j
public class RabbitMQConfig {
    private final CachingConnectionFactory cachingConnectionFactory;
    private final RabbitProperties rabbitProperties;

    public RabbitMQConfig(CachingConnectionFactory cachingConnectionFactory, RabbitProperties rabbitProperties) {
        this.cachingConnectionFactory = cachingConnectionFactory;
        this.rabbitProperties = rabbitProperties;
    }

    @Bean
    public Queue createEmailExchangeQueue() {
        
        return QueueBuilder.durable(rabbitProperties.getEmailHandler())
                .withArgument("x-dead-letter-exchange","x.email-dispatch-failure")
                .withArgument("x-dead-letter-routing-key","fall-back")
                .build();
    }

    @Bean
    public Declarables createPostDispatchSchema(){
        return new Declarables(
                new FanoutExchange("x.post-email-dispatch"),
                new Queue(rabbitProperties.getEmailDispatchQueue() ),
                new Binding(rabbitProperties.getEmailDispatchQueue(), Binding.DestinationType.QUEUE, "x.post-email-dispatch", rabbitProperties.getEmailDispatchRoutingKey(), null)
        );
    }

    @Bean
    public Declarables createDeadLetterSchema(){
        return new Declarables(
            new DirectExchange("x.email-dispatch-failure"),
            new Queue("q.fall-back-email-dispatch"),
            new Binding("q.fall-back-email-dispatch", Binding.DestinationType.QUEUE,"x.email-dispatch-failure", "fall-back", null)
        );
    }

    @Bean
    public RetryOperationsInterceptor retryInterceptor(){
        return RetryInterceptorBuilder.stateless().maxAttempts(3)
                .backOffOptions(2000, 2.0, 100000)
                .recoverer(new RejectAndDontRequeueRecoverer())
                .build();
    }


    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, cachingConnectionFactory);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        factory.setAdviceChain(retryInterceptor());
        factory.setDefaultRequeueRejected(false);
        return factory;
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
    public Jackson2JsonMessageConverter converter(ObjectMapper mapper) {
        return new Jackson2JsonMessageConverter(mapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter converter) {
        RabbitTemplate template = new RabbitTemplate(cachingConnectionFactory);
        
        // mapper.setDate;
        template.setMessageConverter(converter(objectMapper()));
        return template;
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper mapper =
                new ObjectMapper()
                        .registerModule(new ParameterNamesModule())
                        .registerModule(new Jdk8Module())
                        .registerModule(new JavaTimeModule());
        
        // mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }
}
