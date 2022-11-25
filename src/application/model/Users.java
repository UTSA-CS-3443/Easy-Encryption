package application.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import application.controller.LoginController;

public class Users {
    private Scanner sc;
    private String line = "", password, line2 = "";
    private static HashMap<String, String> userInfo = new HashMap<>();
    private ArrayList<String> savedKeys = new ArrayList<String>();

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

    public void addKeyAndFile(String allUsers, String user, String fileKey) {
        Path path = Paths.get(allUsers);

        try {
            List<String> allLines = Files.readAllLines(path);
            int lineNum = 0;
            for (String line : allLines) {
                if (line.contains(user)) {
                    line += fileKey;
                    allLines.set(lineNum, line);
                }
                lineNum++;
            }
            Files.write(path, allLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String doesFileExist(String allUsers, String fileName) {
        Path path = Paths.get(allUsers);

        try {
            List<String> allLines = Files.readAllLines(path);

            for (String line : allLines)
                if (line.contains(fileName)) {
                    String[] toks = line.split(",");
                    System.out.println(toks[2] + " " + fileName);
                    for (int i = 0; i < toks.length; i++)
                        if (toks[i].equals(fileName)) {
                            System.out.println(toks[i + 1]);
                            return toks[i + 1];
                        }
                }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void addSavedKeys(String file) {
        try {
            sc = new Scanner(new File(file));

            while (sc.hasNextLine() && (line2 = sc.nextLine()) != null) {

                String[] rowData = line2.split(",");

                for (int i = 0; i < rowData.length; i++) {
                    if (line2.contains(LoginController.currentUser)) {
                        if ((i % 2 != 0) && (i != 1))
                            savedKeys.add(rowData[i]);
                    }
                }
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean validate(String username, String password) {
        if (userInfo.containsKey(password) && userInfo.containsValue(username)) {
            if (userInfo.get(password).equals(username)) {
                return true;
            }
        }
        return false;
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

    /**
     * @return the savedKeys
     */
    public ArrayList<String> getSavedKeys() {
        return savedKeys;
    }

    /**
     * @param savedKeys the savedKeys to set
     */
    public void setSavedKeys(ArrayList<String> savedKeys) {
        this.savedKeys = savedKeys;
    }
}