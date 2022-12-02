package application.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import application.controller.LoginController;

public class Users {
    private static final String DATA_DIR = "data/";
    private static final String LOGIN_DATA = DATA_DIR + "login.csv";

    private HashMap<String, String> keys = new HashMap<>();
    private Scanner sc;
    private String line = "", password, line2 = "";
    private static HashMap<String, String> userInfo;
    private ArrayList<String> savedKeys = new ArrayList<String>();
    private String curUser;
    private String userDataFile;

    public Users() {
        userInfo = new HashMap<>();
        this.loadAllUsers(LOGIN_DATA);
        this.curUser = null;
    }

    /*
    * Reads in user logins from the login.csv file
    * in order to set up the password/username validation
    */
    public void loadAllUsers(String users) {
        try {
            sc = new Scanner(new File(users));
            while (sc.hasNextLine() && (line = sc.nextLine()) != null) {
                String[] rowData = line.split(",");
                userInfo.put(rowData[1], rowData[0]);
                setPassword(rowData[1]);
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    * Checks whether or not a user exists
    */
    public boolean validate(String username, String password) {
        if (userInfo.containsKey(password) && userInfo.containsValue(username)) {
            if (userInfo.get(password).equals(username)) {
                this.curUser = username;
                this.userDataFile = DATA_DIR + username + ".dat";
                boolean userLoaded = this.deserializeUserData();
                return true;
            }
        }
        return false;
    }

    public void serializeUserData() {
        try {
            FileOutputStream userObjectFile = new FileOutputStream(this.userDataFile);

            ObjectOutputStream userObjectStream = new ObjectOutputStream(userObjectFile);

            userObjectStream.writeObject(this.keys);

            userObjectFile.close();
            userObjectStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean deserializeUserData() {
        try {
            FileInputStream userObjectFile;
            try {
                userObjectFile = new FileInputStream(this.userDataFile);
            } catch (FileNotFoundException e) {
                return false;
            }

            ObjectInputStream userObjectStream = new ObjectInputStream(userObjectFile);

            this.keys = (HashMap) userObjectStream.readObject();

            userObjectFile.close();
            userObjectStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;
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

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public HashMap<String, String> getUserinfo() {
        return this.userInfo;
    }
    
    public String getCurUser() {
        return curUser;
    }

    public void setCurUser(String curUser) {
        this.curUser = curUser;
    }
}
