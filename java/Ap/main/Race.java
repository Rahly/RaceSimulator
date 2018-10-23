package Ap.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
//
import java.util.HashMap;
import java.util.HashSet;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.TimerTask;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Race extends TimerTask {

	Logger logger = Logger.getLogger("MyLog");
	FileHandler file;

	public HashMap<Double, String> listOfStartedPlayer = new HashMap<>();
	public Queue<Players> PlayersResultsPriorityQueue = new PriorityQueue<>();

	public List<String> listOfAllPlayersNames = new ArrayList<>();
	public List<Players> listOfRandomPlayers = new ArrayList<>();

	public int counter = 0;
	public long time = 0;
	public String lastFinished = null;
	public int lastTime;
	public int countOfPlayersFinished = 0;

	public Players firstPlayer = new Players("");
	public Players secondPlayer = new Players("");
	public Players thirdPlayer = new Players("");

	public List<String> readPlayersFromFile(String filePath) throws Exception {

		// file = new FileHandler("D:/logger");

		try {

			// This block configure the logger with handler and formatter
			file = new FileHandler("D://logger/file.log");
			logger.addHandler(file);
			SimpleFormatter formatter = new SimpleFormatter();
			file.setFormatter(formatter);

			// the following statement is used to log any messages
			logger.info("My first log");

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String inputLine = "";
		URL oracle = new URL(filePath);
		BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream(), "UTF8"));

		while ((inputLine = in.readLine()) != null)
			listOfAllPlayersNames.add(inputLine);
		in.close();

		return listOfAllPlayersNames;
	}

	public List<Players> randPlayers() {

		int random = 0;
		Set<String> listOfPlayers = new HashSet<>();
		Random rnd = new Random();
		while (listOfPlayers.size() < 15) {
			random = rnd.nextInt(listOfAllPlayersNames.size());
			listOfPlayers.add(listOfAllPlayersNames.get(random));
		}

		for (String name : listOfPlayers) {
			listOfRandomPlayers.add(new Players(name));
		}

		return listOfRandomPlayers;
	}

	public void setPlayersTime() {
		for (int i = 0; i < listOfRandomPlayers.size(); i++) {
			listOfRandomPlayers.get(i).randPlayersTime();

		}
	}

	@Override
	public void run() {

		int i = 0;
		if (counter < 15) {

			if (counter * 60 == time) {

				logger.info("Wystartowa³ zawodnik " + listOfRandomPlayers.get(counter).getPlayerName());
				counter++;

			}

		}

		for (i = 0; i < counter; i++) {

			if (listOfRandomPlayers.get(i).getPlayerTime() + i * 60 == time) {

				lastFinished = listOfRandomPlayers.get(i).getPlayerName();
				lastTime = listOfRandomPlayers.get(i).getPlayerTime();
				logger.info("Dojecha³ do mety zawodnik " + listOfRandomPlayers.get(i).getPlayerName());
				PlayersResultsPriorityQueue.add(listOfRandomPlayers.get(i));
				countOfPlayersFinished++;
				getBestPlayers();
			}

		}

		time++;

	}

	public void getBestPlayers() {

		if (countOfPlayersFinished == 1)
			firstPlayer = PlayersResultsPriorityQueue.peek();

		else if (countOfPlayersFinished == 2) {
			firstPlayer = PlayersResultsPriorityQueue.poll();
			secondPlayer = PlayersResultsPriorityQueue.peek();
			PlayersResultsPriorityQueue.add(firstPlayer);
		} else {
			firstPlayer = PlayersResultsPriorityQueue.poll();
			secondPlayer = PlayersResultsPriorityQueue.poll();
			thirdPlayer = PlayersResultsPriorityQueue.peek();
			PlayersResultsPriorityQueue.add(firstPlayer);
			PlayersResultsPriorityQueue.add(secondPlayer);
		}
	}

}
