// --== CS400 File Header Information ==--
// Name: Will Wightman, Bryan Li
// Email: wwightman@wisc.edu, bli378@wisc.edu
// Team: EG
// Role: Data Wranglers
// TA: Keren Chen
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes

/**
 * This class serves as a data structure for a Player object with the specified fields gathered from ESPN
 */
public class Player {
    // Data fields relevant to a player
    private String name;
    private String teamName;
    private String position;
    private int jerseyNum;
    private int age;
    private String height;
    private int weight;
    private String exp;
    private String college;

    /**
     * This is the only constructor for the Player data structure, every field must be included
     * 
     * @param name the name of this player
     * @param teamName the team the player plays for
     * @param position position on the team
     * @param jerseyNum the player's jersey number
     * @param age the current age of the player
     * @param height the heigh of the player
     * @param weight the weight of the player in pounds
     * @param exp number of years of experience
     * @param college the college the player attended
     * @return an player object for this player
     */
    public Player (String name, String teamName, String position, int jerseyNum,
            int age, String height, int weight, String exp, String college) {
        this.name = name;
        this.teamName = teamName;
        this.position = position;
        this.jerseyNum = jerseyNum;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.exp = exp;
        this.college = college;
    }

    /**
     * This method returns this player's team name for hashing purposes
     * @return this player's team name
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * This method returns this player's jersey number for hashing purposes
     * @return this player's jersey number
     */
    public int getJerseyNum() {
        return jerseyNum;
    }

    /**
     * This method overides the Object class's toString method in oder to print relevant information
     * about this player
     * @return a string based representation of this player
     */
    public String toString() {
        String output = "";
        output += "Name: " + name;
        output += "\nTeam Name: " + teamName;
        output += "\nPosition: " + position;
        output += "\nJersey Number: " + jerseyNum;
        output += "\nAge: " + age;
        output += "\nHeight: " + height;
        output += "\nWeight: " + weight;
        output += "\nExperience (Years): " + exp;
        return output;
    }
}
