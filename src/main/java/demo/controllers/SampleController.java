package demo.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController implements EnvironmentAware{

	@Autowired
	@Qualifier("test")
	DataSource dataSource;
	
	Environment environment; 
	
	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Hello World!";
	}

	@RequestMapping(value = "/env")
	@ResponseBody
	public Map<String, String> showEnvironment() {
		return System.getenv();
	}

	@RequestMapping(value = "/test")
	@ResponseBody
	public String list() throws SQLException {
		Connection connection = dataSource.getConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery("select name from dummy");
		rs.next();
		return rs.getString(1);
	}
	
	@RequestMapping(value = "/profiles")
	@ResponseBody
	public String[] profiles() throws SQLException {
		return environment.getActiveProfiles();
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

}
