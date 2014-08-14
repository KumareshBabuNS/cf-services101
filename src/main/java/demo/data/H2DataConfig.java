package demo.data;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("default")
public class H2DataConfig{
    @Bean(name="test")
    public DataSource dataSource() throws NamingException {
    	Context initContext = new InitialContext();
    	Context envContext  = (Context)initContext.lookup("java:/comp/env");
    	DataSource ds = (DataSource)envContext.lookup("jdbc/mysql");
    	return ds;
    }
}