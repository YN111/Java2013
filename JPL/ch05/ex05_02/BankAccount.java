import java.util.*;

public class BankAccount {
	private final long number; // 口座番号
	private long balance; // 現在の残高（単位はセント）
	private History bankHistory = new History(); // 履歴
	
	/**
	 * コンストラクタ　口座番号を設定
	 */
	BankAccount(int accountNumber) {
		number = accountNumber;
	}
	

	public static class Permissions {
		public boolean canDeposit, canWithdraw, canClose;
	}

	public class Action {
		private String act;
		private long amount;

		Action(String act, long amount) {
			this.act = act;
			this.amount = amount;
		}

		public String toString() {
			return number + ": " + act + " " + amount;
		}
	}

	public class History {
		private static final int MAX_STORE_NUM = 10;
		private LinkedList<Action> list = new LinkedList<Action>(); // 最後に行われた10個の処理のリスト
		private int storeNum = 0; // 現在格納されている履歴の数
		private int nextNum = 0; // nextメソッドで次に返す履歴の番号

		/**
		 * 履歴の追加
		 */
		public void add(Action a) {
			list.addFirst(a); // 最新の履歴をリストの先頭に入れる
			if (storeNum == MAX_STORE_NUM)
				list.removeLast(); // 最古の履歴を削除
			else
				storeNum++;
		}

		/**
		 * 次の履歴を返す
		 */
		public Action next() {
			if (nextNum < storeNum)
				return list.get(nextNum++);
			else { // 最後の履歴を表示する場合
				nextNum = 0;
				return null;
			}
		}
	}

	/**
	 * 預入
	 */
	public void deposit(long amount) {
		balance += amount;
		bankHistory.add(new Action("deposit", amount));
	}

	/**
	 * 引出
	 */
	public void withdraw(long amount) {
		balance -= amount;
		bankHistory.add(new Action("withdraw", amount));
	}

	/**
	 * 他の口座からお金を移す
	 */
	public void transfer(BankAccount other, long amount) {
		other.withdraw(amount);
		this.deposit(amount);
		bankHistory.add(this.new Action("transfer", amount));
		other.bankHistory.add(other.new Action("transfer", amount));
	}
	
	/**
	 * 履歴取得
	 */
	public History history() {
		return bankHistory;
	}
	
	/**
	 * mainメソッド　テスト用
	 */
	public static void main(String[] args) {
		BankAccount test1 = new BankAccount(1);
		BankAccount test2 = new BankAccount(2);
		
		test1.deposit(10000);
		test2.deposit(10000);
		test1.withdraw(100);
		test1.deposit(200);
		test1.transfer(test2, 300);
		test1.deposit(400);
		test1.withdraw(500);
		test1.deposit(600);
		test1.transfer(test2, 700);
		test1.deposit(800);
		test1.withdraw(900);
		test1.deposit(1000);	// 1個目の履歴が消える
		
		History history1 = test1.history();
		History history2 = test2.history();
		
		// 履歴を表示する
		Action act = history1.next();
		while (act != null) {
			System.out.println(act);
			act = history1.next();
		}
		
		act = history2.next();
		while (act != null) {
			System.out.println(act);
			act = history2.next();
		}
		
	}
}
