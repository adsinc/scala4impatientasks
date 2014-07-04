/**
 * @author dolgiy
 */

import java.io.File

import sys.process._
val revisions = Array(49938, 49950)
val repPath = "/home/dolgiy/ProjectRep/imus"
val logDir = "/home/dolgiy/ProjectRep/imus/branches/scriptLogs/"
val branches = Array(
//  "imus-1.5.440"
//  "imus-1.5.540",
  "imus-1.6"
//  "imus-1.5.330",
//  "imus-1.5.449-mrg"
)
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

println("updating")
branches.foreach(p => update(repPath + "/branches/" + p))

//println("merging")
//for {
//  br <- branches
//  r <- revisions
//} merge(r, br)

//installing
branches.foreach(install)


