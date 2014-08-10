package demo.data;

import static demo.util.DataSourceUtil.createSource;
import static demo.util.DataSourceUtil.getBindingUserDefinedProp;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("user-defined")
public class ParseUserDefinedDataConfig {
	
	@Bean(name="test")
	public DataSource createDataSource(){
		return createSource(getBindingUserDefinedProp(System.getenv("VCAP_SERVICES")));
	}
}
