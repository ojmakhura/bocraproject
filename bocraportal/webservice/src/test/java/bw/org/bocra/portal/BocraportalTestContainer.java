package bw.org.bocra.portal;

import org.testcontainers.containers.PostgreSQLContainer;

public class BocraportalTestContainer extends PostgreSQLContainer<BocraportalTestContainer> {
    private static final String IMAGE_VERSION = "postgres:15";
    private static BocraportalTestContainer container;
    
    private BocraportalTestContainer() {
        super(IMAGE_VERSION);
    }

    public static BocraportalTestContainer getInstance() {
        if (container == null) {
            container = new BocraportalTestContainer();
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
