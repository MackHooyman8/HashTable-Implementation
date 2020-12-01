// --== CS400 File Header Information ==--
// Name: Zi Hern Wong
// Email: zwong@wisc.edu
// Team: EG
// Role: Test Engineer 1
// TA: Keren Chen
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes
import java.io.FileNotFoundException;
import java.util.ArrayList;
/**
 * @author wongz
 *Tests LoadPlayers class as well as the BackEndMethods Class
 */
public class TestProjectOne {
	
	public TestProjectOne() {
	}
	//TestLoadingData
	/**
	 * makes sure using a bad file leads to allPlayers being null
	 * @return boolean true if all players is null false if something is in all players given a invalid file location
	 */
	public static boolean TestBadFile(){
		String badFile = "this_is_fake";//invalid file location
		LoadPlayers load = new LoadPlayers(badFile);//should throw a FileNotFoundException
		if(load.getPlayers()!=null) {//if FNFE is caught, allPlayers should be set to null
			return false;
		}
		else return true;
	
	}
	/**
	 * makes sure loading the data with a valid file allows to retrieve the right source
	 * @return boolean true if getting the source leads to the ESPN data false if otherwise
	 */
	public static boolean TestLoadGetSource() {
		LoadPlayers load = new LoadPlayers("CompleteRoster_TeamAndJersey.txt");
		if(load.getSource().contentEquals("NFL data from ESPN - 09/25/2020 - https://www.espn.com/nfl/teams")) {//makes sure the source is correct
			return true;
		}
		else {
			return false;
		}
		
	}
	/**
	 * makes sure loading the data with a valid file does not result in Arraylist being null
	 * @return boolean true if loading the file results in a not empty ArrayList
	 */
	public static boolean TestGoodFile() {
		LoadPlayers load = new LoadPlayers("CompleteRoster_TeamAndJersey.txt");
		ArrayList<Player>  players = load.getPlayers();
		if(players.isEmpty()) {//if the arraylist is empty, that means nothing was loaded
			return false;
		}
		else{
			return true;
		}
	}
	
