package pe.edu.cibertec.proyecto_daw.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HikariCpConfig {

    @Value("${DB_CLINICA_URL}")
    private String dbClinicaUrl;
    @Value("${DB_CLINICA_USER}")
    private String dbClinicaUser;
    @Value("${DB_CLINICA_PASS}")
    private String dbClinicaPass;
    @Value("${DB_CLINICA_DRIVER}")
    private String dbClinicaDriver;

    @Bean
    public HikariDataSource hikariDataSource() {

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(dbClinicaUrl);
        config.setUsername(dbClinicaUser);
        config.setPassword(dbClinicaPass);
        config.setDriverClassName(dbClinicaDriver);

        config.setMaximumPoolSize(20);
        config.setMinimumIdle(5);
        config.setIdleTimeout(300000);
        config.setConnectionTimeout(30000);

        System.out.println("###### HikariCP initialized ######");
        return new HikariDataSource(config);

    }
}

