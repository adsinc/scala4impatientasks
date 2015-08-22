(ns org.noip.sinc.clojure.labirint
  (:import (javax.swing JFrame JPanel JScrollPane)
           (java.awt Graphics2D BasicStroke Color Dimension))
  (:require [clojure.zip :as z]))

(defn maze
  "Возвращает случайный вырезанный лабиринт; стены - это множество
  2-элементых множеств #{a b}, где a и b - координаты.
  Возвращаемый лабиринт - это множество удаленных стен."
  [walls]
  (let [paths (reduce (fn [index [a b]]
                        (merge-with into index {a [b] b [a]}))
                      {} (map seq walls))
        start-loc (rand-nth (keys paths))]
    (loop [walls walls
           unvisited (disj (set (keys paths)) start-loc)]
      (if-let [loc (when-let [s (seq unvisited)] (rand-nth s))]
        (let [walk (iterate (comp rand-nth paths) loc)
              steps (zipmap (take-while unvisited walk) (next walk))]
          (recur (reduce disj walls (map set steps))
                 (reduce disj unvisited (keys steps))))
        walls))))

(defn wmaze
  "Возвращает случайный вырезанный лабиринт; стены - это множество
  2-элементых множеств #{a b}, где a и b - координаты.
  Возвращаемый лабиринт - это множество удаленных стен."
  [walls]
  (let [paths (reduce (fn [index [a b]]
                        (merge-with into index {a [b] b [a]}))
                      {} (map seq walls))
        start-loc (rand-nth (keys paths))]
    (loop [walls walls
           unvisited (disj (set (keys paths)) start-loc)]
      (if-let [loc (when-let [s (seq unvisited)] (rand-nth s))]
        (let [walk (iterate (comp rand-nth paths) loc)
              steps (zipmap (take-while unvisited walk) (next walk))
              walk (take-while identity (iterate steps loc))
              steps (zipmap walk (next walk))]
          (recur (reduce disj walls (map set steps))
                 (reduce disj unvisited (keys steps))))
        walls))))

