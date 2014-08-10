package demo.util;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class DataSourceUtilTest {

	public static String vcap_env = "{\"p-mysql\":[{\"name\":\"demo-service\",\"label\":\"p-mysql\",\"tags\":[\"mysql\",\"relational\"],\"plan\":\"100mb-dev\",\"credentials\":{\"hostname\":\"172.16.0.54\",\"port\":3306,\"name\":\"cf_18b79bd4_c84c_4361_b989_b812a4f8f0dd\",\"username\":\"a1uuu9KNol0qiXzf\",\"password\":\"qJIBrTsev5XNHR7N\",\"uri\":\"mysql://a1uuu9KNol0qiXzf:qJIBrTsev5XNHR7N@172.16.0.54:3306/cf_18b79bd4_c84c_4361_b989_b812a4f8f0dd?reconnect=true\",\"jdbcUrl\":\"jdbc:mysql://a1uuu9KNol0qiXzf:qJIBrTsev5XNHR7N@172.16.0.54:3306/cf_18b79bd4_c84c_4361_b989_b812a4f8f0dd\"}}],\"user-provided\":[{\"name\":\"mysql-user-defined\",\"label\":\"user-provided\",\"tags\":[],\"credentials\":{\"password\":\"qJIBrTsev5XNHR7N\",\"url\":\"jdbc:mysql://172.16.0.54:3306/cf_18b79bd4_c84c_4361_b989_b812a4f8f0dd\",\"username\":\"a1uuu9KNol0qiXzf\"},\"syslog_drain_url\":\"\"}]}";

	@Test
	public void test() {
		Map<String, String> prop = DataSourceUtil.getBindingVCAPProp(vcap_env);

		Assert.assertEquals(
				"jdbc:mysql://172.16.0.54:3306/cf_18b79bd4_c84c_4361_b989_b812a4f8f0dd",
				prop.get("url"));
		Assert.assertEquals("a1uuu9KNol0qiXzf", prop.get("username"));
		Assert.assertEquals("qJIBrTsev5XNHR7N", prop.get("password"));
		Assert.assertEquals("com.mysql.jdbc.Driver",
				prop.get("driverClassName"));
		
		
		Map<String, String> prop2 = DataSourceUtil.getBindingUserDefinedProp(vcap_env);
		Assert.assertEquals(
				"jdbc:mysql://172.16.0.54:3306/cf_18b79bd4_c84c_4361_b989_b812a4f8f0dd",
				prop2.get("url"));
		Assert.assertEquals("a1uuu9KNol0qiXzf", prop2.get("username"));
		Assert.assertEquals("qJIBrTsev5XNHR7N", prop2.get("password"));
		Assert.assertEquals("com.mysql.jdbc.Driver",
				prop2.get("driverClassName"));

	}

}
