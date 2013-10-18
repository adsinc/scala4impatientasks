package org.noip.sinc.chapter18

import scala.collection.mutable.ArrayBuffer
import java.awt.event.ActionEvent

/**
 * Created by sinc on 25.08.13.
 */

//18.13
//a
//trait Listener[E] {
//  def occurred(e: E): Unit
//}
//trait Source[E, L <: Listener[E]] {
//  private val listeners = new ArrayBuffer[L]()
//  def add(l: L) { listeners += l }
//  def remove(l: L) { listeners -= l }
//  def fire(e: E){
//    for(l <- listeners) l.occurred(e)
//  }
//}
//trait ActionListener extends Listener[ActionEvent]
//class Button extends Source[ActionEvent, ActionListener] {
//  def click() {
//    fire(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "click"))
//  }
//}
//b
//trait Event[S] {
//  var source: S = _
//}
//trait Listener[S, E <: Event[S]] {
//  def occurred(e: E): Unit
//}
//trait Source[S, E <: Event[S], L <: Listener[S, E]] {
//  this: S =>
//  private val listeners = new ArrayBuffer[L]()
//  def add(l: L) { listeners += l }
//  def remove(l: L) { listeners -= l }
//  def fire(e: E){
//    e.source = this
//    for(l <- listeners) l.occurred(e)
//  }
//}
//class ButtonEvent extends Event[Button]
//trait ButtonListener extends Listener[Button, ButtonEvent]
//class Button extends Source[Button, ButtonEvent, ButtonListener] {
//    def click() { fire(new ButtonEvent) }
//}
//c
trait ListenerSupport {
  type S <: Source
  type E <: Event
  type L <: Listener

  trait Event {
    var source: S = _
  }

  trait Listener {
    def occurred(e: E): Unit
  }

  trait Source {
    this: S =>
    private val listeners = new ArrayBuffer[L]()
    def add(l: L) { listeners += l }
    def remove(l: L) { listeners -= l }
    def fire(e: E){
      e.source = this
      for(l <- listeners) l.occurred(e)
    }
  }
}
object ButtonModule extends ListenerSupport {
  type S = Button
  type E = ButtonEvent
  type L = ButtonListener

  class ButtonEvent extends Event
  trait ButtonListener extends Listener
  class Button extends Source {
    def click() { fire(new ButtonEvent) }
  }
}

object Main{
  import ButtonModule._

  def main(args: Array[String]) {
    val b = new Button
    b.add(new ButtonListener {
      def occurred(e: ButtonEvent) { println(e) }
    })
    b.click()
  }
}