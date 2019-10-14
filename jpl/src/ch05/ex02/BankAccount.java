package ch05.ex02;

import java.util.Objects;

import ch03.ex10.LinkedList;

public class BankAccount {
	private static final int MAX_RECODE_NUM = 10;

	private long number;
	private long balance;
	private Action lastAct;
	private final History history = new History();

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
		private LinkedList actions = new LinkedList(null);

		public Action next() {

			if (Objects.isNull(actions.getNext())) {
				return null;
			}
			Action action = (Action) actions.getObj();
			actions = actions.getNext();
			return action;
		}
	}

	public static void main(String[] args) {
		BankAccount bankAccount = new BankAccount();

		bankAccount.deposit(10000);
		bankAccount.deposit(10000);
		bankAccount.deposit(10000);
		bankAccount.deposit(5000);
		bankAccount.deposit(5000);
		bankAccount.withdraw(5000);
		bankAccount.withdraw(4000);
		bankAccount.withdraw(3000);
		bankAccount.withdraw(2000);
		bankAccount.withdraw(1000);
		bankAccount.deposit(5000);
		bankAccount.deposit(5000);
		bankAccount.withdraw(5000);
		bankAccount.withdraw(4000);
		bankAccount.withdraw(3000);

		for (int i = 0; i < 15; i++) {
			System.out.println(bankAccount.history().next());
		}
	}

	public History history() {
		return history;
	}

	public void deposit(long amount) {
		balance += amount;
		lastAct = new Action("deposit", amount);
		history().actions = new LinkedList(lastAct, history().actions);
		if (history().actions.count() > MAX_RECODE_NUM + 1) {
			history().actions.remove();
		}
	}

	public void withdraw(long amount) {
		balance -= amount;
		lastAct = new Action("withdraw", amount);
		history().actions = new LinkedList(lastAct, history().actions);
		if (history().actions.count() > MAX_RECODE_NUM + 1) {
			history().actions.remove();
		}
	}

}
