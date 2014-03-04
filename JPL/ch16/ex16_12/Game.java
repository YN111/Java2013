public class Game {

	private static final String[] players = { "Player1", "Player2" };

	private boolean winFlag;
	private static int count = 0;

	public static void main(String[] args) {
		String name;
		while ((name = getNextPlayer()) != null) {
			try {
				PlayerLoader loader = new PlayerLoader();
				Class<? extends Player> classOf = loader.loadClass(name).asSubclass(Player.class);
				Player player = classOf.newInstance();
				Game game = new Game();
				player.play(game);
				game.reportScore(name);
			} catch (Exception e) {
				reportException(name, e);
			}
		}
	}

	public void setWinFlag(boolean win) {
		winFlag = win;
	}

	private void reportScore(String name) {
		if (winFlag) {
			System.out.println("WIN!!! " + name);
		} else {
			System.out.println("Lose... " + name);
		}
	}

	private static void reportException(String name, Exception e) {
		System.out.println("catch exception\nPlayer Name: " + name + "\n" + e.toString());
	}

	private static String getNextPlayer() {
		if (count < players.length) {
			return players[count++];
		} else {
			return null;
		}
	}
}
