package threading.Chapter10;

public class AccountTransfer {
	private static final Object tieBreaker = new Object();
	private static class Account {
		Integer getBalance() { return new Integer(2);}
		void debit(int amount) {} 
		void credit(int amount) {} 
	}
	
	/**
	 * Bad causes deadlock
	 * @param from
	 * @param to
	 * @param amount
	 */
	public static void transferMoney(Account from, Account to, int amount) {
		synchronized(from) {
			synchronized(to) {
				from.debit(amount);
				to.credit(amount);
			}
		}
	}
	
	public static void transferMoney1(final Account from, final Account to, final int amount) {
		class Helper {
			public void transfer() { 
				from.debit(amount);
				to.credit(amount);
			}
		}
		int fromHash = System.identityHashCode(from);
		int toHash = System.identityHashCode(to);
		if (fromHash < toHash) {
			synchronized(from) {
				synchronized(to) {
					new Helper().transfer();
				}
			}
		} else if (toHash < fromHash) {
			synchronized(to) {
				synchronized(from) {
					new Helper().transfer();
				}
			}
		} else {
			synchronized(tieBreaker) {
				synchronized(from) {
					synchronized(to) {
						new Helper().transfer();
					}
				}
			}
		}
	}
}
