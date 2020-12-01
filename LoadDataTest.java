// --== CS400 File Header Information ==--
// Name: Will Wightman
// Email: wwightman@wisc.edu
// Team: EG
// Role: Data Wrangler 1
// TA: Keren Chen
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class LoadDataTest {
    public static void main(String [] args) {
        LoadPlayers allPlayers = new LoadPlayers("CompleteRoster_TeamAndJersey.txt");
        ArrayList<Player> totalRoster = allPlayers.getPlayers();

        if (totalRoster == null) System.out.println("Unable to find file.");
        else System.out.println("An ArrayLit of Players was returned of size " + totalRoster.size());

        int putInHash = 0;
        HashTableMap<String, Player>  map = new HashTableMap<String, Player>();
        Player iterator = null;
        ArrayList<Player> unplacables = new ArrayList<Player>();

        for (int i = 0; i < totalRoster.size(); ++i) {
            iterator = totalRoster.get(i); 
            if (map.put(iterator.getTeamName() + iterator.getJerseyNum(), iterator)) putInHash++;
            else unplacables.add(iterator);
        }

        if (putInHash != totalRoster.size()) {
            System.out.println("Error: " + (totalRoster.size() - putInHash) + " players could not be hashed");
            System.out.println("Conflicts occured between these players...\n");
            for (int i = 0; i < unplacables.size(); ++i) {
                System.out.println(unplacables.get(i) + "\n was blocked by...\n");
                System.out.println(map.get(unplacables.get(i).getTeamName() + unplacables.get(i).getJerseyNum()) + "\n"); 
            }
        }
        
    }
}
