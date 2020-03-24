package skarlat.dev.courtcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {
	Game    currentGame;
	TextView a;
	TextView b;
	Button  startGame;
	Button  tmp;
	String  send;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

		startGame = (Button) findViewById(R.id.new_game);
		startGame.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startGame.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in));
				newGame(v);
				startGame.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out));
			}
		});
	}
	public void newGame(View view){
		a = (TextView) findViewById(R.id.name_team_a);
		b = (TextView) findViewById(R.id.name_team_b);
		String t1 = a.getText().toString();
		String t2 = b.getText().toString();

		setContentView(R.layout.activity_main);

		currentGame = new Game(t1, t2);

		a = (TextView) findViewById(R.id.team_a);
		b = (TextView) findViewById(R.id.team_b);

		a.setText(t1);
		b.setText(t2);

		LinearLayout linearLayout;
		linearLayout = (LinearLayout) findViewById(R.id.game_layout);
		linearLayout.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out));
		linearLayout.setVisibility(View.VISIBLE);


		tmp = (Button) findViewById(R.id.one_points_team_a);
		tmp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				currentGame.teamA.goal(1);
				refreshTable();
			}
		});
		tmp = (Button) findViewById(R.id.two_points_team_a);
		tmp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				currentGame.teamA.goal(2);
				refreshTable();
			}
		});
		tmp = (Button) findViewById(R.id.three_points_team_a);
		tmp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				currentGame.teamA.goal(3);
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
				refreshTable();
			}
		});
		tmp = (Button) findViewById(R.id.two_points_team_b);
		tmp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				currentGame.teamB.goal(2);
				refreshTable();
			}
		});
		tmp = (Button) findViewById(R.id.three_points_team_b);
		tmp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				currentGame.teamB.goal(3);
				refreshTable();
			}
		});


		a.setText(currentGame.teamA.teamName);
		b.setText(currentGame.teamB.teamName);

		a = (TextView) findViewById(R.id.score_team_a);
		b = (TextView) findViewById(R.id.score_team_b);
		linearLayout.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in));

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
		super.onBackPressed();
	}
	protected void sendResult(){
	}
}
