package org.noip.sinc.merge

import java.io.File

import com.typesafe.config.ConfigFactory
import org.noip.sinc.merge.Context.{localRepPath, remoteRepPath}
import org.noip.sinc.merge.MergeHelper.{install, mergeAll, updateAll}

import scala.collection.mutable
import scala.concurrent.{Future, future}
import scala.concurrent.ExecutionContext.Implicits._
import scala.swing._
import scala.sys.process.{Process, ProcessLogger}

object SimpleMerge extends App {
	val win = new MergeFrame
	win.visible = true
}

class MergeFrame extends Frame {
	title = "Merge util"
	contents = new MainPanel(Context.branches, Context.revisions)
	pack()

	override def closeOperation() = System.exit(0)
}

class MainPanel(branches: Seq[String], revisions: Seq[Int]) extends BoxPanel(Orientation.Vertical) {

	val state = mutable.Map(
		"versions" -> branches,
		"revisions" -> revisions,
		"goals" -> Seq("merge")
	)

	var labels = mutable.Buffer[Label]()
	var components = mutable.Buffer[Component]()

	val verList = new ListView[String](branches)
	listenTo(verList.selection)
	val revField = new TextField(revisions.mkString(", "))
	listenTo(revField)

	addComponent("Versions", new ScrollPane(verList))
	addComponent("Revisions", revField)
	contents += new Separator()
	addComponent("Merge", new CheckBox())
	addComponent("Install", new CheckBox())
	contents += new Button(Action("Start") {

		//todo fix
		components foreach (_.enabled = false)

		val backgroundOperation: Future[Unit] = future {
			val vs = verList.selection.items
			updateAll(vs)
			if(true) mergeAll(vs, revField.text split "[,]" map (_.trim.toInt))
			if(false) vs.foreach(install)
		}

		backgroundOperation onSuccess {
			case result => Swing.onEDT {
				//todo fix
				components foreach (_.enabled = true)
			}
		}


	})
	contents foreach setPrefHeight
	justify(labels)

	def addComponent(label: String, comp: Component) = {
		components += comp
		contents += addLabel(label, comp)
	}

	def addLabel(text: String, c: Component) = {
		setPrefHeight(c)
		val p = new BoxPanel(Orientation.Horizontal)
		val l = new Label(text)
		labels += l
		p.contents += l
		p.contents += Swing.HStrut(6)
		p.contents += c
		p.contents += Swing.HGlue
		p
	}

	def setPrefHeight(c: Component) = {
		val size = new Dimension
		size.height = c.preferredSize.height
		size.width = c.maximumSize.width
		c.maximumSize = size
	}

	def setWidth(c: Component, width: Int) = {
		val s = new Dimension
		s.height = c.preferredSize.height
		s.width = width
		c.maximumSize = s
		c.minimumSize = s
		c.preferredSize = s
	}

	def justify(cs: Seq[Component]) = {
		val w = (cs map (_.preferredSize.width)).max
		cs foreach (setWidth(_, w))
	}
}

object MergeHelper {

	def updateAll(vs: Seq[Any]) = vs foreach (p => update(s"$localRepPath/branches/$p"))

	def mergeAll(vs: Seq[String], rv: Seq[Int]) = 	for {
		v <- vs
		r <- rv
	} merge(r, v)

	def update(path: String) {
		println("Updating " + path)
		val cmd = Seq("svn", "up")
		val p = Process(cmd, new File(path))
		p !< ProcessLogger(s => println(s))
	}

	def merge(revNum: Int, pathTo: String) {
		println("Merging %d to %s".format(revNum, pathTo))
		val cmd = Seq("svn", "merge", "-r%d:%d".format(revNum - 1, revNum), remoteRepPath, ".")
		val p = Process(cmd, new File(localRepPath + "/branches/" + pathTo))
		p !< ProcessLogger(s => println(s))
	}

	def install(path: String) {
		val cmd = Seq("mvn", "-DskipTests", "install")
		val p = Process(cmd, new File(localRepPath + "/branches/" + path))
		p !< ProcessLogger(s => println(s))
	}
}

object Context {
	import scala.collection.JavaConversions.iterableAsScalaIterable
	val conf = ConfigFactory.load("org/noip/sinc/merge/config").getConfig("org.noip.sinc.merge")
	// измена папок бранчей из свойства
	val branches: Seq[String] = conf.getStringList("versions").toSeq
	// номера ревизий из свойства
	val revisions: Seq[Int] = (conf.getIntList("revisions") map (_.toInt)).toSeq
	val localRepPath = conf.getString("localRepPath")
	val remoteRepPath = conf.getString("remoteRepPath")
}
