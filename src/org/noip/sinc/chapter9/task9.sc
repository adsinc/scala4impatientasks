import java.io.File
val dir = "/home/dolgiy/scalaProgs/scala4impatientasks"
def subDirs(dir: File): Iterator[File] = {
  val children = dir.listFiles().filter(_.isDirectory)
  children.toIterator ++ children.toIterator.flatMap(subDirs)
}
subDirs(new File(dir)).flatMap(_.listFiles.filter(!_.isDirectory)).count(_.getName.endsWith(".class"))