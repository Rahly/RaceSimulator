package Ap.main;
import java.util.Random;

public class Players implements Comparable<Players>{

	static int odchylenieStandardowe = 40;
	static int srednia = 300;
	String playerName;
	
	public String getPlayerName() {
		return playerName;
	}

	int playerTime;

	public int getPlayerTime() {
		return playerTime;
	}

	public void setPlayerTime(int playerTime) {
		this.playerTime = playerTime;
	}

	public Players(String name) {

		this.playerName = name;
	}

	public int randPlayersTime() {

		Random rnd = new Random();
		playerTime = (int) (rnd.nextGaussian() * 40 + 300);

		if (playerTime < 250) {

			playerTime = 250;

		} else if (playerTime > 370) {

			playerTime = 370;
		}
		return playerTime;
	}

	@Override
	public int compareTo(Players o) {

		if(this.getPlayerTime() > o.getPlayerTime()) return 1;
		else if(this.getPlayerTime() == o.getPlayerTime()) return 0;
		else return -1;

	}
}
