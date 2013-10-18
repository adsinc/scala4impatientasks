import java.io.{PrintWriter, FileInputStream, File}
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.{Files, FileVisitResult, SimpleFileVisitor, Path}
import scala.io.Source
//
// 1
//val source = Source.fromFile("text.txt", "UTF-8")
//val sourceNumbers = Source.fromFile("numbers.txt", "UTF-8")
//val lineIterator = source.getLines
//for(line <- lineIterator) println(line)
//val lines = source.getLines.toArray.mkString("\n");
//val str = source.mkString
// 2
//for(ch <- source) println(ch)
//val iterator = source.buffered
//while(iterator.hasNext) {
//	if(iterator.head == 'h')
//		println(iterator.next())
//}
// 3
//val tokens = sourceNumbers.mkString.split("\\s+")
//val numbers1 = for(c <- tokens) yield c.toDouble
//val numbers2 = tokens.map(_.toDouble)
//println("How old are you ?")
//val age = readInt()
//source.close()
//sourceNumbers.close()
// 4
//val source1 = Source.fromURL("http://horstmann.com", "UTF-8").mkString
//val source2 = Source.fromString("Hello world").mkString("|")
//val source3 = Source.stdin
//5
//val file = new File("text.txt")
//val in = new FileInputStream(file)
//val bytes = new Array[Byte](file.length().toInt)
//in.read(bytes)
//bytes
//in.close()
//6
//val out = new PrintWriter("out.txt")
//for(i <- 1 to 100) out.println(i)
//out.close()
//7
//def subdirs(dir: File): Iterator[File] = {
//	val children = dir.listFiles().filter(_.isDirectory)
//	children.toIterator ++ children.toIterator.flatMap(subdirs _)
//}
//for(d <- subdirs(new File("/home/alexey/prog/fnplite/fnplweb/trunk"))) println(d.getName)
//implicit def makeFileVisitor(f: (Path) => Unit) = new SimpleFileVisitor[Path] {
//	override def visitFile(p: Path, attrs: BasicFileAttributes) = {
//		f(p)
//		FileVisitResult.CONTINUE
//	}
//}
//val dir = new File("/home/alexey/prog/fnplite/fnplweb/trunk")
//Files.walkFileTree(dir.toPath, (f: Path) => println(f))
//10
val numPattern = "[0-9]+".r
for(matchString <- numPattern.findAllIn("99 bottles, 98 bottles")) println(matchString)



numPattern.findAllIn("99 bottles, 98 bottles").toArray
numPattern.findFirstIn("99 bottles, 98 bottles")









