package demo.data;

import static demo.util.DataSourceUtil.createSource;
import static demo.util.DataSourceUtil.getEnvsProp;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("parse-env")
public class EnvironmentDataConfig {
	
	@Bean(name="test")
	public DataSource createDataSource(){
		return createSource(getEnvsProp());
	}
}
