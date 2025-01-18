package bg.softuni.mobilelele.config;

import bg.softuni.mobilelele.repository.UserRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource,
                                                       UserRoleRepository userRoleRepository,
                                                       ResourceLoader resourceLoader) {

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);

        if (userRoleRepository.count() == 0) {
            ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
            resourceDatabasePopulator.addScript(resourceLoader.getResource("classpath:data.sql"));
            dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        }

        return dataSourceInitializer;
    }
}