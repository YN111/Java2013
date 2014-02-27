public class Main {
	public static void main(String[] args) {
		final FriendlyRevision jareth = new FriendlyRevision("jareth");
		final FriendlyRevision cory = new FriendlyRevision("cory");

		jareth.becomeFriend(cory);
		cory.becomeFriend(jareth);

		new Thread(new Runnable() {
			@Override
			public void run() {
				jareth.hug();
			}
		}, "Thread1").start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				cory.hug();
			}
		}, "Thread2").start();
	}
}