	/**
	 * Combines all 3 loadData tests 
	 * @return boolean true if TestBadFile, TestGoodFile, and TestLoadGetSource all pass
	 */
	public static boolean finalLoadData(){
		if(TestBadFile()&&(TestGoodFile())&&(TestLoadGetSource())) {
			return true;
		}
		else return false;
	}	
	
	
	
	
	//TestBackEndMethods
	/**
	 * Tests that createCompleteRoster returns true which means it successfully loaded the data
	 * @return boolean true if createCompleteRoster returns true and false otherwise
	 */
	public static boolean TestCreateCompleteRoster() {
		BackEndMethods back = new BackEndMethods();
		boolean part1 =back.createCompleteRoster();//made sure the roster is completed successfully
		if(part1==false) {
			return false;//automatically returns false if that is not the case
		}
		else return true;
		}
	/**
	 * Tests the TestFindPLayerInMaster method
	 * @return boolean true if the player was found in the Master Roster and false otherwise
	 */
	public static boolean TestFindPlayerInMaster() {
		BackEndMethods back = new BackEndMethods();
		boolean part1 =back.createCompleteRoster();//creates a list of every single person in the NFL
		String team = back.getTeams()[0];//team is Arizona Cardinals
		Player methodCall = back.findPlayerInMaster(98, team);
		if(methodCall==null) {
			return false;
		}
		else return true;
	}
	/**
	 * Tests the AddPlayer method by adding a player
	 * @return boolean true if the player's name is in the list and the size of the user's roster is 1
	 */
	public static boolean TestAddPlayer() {
		BackEndMethods back = new BackEndMethods();
		boolean part1 =back.createCompleteRoster();//creates a list of every single person in the NFL
		String team = back.getTeams()[0];
		back.addPlayer(98, team);
		String remainingName = "Peters";
		if(!back.listMyPlayers().contains(remainingName)) {//should be the name added into the list
			return false;
		}
		if(back.getMyNflRoster().size()!=1) {
			
		return false;
		}
		else return true;
		}
	/**
	 * Tests the Remove method by adding 2 players and removing one
	 * @return boolean true if the player's is the only one on the list and the size of the user's roster is 1
	 */
	public static boolean TestRemovePlayer() {
		BackEndMethods back = new BackEndMethods();
		boolean part1 =back.createCompleteRoster();//creates a list of every single person in the NFL
		String team = back.getTeams()[0];
		back.addPlayer(98, team);
		back.addPlayer(35, team);
		back.remove(35, team);
		String removedName = "Riley";
		if(back.listMyPlayers().contains(removedName)) {//should be the name that was just removed
			return false;
		}
		String remainingName = "Peters";
		if(!back.listMyPlayers().contains(remainingName)) {//should be the name remaining
			return false;
		}
		if(back.getMyNflRoster().size()!=1) {
			
		return false;
	}
		else return true;
}
	/**
	 * Tests the FindPlayerInMyNflRoster method by adding a player and searching for it
	 * @return boolean true if the player's name is in the list and the size of the user's roster is 1
	 */
	public static boolean TestFindPlayerInMyNflRoster() {
		BackEndMethods back = new BackEndMethods();
		boolean part1 =back.createCompleteRoster();//creates a list of every single person in the NFL
		String team = back.getTeams()[0];
		back.addPlayer(98, team);
		String methodReturn =back.findPlayerInMyNflRoster(98, team).toString();
		if(!methodReturn.contains("Peters")) {
			return false;
		}
		if(back.getMyNflRoster().size()!=1) {
			
		return false;
	}
		else return true;
}
	/**
	 * Tests the ContainsPlayerInMyNflRoster method which should
	 * @return boolean true if the player's name is in the list and false if it isn't
	 */
	public static boolean TestContainsPlayerInMyNflRoster() {
		BackEndMethods back = new BackEndMethods();
		boolean part1 =back.createCompleteRoster();//creates a list of every single person in the NFL
		String team = back.getTeams()[0];
		back.addPlayer(98, team);
		if(!back.ContainsPlayerInMyNflRoster(team,"98")) {
			return false;
		}
		if(back.ContainsPlayerInMyNflRoster(team,"35")) {
			return false;
		}
		return true;
				
	}
	/**
	 * Tests the CreateKey method which should return teamname + jerseynumber
	 * @return boolean true if the key created is expected ad false if not
	 */
	public static boolean TestCreateKey() {
		BackEndMethods back = new BackEndMethods();
		boolean part1 =back.createCompleteRoster();//creates a list of every single person in the NFL
		String expected = back.getTeams()[0]+98;
		if(back.createKey(back.getTeams()[0], "98").equals(expected)) {
			return true;
		}
		return false;
				
	}
	/**
	 * Tests the getSource method much like the loadgetsource method it checks the backend returns the right source
	 * @return boolean true if getting the source leads to the ESPN data false if otherwise
	 */
	public static boolean TestGetSource() {
		BackEndMethods back = new BackEndMethods();
		if(back.getSource().contentEquals("NFL data from ESPN - 09/25/2020 - https://www.espn.com/nfl/teams")) {//makes sure the source is correct
			return true;
		}
		else {
			return false;
		}
		
	}
	/**
	 * Combines all 8 backend tests 
	 * @return boolean true if TestCreateCompleteRoster, TestFindPlayerInMaster, TestAddPlayer, TestRemovePlayer,
	 * TestFindPlayerInMyNflRoster, TestContainsPlayerInMyNflRoster, TestCreateKey, TestGetSource all pass
	 */
	public static boolean finalBackEndTest(){
		if(TestCreateCompleteRoster()&&TestFindPlayerInMaster()&&TestAddPlayer()&&TestRemovePlayer()&&TestFindPlayerInMyNflRoster()
			&&TestContainsPlayerInMyNflRoster()&&TestCreateKey()&&TestGetSource()) {
			return true;
		}
		else return false;
	}

	public static void main(String[] args) {
		System.out.println("Load Works: " + finalLoadData());
		System.out.println("Back End Works: " + finalBackEndTest());

	}

}
