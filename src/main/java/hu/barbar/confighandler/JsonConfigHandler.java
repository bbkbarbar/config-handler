package hu.barbar.confighandler;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import hu.barbar.util.FileHandler;


public class JsonConfigHandler {

	private static final String SEPARATOR_OF_MULTI_LEVEL_KEYS = ".";

	private String configSourceJSONPath = null;

	private JSONObject configJson = null;

	private boolean configJsonHasBeenRead = false;


	public JsonConfigHandler(String configSourceJSONPath){
		this.configSourceJSONPath = configSourceJSONPath;
	}	


	/*
	 *  LOAD PARAMETERS FROM ANY JSON
	 */
	/**
	 * Get Object value for given key in json config.
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		return getElementFromJson(key);
	}
	
	/**
	 * Get Object value for given key in json config with default value.
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public Object get(String key, Object defaultValue) {
		Object result = getElementFromJson(key);
		if(result != null){
			return result;
		}
		return defaultValue;
	}


	/*
	 *  GET STRING
	 */
	/**
	 * Get String value for given key in json config.
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		return (String)getElementFromJson(key);
	}
	
	/**
	 * Get String value for given key in json config with default value.
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public String getString(String key, String defaultValue) {
		String result = getString(key);
		if(result != null){
			return result;
		}
		return defaultValue;
	}
	
	
	/*
	 *  GET LONG
	 */
	/**
	 * Get Long value for given key in json config.
	 * <br> "integer" -like numeric values can read as long.
	 * @param key
	 * @return
	 */
	public Long getLong(String key) {
		return (Long)getElementFromJson(key);
	}
	
	/**
	 * Get Long value for given key in json config with default value.
	 * <br> "integer" -like numeric values can read as long.
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public Long getLong(String key, Long defaultValue) {
		Long result = getLong(key);
		if(result != null){
			return result;
		}
		return defaultValue;
	}
	
	
	/*
	 *  GET BOOL
	 */
	/**
	 * Get boolean value for given key in json config.
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public boolean getBool(String key, boolean defaultValue) {
		Object obj = getElementFromJson(key);
		if(obj == null) {
			//System.out.println("Bool is null: |" + key + "|");
			return defaultValue;
		}
		return (boolean)obj;
	}
	
	

	private Object getElementFromJson(String jsonKey){
		if(!configJsonHasBeenRead){
			readConfigFromJsonFile();
		}
		return this.getElementFromJson(jsonKey, this.configJson);
	}
	
	

	/**
	 * Find the value for a specified key in given JSON object recursively.
	 * @param jsonKey
	 * @param json
	 * @return the value of specified key if it exists
	 * <br> or returns NULL otherwise
	 */
	private Object getElementFromJson(String jsonKey, JSONObject json){

		if(json == null){
			//TODO Log
			//Log.e("Could not read find key in JSON: \"" + jsonKey + "\"; JSON object is null!");
			return null;
		}

		if(json.containsKey(jsonKey)){
			return json.get(jsonKey);
		}

		if(jsonKey.contains(SEPARATOR_OF_MULTI_LEVEL_KEYS)){

			//System.out.println("Key |" + jsonKey + "| contains \".\"");

			String firstPartOfKey = jsonKey.substring(0, jsonKey.indexOf(SEPARATOR_OF_MULTI_LEVEL_KEYS));
			String newKey = jsonKey.substring(jsonKey.indexOf(SEPARATOR_OF_MULTI_LEVEL_KEYS) + 1);
			//System.out.println("firstPartOfKey: " + firstPartOfKey);
			//System.out.println("newKey: |" + newKey + "|");
			JSONObject newJSONObject = (JSONObject) json.get(firstPartOfKey);
			//System.out.println("NewJsonObject:\n" + newJSONObject);
			return getElementFromJson(newKey, newJSONObject);

		}

		return null;

	}

	
	private boolean readConfigFromJsonFile(){
		this.configJson = FileHandler.readJSON(configSourceJSONPath);
		return (this.configJson != null);
	}

	
	private boolean storeConfigToJsonFile(){
		return FileHandler.storeJSON(configSourceJSONPath, configJson);
	}
	
	
	
	/**
	 * Set the filepath of configSource JSON. (e.g.: ../data/config.json)
	 * <br> This JSON file will be used to find the specified key by the getConfig.. methods.
	 * @param configSourceFilePath
	 */
	public void setConfigSourceJSON(String configSourceFilePath) {
		this.configSourceJSONPath = configSourceFilePath;
		if(!FileHandler.fileExists(configSourceFilePath)){
			//TODO
			/*
			Log.e("Setted config source JSON ("
					+ configSourceFilePath
					+ ")is not exists!"); /**/
		}else{
			//TODO
			//Log.i("Config source is set from JSON: " + configSourceFilePath);
		}
	}
	
	
	public boolean isJsonHasBeenRead() {
		return this.configJsonHasBeenRead;
	}


	///////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public boolean put(String key, Object obj){
		if(!configJsonHasBeenRead){
			readConfigFromJsonFile();
		}
		if(!(key.contains(SEPARATOR_OF_MULTI_LEVEL_KEYS))){
			this.configJson.put(key, obj);
			return storeConfigToJsonFile();
		}else{
			System.out.println("Feature not implemented yet: Store values under multi-level key.");
			
			String[] keyArr = key.split(SEPARATOR_OF_MULTI_LEVEL_KEYS);
			
			return false;
		}
		
	}
	
}
