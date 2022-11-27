package application.model;

import java.io.Serializable;
import java.util.HashMap;

public class UserData implements Serializable {
    private static final long serialVersionUID = 1L;
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
