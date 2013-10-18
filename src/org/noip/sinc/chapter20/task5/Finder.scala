package org.noip.sinc.chapter20.task5

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
		accumulator ! FileCount(fileCount)
	}
}

/**
 * Актор, осуществляющий подсчет совпадений с регуляркой в файле.
 */
class RegExpMathCountActor(val accumulator: Actor) extends Actor {
	def act() {
		receive {
			case FileVisitorMsg(file, regex) => accumulator ! Results(file.getAbsolutePath, regex.findAllIn(Source.fromFile(file, "cp1251").mkString).toSet[String])
		}
	}
}

/**
 * Актор, осуществляющий агрегацию результатов.
 */
class AccumulatorActor extends Actor {
	var mainThread: OutputChannel[Any] = null
	var searched = Map[String, Set[String]]()
	var received = 0
	var fileCount = -1
	def act() {
		// прием ссылки на главную нить
		receive {
			case AccumulatorRequest() => mainThread = sender
		}

		while(fileCount < 0 || received < fileCount) {
			receive {
				case Results(fileName, results)	=> {
					results.foreach(founded => { searched += founded -> (searched.getOrElse(founded, Set[String]()) + fileName) })
					received += 1
				}
				case FileCount(count) => fileCount = count
			}
		}
		mainThread ! searched
	}
}

case class AccumulatorRequest()

case class FileVisitorMsg(dir: File, regex: Regex)

case class FileCount(count: Int)

case class Results(fileName: String, results: Set[String])

/**
 * @author dolgiy
 */
object Finder extends App {

//	val regex = "[0-9]+".r
	val regex = "c*s".r

	val dir = new File("/home/sinc/ProjectRep/imus/branches/imus-1.5/ascug-maket/ascug-quickinput/src/main/java")

	//////////////////////////////////////////
	val start = System.currentTimeMillis()

	val accum = new AccumulatorActor
	accum.start()
	new FileVisitorActor(accum).start() ! FileVisitorMsg(dir, regex)
	val searched = (accum !? AccumulatorRequest()).asInstanceOf[Map[String, Set[String]]]

	val time = System.currentTimeMillis() - start

	val str = searched.map(v => "%s in files:\n\t%s".format(v._1, v._2.mkString("\n\t"))).mkString("\n")
	println(str)
	//////////////////////////////////////////


	println("\nStupid no thread")

	/**
	 * Неявное преобразование для использования функций в fileVisitor
	 */
	implicit def makeFileVisitor(f: Path => Unit) = new SimpleFileVisitor[Path] {
		override def visitFile(file: Path, attrs: attribute.BasicFileAttributes) = {
			f(file)
			FileVisitResult.CONTINUE
		}
	}

	val start1 = System.currentTimeMillis()

	var searched1 = Map[String, Set[String]]()
	Files.walkFileTree(dir.toPath, (path: Path) => {
		val fileName = path.toString
		val results = regex.findAllIn(Source.fromFile(path.toFile, "cp1251").mkString).toSet[String]
		results.foreach(founded => { searched1 += founded -> (searched1.getOrElse(founded, Set[String]()) + fileName) })
	})


	val time1 = System.currentTimeMillis() - start1

	val str1 = searched1.map(v => "%s in files:\n\t%s".format(v._1, v._2.mkString("\n\t"))).mkString("\n")
	println(str1)

	println("parallel time=%d ms".format(time))
	println("serial time=%d ms".format(time1))
}
