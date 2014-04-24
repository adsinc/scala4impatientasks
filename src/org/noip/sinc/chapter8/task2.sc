class BankAccount(initialBalance: Double) {
  private var balance = initialBalance

  def deposit(amount: Double) = { balance += amount; balance }

  def withdraw(amount: Double) = { balance -= amount; balance }
}
class SavingsAccount(initialInterest: Double, initialBalance: Double) extends BankAccount(initialBalance) {
  private val interest: Double = initialInterest
  var freeOps = 3

  private def depositClean(amount: Double) = super.deposit(amount)

  def earnMonthlyInterest() = {
    freeOps = 3
    depositClean(depositClean(0) * interest / 100)
  }

  override def deposit(amount: Double) = {
    freeOps -= 1
    super.deposit(amount - commission)
  }
  override def withdraw(amount: Double) = {
    freeOps -= 1
    super.withdraw(amount + commission)
  }

  private def commission = if(freeOps >= 0) 0 else 1
}
val ac = new SavingsAccount(10, 100)
ac.deposit(10)
ac.withdraw(10)
ac.deposit(10)
ac.deposit(10)
ac.deposit(10)
ac.earnMonthlyInterest()
ac.withdraw(10)
ac.deposit(10)
ac.withdraw(10)
ac.deposit(10)
ac.withdraw(10)