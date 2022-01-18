package impl;
import model.*;
import org.jetbrains.annotations.Nullable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Assignment {

    public static void go() throws Exception {

        boolean status; //max or min
        int limit; // 1 - 100
        String by; // NC, NCS, ND, NDS, NT, NDPC
        String display; // DATE, COUNTRY, CONTINENT
        String path = getPath();; // path to CSV file
        String sPath = getPathS(); // path to sample CSV file

        System.out.println(sPath);
        System.out.println(path);

    }

    public static @Nullable String getPathS() {

        try (InputStream input = new FileInputStream("src/main/resources/persistence.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            String sPath;
            // get the property value and print it out
           return sPath = prop.getProperty("sPath");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @org.jetbrains.annotations.Nullable
    public static String getPath() {

        try (InputStream input = new FileInputStream("src/main/resources/persistence.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            String path;
            // get the property value and print it out
            return path = prop.getProperty("path");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
