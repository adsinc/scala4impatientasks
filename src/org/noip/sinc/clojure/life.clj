(ns org.noip.sinc.clojure.life)

(defn empty-board
  "Создает прямоугольное поле с указанной шириной и высотой"
  [w h]
  (vec (repeat w (vec (repeat h nil)))))

(defn populate
  "Включает значение :on в ячейках, определяемых координатами [y, x]"
  [board living-cells]
  (reduce (fn [board coordinates]
            (assoc-in board coordinates :on))
          board
          living-cells))

(def glifer (populate (empty-board 6 6) #{[2 0] [2 1] [2 2] [1 2] [0 1]}))

(defn pprint
  "Печатает красоту в консоль"
  [board]
  (println (apply str (map #(str % "\n") board))))


(pprint glifer)

(defn neighbours
  [[x y]]
  (for [dx [-1 0 1] dy [-1 0 1] :when (not= 0 dx dy)]
    [(+ dx x)(+ dy y)]))

(defn count-neighbours
  [board loc]
  (count (filter #(get-in board %) (neighbours loc))))

(defn indexed-step
  "Возвращает следующее состояние игрового поля, используя индексы для
  определения координат ячеек, соседних с живыми клетками."
  [board]
  (let [w (count board)
        h (count (first board))]
    (loop [new-board board x 0 y 0]
      (cond
        (>= x w) new-board
        (>= y h) (recur new-board (inc x) 0)
        :else
        (let [new-liveness
              (case (count-neighbours board [x y])
                2 (get-in board [x y])
                3 :on
                nil)]
          (recur (assoc-in new-board [x y] new-liveness) x (inc y)))))))

;(-> (iterate indexed-step glifer) (nth 8) pprint)

(defn indexed-step2
  [board]
  (let [w (count board)
        h (count (first board))]
    (reduce
      (fn [new-board x]
        (reduce
          (fn [new-board y]
            (let [new-liveness
                  (case (count-neighbours board [x y])
                    2 (get-in board [x y])
                    3 :on
                    nil)]
              (assoc-in new-board [x y] new-liveness)))
          new-board (range h)))
      board (range w))))

;(-> (iterate indexed-step2 glifer) (nth 8) pprint)

(defn indexed-step3
  [board]
  (let [w (count board)
        h (count (first board))]
    (reduce
      (fn [new-board [x y]]
        (let [new-liveness
              (case (count-neighbours board [x y])
                2 (get-in board [x y])
                3 :on
                nil)]
          (assoc-in new-board [x y] new-liveness)))
      board (for [x (range h) y (range w)][x y]))))

;(-> (iterate indexed-step3 glifer) (nth 8) pprint)

(defn window
  "Возвращает ленивую последовательность окон с тремя элементами в каждом,
  центральными в которых является элемент coll и дополненную значением
  pad или nil, если необходимо"
  ([coll] (window nil coll))
  ([pad coll]
   (partition 3 1 (concat [pad] coll [pad]))))

(defn cell-block
  "Создает последовательсноть окон 3x3 из троек последовательснотей по 3
  элемента в каждой."
  [[left mid right]]
  (window (map vector left mid right)))

(defn liveness
  "Возвращает признак наличия живой клетки (nil или :on) в центральной
  ячейке для выполнения следующего шага"
  [block]
  (let [[_ [_ center _]] block]
    (case (- (count (filter #{:on} (apply concat block)))
             (if (= :on center) 1 0))
      2 center
      3 :on
      nil)))

(defn step-row
  "Возвращает следующее состояние центра строки."
  [rows-triple]
  (vec (map liveness (cell-block rows-triple))))

(defn index-free-step
  "Возвращает следующее состояние игнового поля."
  [board]
  (vec (map step-row (window (repeat nil) board))))

(= (nth (iterate indexed-step glifer) 8)
   (nth (iterate index-free-step glifer) 8))