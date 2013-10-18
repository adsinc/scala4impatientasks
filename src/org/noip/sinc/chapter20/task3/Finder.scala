package org.noip.sinc.chapter20.task3

import scala.actors.{OutputChannel, Actor}
import java.nio.file._
import java.io.File
import scala.util.matching.Regex
import scala.io.Source

/**
 * Актор, осуществляющий обход дерева каталогов.
 */
class FileVisitorActor(val accumulator: Actor) extends Actor {

	var fileCount = 0

	/**
	 * Неявное преобразование для использования функций в fileVisitor
 	 */
	implicit def makeFileVisitor(f: Path => Unit) = new SimpleFileVisitor[Path] {
		override def visitFile(file: Path, attrs: attribute.BasicFileAttributes) = {
			f(file)
			FileVisitResult.CONTINUE
		}
	}

	def startMatching(regex: Regex)(path: Path): Unit = {
		new RegExpMathCountActor(accumulator).start() ! FileVisitorMsg(path.toFile, regex)
		fileCount += 1
	}

	def act() {
		receive {
			// обход дерева каталогов
			case FileVisitorMsg(dir, regex) => Files.walkFileTree(dir.toPath, startMatching(regex) _)
		}
		println("sending " + fileCount)
		accumulator ! FileCount(fileCount)
	}
}

/**
 * Актор, осуществляющий подсчет совпадений с регуляркой в файле.
 */
class RegExpMathCountActor(val accumulator: Actor) extends Actor {
	def act() {
		receive {
			case FileVisitorMsg(file, regex) => accumulator ! Count(regex.findAllIn(Source.fromFile(file).mkString).length)
		}
	}
}

/**
 * Актор, осуществляющий агрегацию результатов.
 */
class AccumulatorActor extends Actor {
	var mainThread: OutputChannel[Any] = null
	var sum = 0
	var received = 0
	var fileCount = -1
	def act() {
		// прием ссылки на главную нить
		receive {
			case AccumulatorRequest() => mainThread = sender
		}

		while(fileCount < 0 || received < fileCount) {
			receive {
				// суммирование результатов
				case Count(c) 			=> sum += c ; received += 1
				case FileCount(count) 	=> fileCount = count
			}
		}

		mainThread ! sum
	}
}

case class AccumulatorRequest()
case class FileVisitorMsg(dir: File, regex: Regex)
case class FileCount(count: Int)
case class Count(count: Int)


/**
 * @author dolgiy
 */
object Finder extends App {
	val dir = new File("src/org/noip/sinc/chapter20/task3/")
	val accum = new AccumulatorActor
	accum.start()
	new FileVisitorActor(accum).start() ! FileVisitorMsg(dir, "a".r)
	val count = accum !? AccumulatorRequest()
	println(count)
}
