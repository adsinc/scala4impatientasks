package org.noip.sinc.chapter8.task1

/**
 * Created with IntelliJ IDEA.
 * User: alexey
 * Date: 03.06.13
 * Time: 14:41
 */
class BankAccount(initialBalance: Double) {
	private var balance = initialBalance
	def deposit(amount: Double) = { balance += amount; balance }
	def withdraw(amount: Double) = { balance -= amount; balance }
}

class CheckingAccount(initialBalance: Double) extends BankAccount(initialBalance) {
	private val operationInterest = 1
	override def deposit(amount: Double): Double = { super.deposit(amount * (1 - operationInterest * 0.01)) }
	override def withdraw(amount: Double): Double = { super.withdraw(amount * (1 + operationInterest * 0.01)) }
}

class SavingAccount(initialBalance: Double) extends BankAccount(initialBalance) {
	private var freeOperations = 3
	val monthInterest = 10
	val operationInterest = 1
	def earnMonthlyInterest = { freeOperations = 3; super.deposit(super.deposit(0) * 0.01 * monthInterest) }
	override def deposit(amount: Double): Double = {
		freeOperations -= 1
		super.deposit(if(freeOperations >= 0) amount else (amount * (1 - operationInterest * 0.01)))
	}
	override def withdraw(amount: Double): Double = {
		freeOperations -= 1
		super.withdraw(if(freeOperations >= 0) amount else (amount * (1 + operationInterest * 0.01)))
	}
}


object AccountTest extends App {
	val bankAccount = new BankAccount(100)
	println(bankAccount.deposit(10))
	println(	bankAccount.withdraw(10))
	val checkingAccount = new CheckingAccount(100)
	println(checkingAccount.deposit(10)  )
	println(checkingAccount.withdraw(10))
	val savingAccount = new SavingAccount(100)
	println(savingAccount.deposit(10))
	println(savingAccount.deposit(10))
	println(savingAccount.withdraw(20))
	println(savingAccount.deposit(10))
	println(savingAccount.deposit(10))
	println(savingAccount.withdraw(20))
	println("Earn interest")
	println(savingAccount.earnMonthlyInterest)
	println(savingAccount.deposit(10))
	println(savingAccount.deposit(10))
	println(savingAccount.withdraw(20))
	println(savingAccount.deposit(10))
	println(savingAccount.deposit(10))
	println(savingAccount.withdraw(20))
}