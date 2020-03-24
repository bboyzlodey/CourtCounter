package skarlat.dev.courtcounter;


public class Game {
	Team  teamA;
	Team  teamB;
	int     pointsTeamA;
	int     pointsTeamB;

	public Game(String teamA, String teamB){
		this.teamA = new Team(teamA);
		this.teamB = new Team(teamB);
	}
//	public static Game gameStart(){}
	public void replacing(){}
	public void endGame(){
	}
	public void deletePlayer() {
	}
}
