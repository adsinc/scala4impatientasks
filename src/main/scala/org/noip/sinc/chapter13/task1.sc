import scala.collection.mutable.Map
import scala.collection.mutable.TreeSet

def indexes(s: String) = ((Map[Char, TreeSet[Int]]()) /: (s zip (0 until s.length))) {
  (m, p) => m += (p._1 -> (m.getOrElse(p._1, TreeSet[Int]()) += p._2))
}
indexes("Mississippi").mkString