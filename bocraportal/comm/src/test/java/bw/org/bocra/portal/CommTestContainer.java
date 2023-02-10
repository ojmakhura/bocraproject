package bw.org.bocra.portal;

import org.testcontainers.containers.PostgreSQLContainer;

public class CommTestContainer extends PostgreSQLContainer<CommTestContainer> {
    private static final String IMAGE_VERSION = "postgres:15";
    private static CommTestContainer container;
    
    private CommTestContainer() {
        super(IMAGE_VERSION);
    }

    public static CommTestContainer getInstance() {
        if (container == null) {
            container = new CommTestContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}
