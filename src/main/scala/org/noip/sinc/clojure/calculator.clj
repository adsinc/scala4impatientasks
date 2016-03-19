(ns org.noip.sinc.clojure.calculator
  (:import (javax.swing JFrame JPanel JButton)
           (java.awt GridLayout)
           (java.awt.event ActionListener)))

(def conf {
           :rows 4
           :cols 3})

;(def operations {
;                 '+ +
;                 '- -
;                 '* *
;                 '/ /
;                 })
;
;(def add-action-listener
;  [component f & args]
;  (.addActionListener component
;                      (proxy [ActionListener] []
;                        (actionPerformed [e]
;                          (apply f e args)))))
;
;(defn create-button
;  [name op]
;  (doto (JButton. (str name))
;    (.addActionListener #())))
;
;(map create-button operations)

(defn create-panel [conf]
  "Создает главную панель"
  (let [{rows :rows  cols :cols} conf]
    (JPanel. (GridLayout. rows cols 5 5))))

(defn create-window []
  "Создает окно калькулятора"
  (doto (JFrame. "Calculator")
    (.setContentPane (create-panel))
    (.pack)
    (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
    (.setVisible true)))

(create-window)

