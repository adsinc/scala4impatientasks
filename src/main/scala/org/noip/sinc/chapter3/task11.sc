import java.awt.datatransfer.{DataFlavor, SystemFlavorMap}

import scala.collection.JavaConverters._

val f = SystemFlavorMap.getDefaultFlavorMap.asInstanceOf[SystemFlavorMap]

val l= f.getNativesForFlavor(DataFlavor.imageFlavor).asScala