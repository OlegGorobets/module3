package ua.gorobetso.module.utils;

import org.flywaydb.core.Flyway;

public class FlywayMigration {
    private static final Flyway flyway = Flyway.configure()
            .dataSource("jdbc:postgresql://ec2-44-205-112-253.compute-1.amazonaws.com:5432/dfofn8jefl1u07", "mshfngocxeqktw", "37ef35fa349adb0a63cd85610e7f80eeb1a5b2b27a0121b92b049865027ac5eb")
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
