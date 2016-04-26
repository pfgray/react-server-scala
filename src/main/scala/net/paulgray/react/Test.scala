package net.paulgray.react

import java.io.File
import java.net.URL
import akka.pattern.ask

import akka.actor.ActorSystem
import akka.util.Timeout
import com.typesafe.jse._
import com.typesafe.jse.Engine.JsExecutionResult
import scala.concurrent.duration._
import scala.collection.immutable
import scala.concurrent.ExecutionContext.Implicits.global


/**
  * Created by paul on 4/24/16.
  */
object Test {

  implicit val system = ActorSystem("jse-system")
  implicit val timeoutA = Timeout(5.seconds)

  system.scheduler.scheduleOnce(7.seconds) {
    system.shutdown()
    System.exit(1)
  }

  def main(args: Array[String]): Unit = {
    val engine = system.actorOf(Node.props(), "engine")
    val test: URL = Test.getClass.getResource("test.js")
    //val resource = Test.getClass.getResourceAsStream("test.js")
    println(s"got:$test")
    val f = new File(test.toURI)


    // /usr/local/lib/node_modules/



    for (
      result <- (engine ? Engine.ExecuteJs(f, immutable.Seq("999"), timeoutA.duration, -1, LocalEngine.nodePathEnv(immutable.Seq("/usr/local/lib/node_modules")))).mapTo[JsExecutionResult]
    ) yield {
      println(s"output\n======\n${new String(result.output.toArray, "UTF-8")}\n")
      println(s"error\n=====\n${new String(result.error.toArray, "UTF-8")}\n")

      try {
        system.shutdown()
        System.exit(0)
      } catch {
        case _: Throwable =>
      }
    }
  }

}
