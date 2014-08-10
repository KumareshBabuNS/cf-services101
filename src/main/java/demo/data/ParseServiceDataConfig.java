package demo.data;

import static demo.util.DataSourceUtil.createSource;
import static demo.util.DataSourceUtil.getBindingVCAPProp;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("parse-vcap")
public class ParseServiceDataConfig {

	@Bean(name="test")
	public DataSource createDataSource(){
		return createSource(getBindingVCAPProp(System.getenv("VCAP_SERVICES")));
	}
}
