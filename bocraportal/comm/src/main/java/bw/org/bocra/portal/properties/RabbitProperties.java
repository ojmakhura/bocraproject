package bw.org.bocra.portal.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitProperties {

    private final String host;
    private final int port;
    private final String username;
    private final String password;
    private final String emailHandler;
    private final String emailDispatchExchange;
    private final String emailDispatchQueue;
    private final String emailDispatchRoutingKey;
    private final String emailQueueExchange;
    private final String emailQueue;
    private final String emailQueueRoutingKey;


    public RabbitProperties(String host, int port, String username, String password, String emailDispatchExchange, String emailQueueExchange,
            String emailHandler, String emailDispatchQueue, String emailDispatchRoutingKey, String emailQueue, String emailQueueRoutingKey) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.emailDispatchExchange = emailDispatchExchange;
        this.emailDispatchQueue = emailDispatchQueue;
        this.emailDispatchRoutingKey = emailDispatchRoutingKey;
        this.emailQueue = emailQueue;
        this.emailQueueRoutingKey = emailQueueRoutingKey;
        this.emailQueueExchange = emailQueueExchange;
        this.emailHandler = emailHandler;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailDispatchQueue() {
        return emailDispatchQueue;
    }

    public String getEmailDispatchRoutingKey() {
        return emailDispatchRoutingKey;
    }

    public String getEmailQueue() {
        return emailQueue;
    }

    public String getEmailQueueRoutingKey() {
        return emailQueueRoutingKey;
    }

    public String getEmailDispatchExchange() {
        return emailDispatchExchange;
    }

    public String getEmailQueueExchange() {
        return emailQueueExchange;
    }

    public String getEmailHandler() {
        return emailHandler;
    }

    @Override
    public String toString() {
        return "RabbitProperties [host=" + host + ", port=" + port + ", username=" + username + ", password=" + password
                + ", emailHandler=" + emailHandler + ", emailDispatchExchange=" + emailDispatchExchange
                + ", emailDispatchQueue=" + emailDispatchQueue + ", emailDispatchRoutingKey=" + emailDispatchRoutingKey
                + ", emailQueueExchange=" + emailQueueExchange + ", emailQueue=" + emailQueue
                + ", emailQueueRoutingKey=" + emailQueueRoutingKey + "]";
    }
    
}
