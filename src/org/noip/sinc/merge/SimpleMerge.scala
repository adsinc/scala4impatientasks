package org.noip.sinc.merge

import java.io.File

import com.typesafe.config.ConfigFactory
import org.noip.sinc.merge.Context.{localRepPath, remoteRepPath}
import org.noip.sinc.merge.MergeHelper.update

import scala.collection.mutable
import scala.swing._
import scala.swing.event.{SelectionChanged, ValueChanged}
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

	reactions += {
		case e: SelectionChanged => state("versions") = verList.selection.items
		case e: ValueChanged => state("revisions") = revField.text
	}

	addComponent("Versions", new ScrollPane(verList))
	addComponent("Revisions", revField)
	contents += new Separator()
	addComponent("Merge", new CheckBox())
	addComponent("Install", new CheckBox())
	contents += new Button(Action("Start") {
		state("versions") foreach (p => update(s"$localRepPath/branches/$p"))
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
