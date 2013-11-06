package org.noip.sinc.chapter22.examples

import javax.swing._
import java.awt.BorderLayout
import scala.util.continuations._
import java.awt.event.{ActionEvent, ActionListener}

object TestExample8 extends App{
	val frame = new JFrame()
	val button = new JButton("Next")

	setListener(button) { run() }

	val textField = new JTextArea(10, 40)

	textField.setEnabled(false)

	val label = new JLabel("Welcome to the demo app")

	frame.add(label, BorderLayout.NORTH)
	frame.add(textField)

	val panel = new JPanel()
	panel.add(button)

	frame.add(panel, BorderLayout.SOUTH)
	frame.pack()
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
	frame.setVisible(true)

	def run() {
		reset {
			val response1 = getResponse("What is your first name?")
			val response2 = getResponse("What is your last name?")
			process(response1, response2)
		}
	}

	def process(s1: String, s2: String) {
		label.setText("Hello " + s1 + " " + s2)
	}

	var cont: Unit => Unit = null

	def getResponse(prompt: String): String @cps[Unit] = {
		label.setText(prompt)
		textField.setEnabled(true)
		setListener(button) { cont() }
		shift {
			k: (Unit => Unit) => {
				cont = k
			}
		}
		setListener(button) {}
		textField.setEnabled(false)
		val text = textField.getText
		textField.setText("")
		text
	}

	def setListener(button: JButton)(action: => Unit) {
		for(l <- button.getActionListeners) button.removeActionListener(l)
		button.addActionListener(new ActionListener {
			def actionPerformed(e: ActionEvent) { action }
		})
	}
}
