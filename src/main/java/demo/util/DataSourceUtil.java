package demo.util;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.json.JSONObject;

public class DataSourceUtil {
	public static Map<String, String> getBindingVCAPProp(String vcap) {
		Map<String, String> map = new HashMap<String, String>();

		JSONObject json = new JSONObject(vcap);

		JSONObject credentials = json.getJSONArray("p-mysql").getJSONObject(0)
				.getJSONObject("credentials");

		map.put("url",
				"jdbc:mysql://" + credentials.getString("hostname") + ":"
						+ credentials.getInt("port") + "/"
						+ credentials.getString("name"));
		map.put("username", credentials.getString("username"));
		map.put("password", credentials.getString("password"));
		map.put("driverClassName", "com.mysql.jdbc.Driver");

		return map;
	}

	public static Map<String, String> getBindingUserDefinedProp(String vcap) {
		Map<String, String> map = new HashMap<String, String>();

		JSONObject json = new JSONObject(vcap);

		JSONObject credentials = json.getJSONArray("user-provided").getJSONObject(0)
				.getJSONObject("credentials");

		map.put("url", credentials.getString("url"));
		map.put("username", credentials.getString("username"));
		map.put("password", credentials.getString("password"));
		map.put("driverClassName", "com.mysql.jdbc.Driver");

		return map;
	}
	
	public static Map<String, String> getEnvsProp() {
		Map<String, String> map = new HashMap<String, String>();

		map.put("url", System.getenv("URL"));
		map.put("username", System.getenv("USERNAME"));
		map.put("password", System.getenv("PASSWORD"));
		map.put("driverClassName", "com.mysql.jdbc.Driver");

		return map;
	}	
	
	public static DataSource createSource(Map<String, String> map){
		BasicDataSource ds = new BasicDataSource();
		ds.setUrl(map.get("url"));
		ds.setUsername(map.get("username"));
		ds.setPassword(map.get("password"));
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		return ds;
	}
}
