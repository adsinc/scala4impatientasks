import scala.util.parsing.combinator.RegexParsers

/**
 * Created with IntelliJ IDEA.
 * Date: 29.09.13
 * Time: 18:14
 * @author dolgiy
 */
//2
//class ExprParser extends RegexParsers {
//  val number = "[0-9]+".r
//
//  def expr: Parser[Int] = term ~ opt(("+" | "-") ~ expr) ^^ {
//    case t ~ None => t
//    case t ~ Some("+" ~ e) => t + e
//    case t ~ Some("-" ~ e) => t - e
//  }
//  def term: Parser[Int] = pow ~ rep(("*" | "/" | "%") ~ pow) ^^ {
//    case f ~ r => r.foldLeft(f)((l, r) => r match {
//      case "*" ~ e => l * e
//      case "/" ~ e => l / e
//      case "%" ~ e => l % e
//    })
//  }
//  def pow: Parser[Int] = factor ~ rep("^" ~> factor) ^^ {
//    case f ~ r => (f :: r).reduceRight(BigInt(_).pow(_).toInt)
//  }
//  def factor: Parser[Int] = number ^^ {_.toInt} | "(" ~> expr <~ ")"
//}
//val parser = new ExprParser
//val result = parser.parseAll(parser.expr, "4^2^3*2-4^2^3*3")
//if(result.successful) println(result.get)
//1
//class ExprParser extends RegexParsers {
//  val number = "[0-9]+".r
//
//  def expr: Parser[Int] = term ~ opt(("+" | "-") ~ expr) ^^ {
//    case t ~ None => t
//    case t ~ Some("+" ~ e) => t + e
//    case t ~ Some("-" ~ e) => t - e
//  }
//  def term: Parser[Int] = factor ~ rep(("*" | "/" | "%") ~ factor) ^^ {
//    case f ~ r => r.foldLeft(f)((l, r) => r match {
//      case "*" ~ e => l * e
//      case "/" ~ e => l / e
//      case "%" ~ e => l % e
//    })
//  }
//  def factor: Parser[Int] = log(number)("number") ^^ {_.toInt} | "(" ~> expr <~ ")"
//}
//val parser = new ExprParser
//val result = parser.parseAll(parser.expr, "4 * 5 % 9 * 10 / 2")
//if(result.successful) println(result.get)
//
