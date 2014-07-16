/**
 * @author dolgiy
 */

import java.io.{FileInputStream, File}
import java.util.Properties

import sys.process._

val prop = new Properties()
prop.load(new FileInputStream("/home/dolgiy/scalaProgs/scala4impatientasks/src/org/noip/sinc/merge/config.properties"))

def getPropAsList(propName: String) =
  (prop.getProperty(propName) split ",").toList map (_.trim)

val repPath = "/home/dolgiy/ProjectRep/imus"
val logDir = "/home/dolgiy/ProjectRep/imus/branches/scriptLogs/"
// номера ревизий из свойства
val revisions = getPropAsList("revisions") map (_.toInt)
// измена папок бранчей из свойства
val branches = getPropAsList("versions")

def update(path: String) {
  println("Updating " + path)
  val cmd = Seq("svn", "up")
  val f = new File(logDir + "update")
  val p = Process(cmd, new File(path))
  p !< ProcessLogger(s => println(s))
//  p !< ProcessLogger(f)
}
val serverPath = "svn://192.168.100.2/ProjectRep/imus/trunk"

def merge(revNum: Int, pathTo: String) {
  println("Merging %d to %s".format(revNum, pathTo))
  val cmd = Seq("svn", "merge", "-r%d:%d".format(revNum - 1, revNum), serverPath, ".")
  val f = new File(logDir + "merge")
  val p = Process(cmd, new File(repPath + "/branches/" + pathTo))
  p !< ProcessLogger(s => println(s))
//  p !< ProcessLogger(f)
}
def install(path: String) {
  val cmd = Seq("mvn", "-DskipTests", "install")
  val f = new File(logDir + "install")
  val p = Process(cmd, new File(repPath + "/branches/" + path))
  p !< ProcessLogger(s => println(s))
//  p !< ProcessLogger(f)
}

//println("updating")
branches.foreach(p => update(repPath + "/branches/" + p))

println("merging")
for {
  br <- branches
  r <- revisions
} merge(r, br)

//installing
//branches.foreach(install)


