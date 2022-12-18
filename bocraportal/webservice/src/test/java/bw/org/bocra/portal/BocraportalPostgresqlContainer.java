package bw.org.bocra.portal;

import org.testcontainers.containers.PostgreSQLContainer;

public class BocraportalPostgresqlContainer extends PostgreSQLContainer<BocraportalPostgresqlContainer> {
    private static final String IMAGE_VERSION = "postgres:15";
    private static BocraportalPostgresqlContainer container;
    
    private BocraportalPostgresqlContainer() {
        super(IMAGE_VERSION);
    }

    public static BocraportalPostgresqlContainer getInstance() {
        if (container == null) {
            container = new BocraportalPostgresqlContainer();
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
