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

    private Scanner sc;
    private String line = "", password, line2 = "";
    private static HashMap<String, String> userInfo;
    private ArrayList<String> savedKeys = new ArrayList<String>();
    private UserData userData;
    private String curUser;
    private String userDataFile;

    public Users() {
        userInfo = new HashMap<>();
        this.loadAllUsers(LOGIN_DATA);
        this.curUser = null;
    }

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

    public boolean validate(String username, String password) {
        if (userInfo.containsKey(password) && userInfo.containsValue(username)) {
            if (userInfo.get(password).equals(username)) {
                this.curUser = username;
                this.userDataFile = DATA_DIR + username + ".dat";
                boolean userLoaded = this.deserializeUserData();
                if (!userLoaded) {
                    this.userData = new UserData(this.curUser);
                }
                return true;
            }
        }
        return false;
    }
    
    public void serializeUserData() {
        try {
            FileOutputStream userObjectFile = new FileOutputStream(this.userDataFile);

            ObjectOutputStream userObjectStream = new ObjectOutputStream(userObjectFile);

            userObjectStream.writeObject(this.userData);

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

            this.userData = (UserData) userObjectStream.readObject();

            userObjectFile.close();
            userObjectStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;
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
    
    public UserData getUserData() {
        return this.userData;
    }

//    /**
//     * @return the savedKeys
//     */
//    public ArrayList<String> getSavedKeys() {
//        return savedKeys;
//    }

    /**
     * @param savedKeys the savedKeys to set
     */
//    public void setSavedKeys(ArrayList<String> savedKeys) {
//        this.savedKeys = savedKeys;
//    }
//
//    public void addKeyAndFile(String allUsers, String user, String fileKey) {
//        Path path = Paths.get(allUsers);
//
//        try {
//            List<String> allLines = Files.readAllLines(path);
//            int lineNum = 0;
//            for (String line : allLines) {
//                if (line.contains(user)) {
//                    line += fileKey;
//                    allLines.set(lineNum, line);
//                }
//                lineNum++;
//            }
//            Files.write(path, allLines);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String doesFileExist(String allUsers, String fileName) {
//        Path path = Paths.get(allUsers);
//
//        try {
//            List<String> allLines = Files.readAllLines(path);
//
//            for (String line : allLines)
//                if (line.contains(fileName)) {
//                    String[] toks = line.split(",");
//                    for (int i = 0; i < toks.length; i++)
//                        if (toks[i].equals(fileName)) {
//                            return toks[i + 1];
//                        }
//                }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    public void addSavedKeys(String file) {
//        try {
//            sc = new Scanner(new File(file));
//
//            while (sc.hasNextLine() && (line2 = sc.nextLine()) != null) {
//
//                String[] rowData = line2.split(",");
//
//                for (int i = 0; i < rowData.length; i++) {
//                    if (line2.contains(LoginController.currentUser)) {
//                        if ((i % 2 != 0) && (i != 1))
//                            savedKeys.add(rowData[i]);
//                    }
//                }
//            }
//            sc.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public String getCurUser() {
        return curUser;
    }

    public void setCurUser(String curUser) {
        this.curUser = curUser;
    }
}