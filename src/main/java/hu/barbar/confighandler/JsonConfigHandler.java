package hu.barbar.confighandler;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import hu.barbar.util.FileHandler;


public class JsonConfigHandler {

	private String configSourceJSONPath = null;

	private JSONObject configJson = null;

	private static boolean configJsonHasBeenRead = false;


	public JsonConfigHandler(String configSourceJSONPath){
		this.configSourceJSONPath = configSourceJSONPath;
	}	


	/*
	 *  LOAD PARAMETERS FROM ANY JSON
	 */
	
	public Object get(String key) {
		return getElementFromJson(key);
	}

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
	public String getString(String key) {
		return (String)getElementFromJson(key);
	}

	public String getString(String key, String defaultValue) {
		String result = getString(key);
		if(result != null){
			return result;
		}
		return defaultValue;
	}
	

	private Object getElementFromJson(String jsonKey){
		if(!configJsonHasBeenRead){
			this.configJson = FileHandler.readJSON(configSourceJSONPath);
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

		if(jsonKey.contains(".")){

			//System.out.println("Key |" + jsonKey + "| contains \".\"");

			String firstPartOfKey = jsonKey.substring(0, jsonKey.indexOf("."));
			String newKey = jsonKey.substring(jsonKey.indexOf(".") + 1);
			//System.out.println("firstPartOfKey: " + firstPartOfKey);
			//System.out.println("newKey: |" + newKey + "|");
			JSONObject newJSONObject = (JSONObject) json.get(firstPartOfKey);
			//System.out.println("NewJsonObject:\n" + newJSONObject);
			return getElementFromJson(newKey, newJSONObject);

		}

		return null;

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



}
