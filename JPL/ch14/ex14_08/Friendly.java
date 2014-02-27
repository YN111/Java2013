/*
 * �f�b�g���b�N�̕p�x
 * yield�Ăяo���Ȃ� �F 20��4��
 * yield�Ăяo������  �F 20��6��
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
