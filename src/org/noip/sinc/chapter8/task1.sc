class BankAccount(initialBalance: Double) {
  private var balance = initialBalance
  def deposit(amount: Double) = { balance += amount; balance }
  def withdraw(amount: Double) = { balance -= amount; balance }
}
class CheckingAccount(initialBalance: Double) extends BankAccount(initialBalance) {
  override def deposit(amount: Double) = super.deposit(amount - 1)
  override def withdraw(amount: Double) = super.withdraw(amount + 1)
}
val ac = new CheckingAccount(100)
ac.deposit(10)
ac.deposit(10)
ac.deposit(10)
ac.withdraw(10)
ac.withdraw(10)
ac.withdraw(10)