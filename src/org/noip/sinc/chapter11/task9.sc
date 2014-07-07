import java.io.File
class RichFile(val file: File)

object RichFile {
  def unapply(file: File) = {
    val pth = file.getPath
    val (dir, fullName) = pth.splitAt(pth lastIndexOf '/')
    val (name, ext) = fullName.splitAt(fullName lastIndexOf '.')
    Option(dir, name drop 1 , ext drop 1)
  }
}

val f = new File("/home/sinc/hello.txt")

val RichFile(path, name, extention) = f

println("%s\n%s\n%s".format(path, name, extention))