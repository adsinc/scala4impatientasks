import scala.collection.mutable.Map
import scala.collection.mutable

def indexes(s: String) = ((Map[Char, Set[Int]]()) /: s) {
  (m, c) => m + (c  -> (m.getOrElse(c, Set()) + (s indexOf c)))
}

indexes("Mississippi")