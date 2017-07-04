package hu.barbar.confighandler;

import org.json.simple.JSONObject;

import hu.barbar.util.FileHandler;

public class Examples {

	private static final String CONFIG_JSON = "sample_config.json";
	private static final String CONFIG_JSON2 = "sample_config2.json";
	
    public static void main(String[] args){
        System.out.println("Config-handler example.");
        JsonConfigHandler config1 = new JsonConfigHandler(CONFIG_JSON);

        System.out.println("\nparameter1: " + config1.getString("parameter1"));
        System.out.println("\nparameter2: " + config1.getString("parameter2", "Default value"));
        System.out.println("\nboolVal: " + config1.getBool("boolVal", false));
        

        System.out.println("\nparameter_root.sub-parameter2: " + config1.getString("parameter_root.sub-parameter2"));
        System.out.println("\nparameter_root.sub-parameter3: " + config1.getString("parameter_root.sub-parameter3", "Other default value"));
        
        
        JSONObject json = FileHandler.readJSON(CONFIG_JSON);
        json.put("boolVal", false);
        FileHandler.storeJSON(CONFIG_JSON2, json);
        
    }

}
