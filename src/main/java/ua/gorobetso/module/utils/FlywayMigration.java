package ua.gorobetso.module.utils;

import org.flywaydb.core.Flyway;

public class FlywayMigration {
    private static final Flyway flyway = Flyway.configure()
            .dataSource("jdbc:postgresql://localhost:5432/module3", "postgres", "root")
            .baselineOnMigrate(true)
            .locations("db/migration")
            .cleanDisabled(false)
            .load();
    public void migrateDatabase() {
        flyway.migrate();
    }

    public void cleanDatabase() {
        flyway.clean();
    }
}
