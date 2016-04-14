package com.lambdacons.euler.p001

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

import scala.concurrent.Await


/**
  * Created by eric on 3/30/16.
  */
object Problem001Actors {

  case class StartMsg(below : Int)
  case class SetFilterMsg(multiple: Array[Int])
  case class StartProcessingMsg(below: Int)
  case class ProcessNumberMsg(number: Int, multiples : Int*)
  case class NumberToAddMsg(number: Int)
  case class RunningTotalMsg(total: Int)
  case class CloseProcessingMsg()

  class RoutingActor(sourcingRef: ActorRef) extends Actor {
    def receive = {
      case StartMsg(below) =>
        println("Received StartMsg")
        sourcingRef ! new StartProcessingMsg(below)

      case _ => println("Routing Actor Unknown message")
    }
  }

  class SourcingActor(filterRef: ActorRef) extends Actor {
    def receive = {

      case StartProcessingMsg(below) =>
        println("Source Start Processing")
        Stream.from(1, 1)
          .takeWhile(_ < below)
          .foreach(filterRef ! new ProcessNumberMsg(_))

      case _ => println("Source Unknown message ")
    }
  }

  class FilteringActor(summingActor: ActorRef) extends Actor {
    var multiples = Array(2, 5)
    def receive = {
      case SetFilterMsg(multiple) =>
        println("Set Filter message " + multiple)
        this.multiples = multiple
        sender ! "Filter Set"

      case ProcessNumberMsg(number) =>
        if(multiples.exists(number % _ == 0)) {
          summingActor ! new NumberToAddMsg(number)
        }

      case _ => println("Filter Unknown message ")
    }
  }

  class SummingActor() extends Actor {
    private var runningTotal = 0
    def receive = {
      case NumberToAddMsg(number) =>
        runningTotal += number
        println("Running Total: " + runningTotal)

      case _ => println("Summing Unknown message ")
    }
  }

  object AkkaProblem1 extends App {

    import akka.pattern.ask
    import akka.util.Timeout
    import scala.concurrent.duration._

    override def main(args: Array[String]): Unit = {
      implicit val timeout = Timeout(5 seconds)

      val system = ActorSystem("ActorSystem")
      val summer = system.actorOf(Props(new SummingActor()))
      val filter = system.actorOf(Props(new FilteringActor(summer)))
      val source = system.actorOf(Props(new SourcingActor(filter)))
      val router = system.actorOf(Props(new RoutingActor(source)))

      val future = filter ? new SetFilterMsg(Array(3,5))
      val result = Await.result(future, 1 second).asInstanceOf[String]
      println(result)

      router ! new StartMsg(1000)
    }
  }

}
