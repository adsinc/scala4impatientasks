class BankAccount {

	private var money: Long = 0

	def balance: Double = money / 100

	def deposit(m: Double): Double = {
		money = money + (m * 100).toLong
		balance
	}

	def withdraw(m: Double): Double = {
		money = money - math.min((m * 100).toLong, money)
		balance
	}
}

val account = new BankAccount
account.deposit(100)
account.balance
for(i <- 1 to 10) println(account.withdraw(20))
account.balance
