package application.model;

import java.util.HashMap;

public class UserData {
    private static HashMap<String, String> keys = new HashMap<>();
    public UserData(String username) {
        
    }
    
    public void addKey(String keyName, String key) {
        keys.put(keyName, key);
    }
    
    public String getKey(String keyName) {
        return keys.get(keyName);
    }
    
    public HashMap<String, String> getKeys() {
        return this.keys;
    }
}
