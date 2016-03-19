import java.awt.datatransfer._
import scala.collection.mutable
import scala.collection.JavaConversions.asScalaBuffer

val flavors = SystemFlavorMap.getDefaultFlavorMap.asInstanceOf[SystemFlavorMap]
val buf: mutable.Buffer[String] = flavors.getNativesForFlavor(DataFlavor.imageFlavor)