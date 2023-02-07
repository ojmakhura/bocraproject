package bw.org.bocra.portal.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitProperties {

    private final String host;
    private final int port;
    private final String username;
    private final String password;
    private final String exchange;
    private final String queue;
    private final String routingkey;

    public RabbitProperties(String host, int port, String username, String password, String exchange, String queue,
            String routingkey) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.exchange = exchange;
        this.queue = queue;
        this.routingkey = routingkey;
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

    public String getExchange() {
        return exchange;
    }

    public String getQueue() {
        return queue;
    }

    public String getRoutingkey() {
        return routingkey;
    }

    @Override
    public String toString() {
        return "RabbitProperties [host=" + host + ", port=" + port + ", username=" + username + ", password=" + password
                + ", exchange=" + exchange + ", queue=" + queue + ", routingkey=" + routingkey + "]";
    }
}
