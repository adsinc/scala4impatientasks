(ns org.noip.sinc.clojure.calculator
  (:import (javax.swing JFrame JPanel JButton)
           (java.awt GridLayout)
           (java.awt.event ActionListener)))

(def operations {
                 '+ +
                 '- -
                 '* *
                 '/ /
                 })

(def add-action-listener
  [component f & args]
  (.addActionListener component
                      (proxy [ActionListener] []
                        (actionPerformed [e]
                          (apply f e args)))))

(defn create-button
  [name op]
  (doto (JButton. (str name))
    (.addActionListener #())))

(map create-button operations)

(defn create-panel [row col]
  (JPanel. (GridLayout. row col 5 5)))

(defn create-window []
  "Создает окно калькулятора"
  (doto (JFrame. "Calculator")
    (.setContentPane (create-panel 4 3))
    (.pack)
    (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
    (.setVisible true)))

(create-window)

