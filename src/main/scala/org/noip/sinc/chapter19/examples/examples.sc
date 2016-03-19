import scala.util.parsing.combinator.syntactical.StandardTokenParsers
import scala.util.parsing.combinator.{PackratParsers, RegexParsers}
import scala.util.parsing.input.CharSequenceReader

/**
 * Created with IntelliJ IDEA.
 * Date: 29.09.13
 * Time: 0:11
 * @author dolgiy
 */
//13
//class ExprParsers extends StandardTokenParsers {
//  def value: Parser[Any] = numericLit | "true" | "false" | failure("not valid value")
//
//  def parseAll[T](p: Parser[T], in: String): ParseResult[T] =
//    phrase(p)(new lexical.Scanner(in))
//}
//val parser = new ExprParsers
//val result = parser.parseAll(parser.value, "true")
//if(result.successful) println(result.get)
//12
//class ExprParsers extends StandardTokenParsers {
//  lexical.delimiters += ("+", "-", "*", "(", ")")
//  def expr: Parser[Any] = term ~ rep(("+" | "-") ~ term)
//  def term: Parser[Any] = factor ~ rep("*" ~> factor)
//  def factor: Parser[Any] = numericLit | "(" ~> expr <~ ")"
//  def parseAll[T](p: Parser[T], in: String): ParseResult[T] =
//    phrase(p)(new lexical.Scanner(in))
//}
//val parser = new ExprParsers
//val result = parser.parseAll(parser.expr, "3*(1+2)")
//if(result.successful) println(result.get)
//9
//class OnesPackratParser extends RegexParsers with PackratParsers {
//  lazy val ones: PackratParser[Any] = ones ~ "1" | "1"
//  def parseAll[T](p: Parser[T], input: String) = phrase(p)(new CharSequenceReader(input))
//}
//val parser = new OnesPackratParser
//val result = parser.parseAll(parser.ones, "1111111111111111")
//
//if(result.successful) println(result.get)
//8
//class ExprParser extends RegexParsers {
//  val number = "[0-9]+".r
//
//  def expr: Parser[Any] = term ~! opt(("+" | "-") ~! expr)
//
//  def term: Parser[Any] = factor ~! rep("*" ~! factor)
//
//  def factor: Parser[Any] = "(" ~! expr ~! ")" | number
//}
//val parser = new ExprParser
//val result = parser.parseAll(parser.expr, "(3+4)*5")
//
//if(result.successful) println(result.get)
//7
//class ExprParser extends RegexParsers {
//  val number = "[0-9]+".r
//
//  def expr: Parser[Int] = term ~ rep(
//    ("+" | "-") ~ term ^^ {
//      case "+" ~ t => t
//      case "-" ~ t => -t
//    }) ^^ { case t ~ r => t + r.sum}
//
//  def term: Parser[Int] = factor into { first =>
//    rep("*" ~> factor) ^^ (first * _.product)
//  }
//
//  def factor: Parser[Int] = log(number)("number") ^^ {_.toInt} | "(" ~> expr <~ ")"
//}
//val parser = new ExprParser
//val result = parser.parseAll(parser.expr, "3-4-5*2")
//if(result.successful) println(result.get)
//6
//class ExprParser extends RegexParsers {
//  val number = "[0-9]+".r
//
//  def expr: Parser[Int] = term ~ rep(
//    ("+" | "-") ~ term ^^ {
//      case "+" ~ t => t
//      case "-" ~ t => -t
//    }) ^^ { case t ~ r => t + r.sum}
//
//  def term: Parser[Int] = factor ~ rep("*" ~> factor) ^^ {
//    case f ~ r => f * r.product
//  }
//
//  def factor: Parser[Int] = number ^^ {_.toInt} | "(" ~> expr <~ ")"
//}
//val parser = new ExprParser
//val result = parser.parseAll(parser.expr, "3-4-5")
//if(result.successful) println(result.get)
//5
//class Expr
//case class Number(value: Int) extends Expr
//case class Operator(op: String, left: Expr, right: Expr) extends Expr
//
//class ExprParser extends RegexParsers {
//  val number = "[0-9]+".r
//
//  def expr: Parser[Int] = term ~ opt(("+" | "-") ~ expr) ^^ {
//    case t ~ None => t
//    case t ~ Some("+" ~ e) => t + e
//    case t ~ Some("-" ~ e) => t - e
//  }
//  def term: Parser[Expr] = (factor ~ opt("*" ~> term)) ^^ {
//    case a ~ None => a
//    case a ~ Some(b) => Operator("*", a, b)
//  }
//
//  def factor: Parser[Expr] = number ^^ (n => Number(n.toInt)) | "(" ~> expr <~ ")"
//}
//val parser = new ExprParser
//val result = parser.parseAll(parser.expr, "3-4*5")
//if(result.successful) println(result.get)
//4
//class ExprParser extends RegexParsers {
//  val number = "[0-9]+".r
//
//  def expr: Parser[Int] = term ~ opt(("+" | "-") ~ expr) ^^ {
//    case t ~ None => t
//    case t ~ Some("+" ~ e) => t + e
//    case t ~ Some("-" ~ e) => t - e
//  }
//  def term: Parser[Int] = factor ~ rep("*" ~> factor) ^^ {
//    case f ~ r => f * r.product
//  }
//
//  def factor: Parser[Int] = number ^^ {_.toInt} | "(" ~> expr <~ ")"
//}
//val parser = new ExprParser
//val result = parser.parseAll(parser.expr, "3-4*5")
//if(result.successful) println(result.get)
//3
//class ExprParser extends RegexParsers {
//  val number = "[0-9]+".r
//
//  def expr: Parser[Int] = term ~ opt(("+" | "-") ~ expr) ^^ {
//    case t ~ None => t
//    case t ~ Some("+" ~ e) => t + e
//    case t ~ Some("-" ~ e) => t - e
//  }
//  def term: Parser[Int] = factor ~ rep("*" ~ factor) ^^ {
//    case f ~ r => f * r.map(_._2).product
//  }
//
//  def factor: Parser[Int] = number ^^ {_.toInt} | "(" ~ expr ~ ")" ^^ {
//    case _ ~ e ~ _ => e
//  }
//}
//val parser = new ExprParser
//val result = parser.parseAll(parser.expr, "3-4*5")
//if(result.successful) println(result.get)
// 2
//class ExprParser extends RegexParsers {
//  val number = "[0-9]+".r
//
//  def expr: Parser[Any] = term ~ opt(("+" | "-") ~ expr)
//  def term: Parser[Any] = factor ~ rep("*" ~ factor)
//  def factor: Parser[Any] = number | "(" ~ expr ~ ")"
//}
//val parser = new ExprParser
//val result = parser.parseAll(parser.expr, "3-4*5")
//
//if(result.successful) println(result.get)