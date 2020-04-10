package skarlat.dev.courtcounter;


import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Game {
	Team  teamA;
	Team  teamB;
	int     pointsTeamA;
	int     pointsTeamB;
//	Logger logGame;

	public Game(String teamA, String teamB) throws IOException {
		this.teamA = new Team(teamA);
		this.teamB = new Team(teamB);
//		logGame = Logger.getLogger("Basketball match");
		//Handler tmp = new FileHandler("logGame");
//		tmp.setFormatter(new SimpleFormatter());
//		tmp.setFormatter(new Formatter() {
//			@Override
//			public String format(LogRecord record) {
//				return (System.currentTimeMillis() + " : " + record.getMessage());
//			}
//		});
//		logGame.addHandler(tmp);
		startGame();
	}
//	public static Game gameStart(){}
	public void replacing(){}
	public void endGame(){
//		logGame.log(Level.INFO, "****GAME OVER****" +
//				"&&&&SCORE&&&&" +
//				teamA.teamName + ": " + pointsTeamA
//		+ "\n" + teamB.teamName + ": " + pointsTeamB );
	}
	private void startGame(){
//		logGame.log(Level.INFO, "Hello everybody! Today we can see math between "
//				+ teamA.teamName + " and " + teamB.teamName);
//		logGame.log(Level.INFO, "OK!Let's go!!!");
//		logGame.log(Level.INFO, "GAME STARTED");
	}

	public void deletePlayer() {
	}
}
