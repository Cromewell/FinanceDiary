package com.cromewell.financediary.utils;


import com.cromewell.financediary.Account;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.net.URISyntaxException;

/**
 * Created by Jo on 11.07.2016.
 * @author Cromewell
 */
public class Utils {

    private static File jarLocation = getJarLocation();

    /**
     *
     * @param text      A String which will be converted to a only number int
     * @return          The numbers in the String (in a whole number)
     */
    public static int stringToInt(String text){
        String[] textTokens = text.replaceAll("[A-z]+", "").split("\\n");
        int number = 0;
        try {
            number = Integer.parseInt(textTokens[0]);
        }catch(NumberFormatException nfe){
            System.out.println("Only numbers.");
        }
        return number;
    }

    /**
     *
     * @param acc       The used account
     */
    public static void saveToFile(Account acc){
        FileChooser save = new FileChooser();
        if(jarLocation != null) {
            save.setInitialDirectory(jarLocation.getParentFile());
        }
        save.setInitialFileName(acc.getName()); //inital filename = username
        //save.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Data", "*.txt"));//
        File destination = save.showSaveDialog(new Stage()); //file  used to write in the data
        if(destination!= null){
            try{
                BufferedWriter writer = new BufferedWriter(new FileWriter(destination));
                writer.write(String.valueOf(acc.getMoneyProperty()));
                writer.flush();
                writer.close();
            } catch (IOException e) {
                System.out.println("Error while saving to file "+destination.getName());
            }
        }
    }

    /**
     *
     * @param acc       The used account
     */
    public static void loadFromFile(Account acc){
        FileChooser load = new FileChooser();
        if(jarLocation != null) {
            load.setInitialDirectory(jarLocation.getParentFile());
        }
        File src = load.showOpenDialog(new Stage());
        if(src != null){
            String line = "";
            try {
                BufferedReader reader = new BufferedReader(new FileReader(src));
                line = reader.readLine();
                reader.close();
            } catch (IOException e) {
                System.out.println("Error while reading from file "+src.getName());
                System.exit(1);
            }
            acc.setMoneyProperty(getSumFromString(line));
        }
    }

    /**
     *
     * @param line       A string which will be converted to an int
     */
    private static int getSumFromString(String line) { //no need for exception handling
        try {
            return Integer.parseInt(line);
        }catch (NumberFormatException nfe){
            System.out.println("Error while converting filedata to int.  "+line);
            System.exit(1);
        }
        return 0;
    }

    /**
     * Tries to get the location of the jar file.
     * @return  returns the jar location.
     */
    private static File getJarLocation(){
        try{
            return new File(Utils.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
        } catch (URISyntaxException e) {
            System.out.println("Error while trying to get jar location.");
            e.printStackTrace();
        }
        return null;
    }
}