(defn grid
  [w h]
  (set (concat
         (for [i (range (dec w)) j (range h)] #{[i j] [(inc i) j]})
         (for [i (range w) j (range (dec h))] #{[i j] [i (inc j)]}))))

(defn draw
  [w h maze]
  (doto (JFrame. "Maze")
    (.setContentPane
      (doto (proxy [JPanel] []
              (paintComponent [^java.awt.Graphics g]
                (let [g (doto ^Graphics2D (.create g)
                                                   (.scale 10 10)
                                                   (.translate 1.5 1.5)
                                                   (.setStroke (java.awt.BasicStroke. 0.4)))]
                  (.drawRect g -1 -1 w h)
                  (doseq [[[xa ya] [xb yb]] (map sort maze)]
                    (let [[xc yc] (if (= xa xb)
                                    [(dec xa) ya]
                                    [xa (dec ya)])]
                      (.drawLine g xa ya xc yc))))))
        (.setPreferredSize (java.awt.Dimension.
                             (* 10 (inc w)) (* 10 (inc h))))))
    .pack
    (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
    (.setVisible true)))

;(draw 40 40 (maze (grid 40 40)))
;(draw 40 40 (wmaze (grid 40 40)))

(defn hex-grid
  [w h]
  (let [vertices (set (for [y (range h) x (range (if (odd? y) 1 0) (* 2 w) 2)]
                        [x y]))
        deltas [[2 0] [1 1] [-1 1]]]
    (set (for [v vertices d deltas f [+ -]
               :let [w (vertices (map f v d))]
               :when w] #{v w}))))

(defn- hex-outer-walls
  [w h]
  (let [vertices (set (for [y (range h) x (range (if (odd? y) 1 0) (* 2 w) 2)]
                        [x y]))
        deltas [[2 0] [1 1] [-1 1]]]
    (set (for [v vertices d deltas f [+ -]
               :let [w (map f v d)]
               :when (not (vertices w))] #{v (vec w)}))))

(defn hex-draw
  [w h maze]
  (doto (JFrame. "Maze")
    (.setContentPane
      (doto (proxy [JPanel] []
              (paintComponent [^java.awt.Graphics g]
                (let [maze (into maze (hex-outer-walls w h))
                      g (doto ^Graphics2D (.create g)
                          (.scale 10 10)
                          (.translate 1.5 1.5)
                          (.setStroke (java.awt.BasicStroke. 0.4
                                                             java.awt.BasicStroke/CAP_ROUND
                                                             java.awt.BasicStroke/JOIN_MITER)))
                      draw-line (fn [[[xa ya] [xb yb]]]
                                  (.draw g
                                         (java.awt.geom.Line2D$Double.
                                           xa (* 2 ya) xb (* 2 yb))))]
                  (doseq [[[xa ya] [xb yb]] (map sort maze)]
                    (draw-line
                      (cond
                        (= ya yb) [[(inc xa) (+ ya 0.4)]
                                   [(inc xa) (- ya 0.4)]]
                        (< ya yb) [[(inc xa) (+ ya 0.4)] [xa (+ ya 0.6)]]
                        :else [[(inc xa) (- ya 0.4)]
                               [xa (- ya 0.6)]]))))))
        (.setPreferredSize (java.awt.Dimension.
                             (* 20 (inc w)) (* 20 (+ 0.5 h))))))
    .pack
    (.setVisible true)))

;(hex-draw 30 30 (maze (hex-grid 30 30)))

;(def labirinth (let [g (grid 10 10)] (reduce disj g (maze g))))

;(def theseus (rand-nth (distinct (apply concat labirinth))))

;(def minotaur (rand-nth (distinct (apply concat labirinth))))

(defn ariadne-zip
  [labirinth loc]
  (let [paths (reduce (fn [index [a b]]
                      (merge-with into index {a [b] b [a]}))
                      {} (map seq labirinth))
        children (fn [[from to]]
                   (seq (for [loc (paths to)
                              :when (not= loc from)]
                          [to loc])))]
    (z/zipper (constantly true)
                        children
                        nil
                        [nil loc])))

;(->> theseus
;     (ariadne-zip labirinth)
;     (iterate clojure.zip/next)
;     (filter #(= minotaur (second (z/node %))))
;     first clojure.zip/path
;     (map second))

(defn draw-with-path
  [w h maze path]
  (doto (JFrame. "Theseus Maze")
    (.setContentPane
      (JScrollPane.
        (doto (proxy [JPanel] []
                (paintComponent [g]
                  (let [g (doto ^Graphics2D (.create g)
                            (.scale 10 10)
                            (.translate 1.5 1.5)
                            (.setStroke (BasicStroke. 0.4)))]
                    (.drawRect g -1 -1 w h)
                    (doseq [[[xa ya] [xb yb]] (map sort maze)]
                      (let [[xc yc] (if (= xa xb)
                                      [(dec xa) ya]
                                      [xa (dec ya)])]
                        (.drawLine g xa ya xc yc)))
                    (.translate g -0.5 -0.5)
                    (.setColor g Color/RED)
                    (doseq [[[xa ya] [xb yb]] path]
                      (.drawLine g xa ya xb yb)))))
          (.setPreferredSize (Dimension.
                               (* 10 (inc w)) (* 10 (inc h)))))))
    .pack
    (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
    (.setVisible true)))

(let [w 50 h 50
      grid (grid w h)
      walls (maze grid)
      labyrinth (reduce disj grid walls)
      places (distinct (apply concat labyrinth))
      theseus (rand-nth places)
      minotaur (rand-nth places)
      path (->> theseus
                (ariadne-zip labyrinth)
                (iterate z/next)
                (filter #(= minotaur (first (z/node %))))
                first z/path rest)]
  (draw-with-path w h walls path))