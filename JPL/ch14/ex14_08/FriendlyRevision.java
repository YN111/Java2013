public class FriendlyRevision {

	private FriendlyRevision partner;
	private String name;

	public FriendlyRevision(String name) {
		this.name = name;
	}

	public synchronized void hug() {
		synchronized (partner) { // パートナーのロックを獲得
			System.out.println(Thread.currentThread().getName() + " in " + name
					+ ".hug() trying to invoke " + partner.name + ".hugBack()");
			partner.hugBack();
		}
	}

	private synchronized void hugBack() {
		System.out.println(Thread.currentThread().getName() + " in " + name + ".hugBack()");
	}

	public void becomeFriend(FriendlyRevision partner) {
		this.partner = partner;
	}
}
