package skarlat.dev.courtcounter;

import java.io.Serializable;

public class Team implements Serializable {
	String  teamName;
	int     points;
	public      Team(){
		teamName = "null";
		points = 0;
	}
	public      Team(String teamName){
		this.teamName = teamName;
		points = 0;
	}
	public int  goal(int typeGoal){
		return (points += typeGoal);
	}
}
