package ch05.ex02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BankAccountTet {

	@Test
	void test() {
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

		assertEquals("0: withdraw 3000", bankAccount.history().next().toString());
		assertEquals("0: withdraw 4000", bankAccount.history().next().toString());
		assertEquals("0: withdraw 5000", bankAccount.history().next().toString());
		assertEquals("0: deposit 5000", bankAccount.history().next().toString());
		assertEquals("0: deposit 5000", bankAccount.history().next().toString());
		assertEquals("0: withdraw 1000", bankAccount.history().next().toString());
		assertEquals("0: withdraw 2000", bankAccount.history().next().toString());
		assertEquals("0: withdraw 3000", bankAccount.history().next().toString());
		assertEquals("0: withdraw 4000", bankAccount.history().next().toString());
		assertEquals("0: withdraw 5000", bankAccount.history().next().toString());
		assertEquals(null, bankAccount.history().next());

	}
}
