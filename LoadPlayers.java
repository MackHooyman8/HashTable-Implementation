// --== CS400 File Header Information ==--
// Name: Will Wightman
// Email: wwightman@wisc.edu
// Team: EG
// Role: Data Wrangler 2
// TA: Keren Chen
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This object loads the data from a text file into an ArrayList of Players
 * Other key information is extracted from the data file including the source the data came from
 */
public class LoadPlayers {
    private ArrayList<Player>  allPlayers = null; // The hashtable where all players are stored
    private String source = null; // A string indicating the source of the data

    /**
     * constructor for the LoadPlayers class
     * This class all player in the NFL from a table formatted as a tab-segrated text file
     * 
     * The rows are formatted as follows...
     *  - The first line of the document contains sourcing information
     *  - The second line of the document contains the headers
     *  - The third line and on contain tha data
     *
     * The format of the collumns are as follows...
     * NAME - (String) Name of player
     * TEAM - (String) Name of team
     * JERSEY - (int) Jersey number
     * POS - (String) Position in team
     * AGE - (int) Age of player
     * HT - (String) Height in feet and inches
     * WT - (int) Weight in pounds
     * EXP - (String) Experience in years, could have 'R' for reserve
     * College - (String) The college attended, if any
     *
     * @param fileLocation
     * @return the LoadPlayers object containing all players in the data file
     */
    public LoadPlayers(String fileLocation) {
        allPlayers = null; // Clears the new HashTableMap
        Scanner reader = null; // The scanner reading the file
        File file = null; // The file object being read from
        
        String data = null; // The line the player's data is on
        String[] tokens = null; // The array of string tokens the data is stored in
        
        try {
            allPlayers = new ArrayList<Player>(); // Creates a new hashtable
            file = new File(fileLocation); // Generates a new file object
            
            // Throw an exception if the file cannot be found
            if (file == null) throw new FileNotFoundException("Unable to Find File");
            
            // Creates a scanner
            reader = new Scanner(file); // Creates a new scanner to read the file

            // Saves the source of the data (Line 1 of document)
            if (reader.hasNextLine()) source = reader.nextLine().trim();
            
            // Clears header from buffer (Line 2 of document)
            if (reader.hasNextLine()) reader.nextLine();

            // Loads data into Player objects (Line 3+ of document)
            while (reader.hasNextLine()) {
                // Gets the next line and tokenizes the data
                data = reader.nextLine();
                tokens = data.split("\t");
                
                // Cleans up the "Weight" portion of the tokenized data
                tokens[6] = tokens[6].split(" ")[0];

                // Gives those without jersey numbers the number "0" temporarily
                if (tokens[3].equals("")) tokens[3] = "0";

                // Creates a new player based off data and adds it to total roster
                if (tokens.length == 9) allPlayers.add(new Player(tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]), tokens[5], Integer.parseInt(tokens[6]), tokens[7], tokens[8]));
            }
        
        } catch (FileNotFoundException e) {
            allPlayers =  null; // If an error occurred when loading data, data array set to null
            
        } catch (Exception e) {
            System.out.println("An unexpected error occured" + e);
            allPlayers = null; // If an error occurred when loading data, data array set to null
            
        } finally {
            // Final maintainence operations
            if (reader != null) {
                reader.close();
            }
        }
    }

    /**
     * This method returns the HashTable containing all the data read from file
     * @return ArrayList<Player>  containing all players
     */
    public ArrayList<Player> getPlayers() {
        return allPlayers;
    }
    
    /**
     * This method returns the source of the data in the given file listed at the top of the data sheet
     * @return the source of the data listed at the top of the data file as a string
     */
    public String getSource() {
        return source;
    }
}
