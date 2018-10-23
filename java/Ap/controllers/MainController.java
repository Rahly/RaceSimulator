package Ap.controllers;

import java.util.Timer;
import java.util.TimerTask;

import Ap.main.Race;
import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.scene.control.Label;

public class MainController {

	public static boolean startFlag = false;
	public Race myrace;

	public String name;

	@FXML
	private Label playerStarted;

	@FXML
	private Label first;

	@FXML
	private Label second;

	@FXML
	private Label third;

	@FXML
	private Label clockLabel;

	@FXML
	public void onClick() throws Exception {
		Timer timer = new Timer();
		timer.schedule(myrace, 0, 40);
	}

	@FXML
	public void initialize() throws Exception {

		myrace = new Race();
		myrace.readPlayersFromFile("http://szgrabowski.kis.p.lodz.pl/zpo18/nazwiska.txt");
		myrace.randPlayers();
		myrace.setPlayersTime();
		updateLayout();

	}

	public void updateLayout() {

		Timer raceTimer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(() -> {

					if (myrace.lastFinished != null)
						playerStarted.setText(myrace.lastFinished + " " + Integer.toString(myrace.lastTime / 60) + ":"
								+ Integer.toString(myrace.lastTime % 60));

					clockLabel.setText(Long.toString(myrace.time / 60) + ":" + Long.toString(myrace.time % 60));
					if (myrace.countOfPlayersFinished == 1)
						first.setText(myrace.firstPlayer.getPlayerName() + ": "
								+ Long.toString(myrace.firstPlayer.getPlayerTime() / 60) + ":"
								+ Long.toString(myrace.firstPlayer.getPlayerTime() % 60));
					else if (myrace.countOfPlayersFinished == 2) {
						first.setText(myrace.firstPlayer.getPlayerName() + ": "
								+ Long.toString(myrace.firstPlayer.getPlayerTime() / 60) + ":"
								+ Long.toString(myrace.firstPlayer.getPlayerTime() % 60));
						second.setText(myrace.secondPlayer.getPlayerName() + ": "
								+ Long.toString(myrace.secondPlayer.getPlayerTime() / 60) + ":"
								+ Long.toString(myrace.secondPlayer.getPlayerTime() % 60));
					} else if (myrace.countOfPlayersFinished >= 3) {
						first.setText(myrace.firstPlayer.getPlayerName() + ": "
								+ Long.toString(myrace.firstPlayer.getPlayerTime() / 60) + ":"
								+ Long.toString(myrace.firstPlayer.getPlayerTime() % 60));
						second.setText(myrace.secondPlayer.getPlayerName() + ": "
								+ Long.toString(myrace.secondPlayer.getPlayerTime() / 60) + ":"
								+ Long.toString(myrace.secondPlayer.getPlayerTime() % 60));
						third.setText(myrace.thirdPlayer.getPlayerName() + ": "
								+ Long.toString(myrace.thirdPlayer.getPlayerTime() / 60) + ":"
								+ Long.toString(myrace.thirdPlayer.getPlayerTime() % 60));
					}
					if (myrace.countOfPlayersFinished == 15) {
						raceTimer.cancel();
					}
				});

			}
		};
		raceTimer.scheduleAtFixedRate(task, 0, 40);
	}

}
