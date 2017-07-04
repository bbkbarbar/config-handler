package hu.barbar.confighandler;

//import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestSuite;

/**
 * Unit test for JsonConfigHandler class.
 */
public class JsonConfigHandlerTestSuite extends TestSuite {
	
	private static final String CONFIG_JSON = "sample_config.json";
	
	private JsonConfigHandler config = null;
	
	
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
	
	
	// Store values
	
	@Test
	public void storeObjectInJsonConfigUnderSimpleKeyTest() {
		config = new JsonConfigHandler(CONFIG_JSON);
		String data = "data for new key22";
		assertTrue( config.put("new key", data) );
		
		String reCheckedData = config.getString("new key");
		assertNotNull(reCheckedData);
		assertEquals("data for new key22", reCheckedData);
	}
	
	@Test
	public void storeObjectInJsonConfigUnderMultiLevelKeyTest() {
		config = new JsonConfigHandler(CONFIG_JSON);
		String data = "data for multilevel key";
		assertTrue( config.put("new multi level.key1", data) );
		
		String reCheckedData = config.getString("new multi level.key1");
		assertNotNull(reCheckedData);
		assertEquals(data, reCheckedData);
	}
	

}
