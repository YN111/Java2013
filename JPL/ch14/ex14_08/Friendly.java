/*
 * デットロックの頻度
 * yield呼び出しなし ： 20回中4回
 * yield呼び出しあり  ： 20回中6回
 */

public class Friendly {
	private Friendly partner;
	private String name;

	public Friendly(String name) {
		this.name = name;
	}

	public synchronized void hug() {
		System.out.println(Thread.currentThread().getName() + " in " + name
				+ ".hug() trying to invoke " + partner.name + ".hugBack()");
		partner.hugBack();
		Thread.yield();
	}

	private synchronized void hugBack() {
		System.out.println(Thread.currentThread().getName() + " in " + name + ".hugBack()");
	}

	public void becomeFriend(Friendly partner) {
		this.partner = partner;
	}
}
