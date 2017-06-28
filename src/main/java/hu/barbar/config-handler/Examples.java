package hu.barbar.config-handler;

public class Examples {

    public static void main(String[] args){
        System.out.println("Config-handler example.");
        JsonConfigHandler config1 = new JsonConfigHandler("sample_config.json");

        System.out.println("\nparameter1: " + config1.getString("parameter1"));
        System.out.println("\nparameter2: " + config1.getString("parameter2", "Default value"));

        System.out.println("\nparameter_root.sub-parameter2: " + config1.getString("parameter_root.sub-parameter2"));
        System.out.println("\nparameter_root.sub-parameter3: " + config1.getString("parameter_root.sub-parameter3", "Other default value"));
    }

}
