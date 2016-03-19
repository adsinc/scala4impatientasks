package org.noip.sinc.actors

import akka.actor.{ActorRef, Actor}
import akka.event.LoggingReceive
import org.noip.sinc.actors.BankAccount.{Failed, Done, Withdraw, Deposit}

class BankAccount extends Actor {
  var balance = BigInt(0)

  def receive: Receive = LoggingReceive {
    case Deposit(amount) =>
      balance += amount
      sender ! Done
    case Withdraw(amount) if amount <= balance =>
      balance -= amount
      sender ! Done
    case _ => sender ! Failed
  }
}

object BankAccount {

  case class Deposit(amount: BigInt) {
    require(amount > 0)
  }

  case class Withdraw(amount: BigInt) {
    require(amount > 0)
  }

  case object Done

  case object Failed

}