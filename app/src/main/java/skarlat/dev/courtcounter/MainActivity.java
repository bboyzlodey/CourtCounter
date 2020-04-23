package skarlat.dev.courtcounter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {
	Game    currentGame;
	TextView a;
	TextView b;
	Button  startGame;
	Button  tmp;
	String  send;
	String teamsPath;
	String team1;
	String team2;

	@RequiresApi(api = Build.VERSION_CODES.O)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
		}

		if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
		}

		if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
		}


		teamsPath = "teams";
		File file = new File(getApplicationContext().getFilesDir(), teamsPath);
//		File file = new File(teamsPath); //этот метод не будет работать
		setContentView(R.layout.activity_start);
		a = (TextView) findViewById(R.id.name_team_a);
		b = (TextView) findViewById(R.id.name_team_b);
		if (true)
		{
			if(!(file.exists() && file.isFile())) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					FileOutputStream fOut = openFileOutput(teamsPath, Context.MODE_WORLD_READABLE);
					OutputStreamWriter osw = new OutputStreamWriter(fOut);
					ObjectOutputStream object = new ObjectOutputStream(fOut);
// записываем строку в файл
					osw.write(teamsPath);
					object.writeObject(new Team("POPPING"));
					object.writeObject(new Team("HIP-HOP"));
					object.flush();
					object.close();
					/* проверяем, что все действительно записалось и закрываем файл */
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else{
			try{FileInputStream fOut = openFileInput(teamsPath);
				ObjectInputStream object = new ObjectInputStream(fOut);
// записываем строку в файл
				Team firstTeam = (Team) object.readObject();
				Team secondTeam = (Team) object.readObject();
				team2 = firstTeam.teamName;
				team1 = secondTeam.teamName;

//				object.flush();
				object.close();
				/* проверяем, что все действительно записалось и закрываем файл */
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}}
//			try {
//				file.createNewFile();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			writeToFile();
//			try {
//							Team [] teams = readFromFile();
//							team2 = teams[0].teamName;
//							team1 = teams[1].teamName;
//						} catch (IOException e) {
//							e.printStackTrace();
//						}

		}
		else
		{
			try{FileInputStream fOut = openFileInput(teamsPath);
				ObjectInputStream object = new ObjectInputStream(fOut);
// записываем строку в файл
				Team firstTeam = (Team) object.readObject();
				Team secondTeam = (Team) object.readObject();
				team2 = firstTeam.teamName;
				team1 = secondTeam.teamName;

//				object.flush();
				object.close();
				/* проверяем, что все действительно записалось и закрываем файл */
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		startGame = (Button) findViewById(R.id.new_game);
		startGame.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startGame.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in));
				try {
					newGame(v);
				} catch (IOException e) {
					e.printStackTrace();
				}
				startGame.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out));
			}
		});
	}
	public void newGame(View view) throws IOException {
		String t1 = a.getText().toString();
		String t2 = b.getText().toString();

		setContentView(R.layout.activity_main);

		currentGame = new Game(team1, team2);

		a = (TextView) findViewById(R.id.team_a);
		b = (TextView) findViewById(R.id.team_b);

		a.setText(currentGame.teamA.teamName);
		b.setText(currentGame.teamA.teamName);

		LinearLayout linearLayout;
		linearLayout = (LinearLayout) findViewById(R.id.game_layout);
		linearLayout.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out));
		linearLayout.setVisibility(View.VISIBLE);


		tmp = (Button) findViewById(R.id.one_points_team_a);
		tmp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				currentGame.teamA.goal(1);
//				currentGame.logGame.log(Level.INFO, "Team A +1 points");
				refreshTable();
			}
		});
		tmp = (Button) findViewById(R.id.two_points_team_a);
		tmp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				currentGame.teamA.goal(2);
//				currentGame.logGame.log(Level.INFO, "Team A +2 points");
				refreshTable();
			}
		});
		tmp = (Button) findViewById(R.id.three_points_team_a);
		tmp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				currentGame.teamA.goal(3);
//				currentGame.logGame.log(Level.INFO, "Team A +3 points");
				refreshTable();
			}
		});
		/*
			For team B
		 */
		tmp = (Button) findViewById(R.id.one_points_team_b);
		tmp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				currentGame.teamB.goal(1);
//				currentGame.logGame.log(Level.INFO, "Team B +1 points");
				refreshTable();
			}
		});
		tmp = (Button) findViewById(R.id.two_points_team_b);
		tmp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				currentGame.teamB.goal(2);
//				currentGame.logGame.log(Level.INFO, "Team B +2 points");
				refreshTable();
			}
		});
		tmp = (Button) findViewById(R.id.three_points_team_b);
		tmp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				currentGame.teamB.goal(3);
//				currentGame.logGame.log(Level.INFO, "Team B +3 points");
				refreshTable();
			}
		});


		a.setText(team1);
		b.setText(team2);

		a = (TextView) findViewById(R.id.score_team_a);
		b = (TextView) findViewById(R.id.score_team_b);
		linearLayout.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in));

	}
	//@RequiresApi(api = Build.VERSION_CODES.O)
	public Team[] readFromFile() throws IOException {
		Team [] teams = new Team[2];
		try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(teamsPath))){
			teams[0] = (Team) objectInputStream.readObject();
			teams[1] = (Team) objectInputStream.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return teams;
	}
	//@RequiresApi(api = Build.VERSION_CODES.O)
	public void writeToFile(){
		try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(teamsPath))){
			objectOutputStream.writeObject(new Team("POPPING"));
			objectOutputStream.writeObject(new Team("WACKING"));
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	public void refreshTable(){
		if (parseInt(a.getText().toString()) != currentGame.teamA.points) {
			a.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out));
			a.setText(Integer.toString(currentGame.teamA.points));
			a.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in));
		}
		else {
			b.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out));
			b.setText(Integer.toString(currentGame.teamB.points));
			b.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce));
		}
	}
	public void endGame(View v){
		send = "skarlat-denis@mail.ru";
//		currentGame.endGame();
		super.onBackPressed();
	}
	protected void sendResult(){
	}
}
