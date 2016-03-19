package org.noip.sinc.chapter20.task6

import scala.actors.Actor

object ManyThreads extends App {
  val counter = new Actor {
    var ids = Set[Long]()
    def act() {
      while(true) {
        receive {
          case id: Long => ids += id; println(ids.size)
        }
      }
    }
  }
  counter.start()

  val actors1 = 1 to 100 map (_ => new Actor {
    def act() {
      while(true) {
        receive {
          case "Hello" => counter ! Thread.currentThread().getId
        }
      }
    }
  })
  actors1 foreach (_.start())
  actors1 foreach (_ ! "Hello")

//  val actors2 = 1 to 100 map (_ => new Actor {
//    def act() {
//      loop {
//        react {
//          case "Hello" => counter ! Thread.currentThread().getId
//        }
//      }
//    }
//  })
//  actors2 foreach (_.start())
//  actors2 foreach (_ ! "Hello")
}
