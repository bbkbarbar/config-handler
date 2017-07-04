package hu.barbar.confighandler;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestSuite;

/**
 * Unit test for JsonConfigHandler class.
 */
public class JsonConfigHandlerTestSuite extends TestSuite {
	
	private static final String CONFIG_JSON = "sample_config.json";
	
	private static JsonConfigHandler config = null;
	
	
	@Before
	public void before() {
		config = new JsonConfigHandler(CONFIG_JSON);
	}
	
	
	@Test
	public void readSingleKeyFromJsonTest() {
        assertEquals("value1", config.getString("parameter1"));
	}
	
	@Test
	public void readNestedKeyFromJsonTest() {
        assertEquals("sub-value1", config.getString("parameter_root.sub-parameter1"));
	}
	

}
