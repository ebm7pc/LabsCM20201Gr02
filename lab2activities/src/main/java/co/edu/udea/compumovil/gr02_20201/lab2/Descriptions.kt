package co.edu.udea.compumovil.gr02_20201.lab2

import android.widget.ImageView
import androidx.core.graphics.drawable.toDrawable

data class Descriptions(
    val santorini:String="Las más conocidas de todas son las que forman el archipiélago de Santorini.",
    val naxos:String="Naxos es la más grande y fértil de las islas Cícladas.",
    val rodas:String="Posee un gran legado de mitos y leyendas.",
    val miconos:String="En verano, Miconos se convierte en la Ibiza de Grecia.",
    val olimpia:String="El lugar donde se celebraban los Juegos Olímpicos en la Antigüedad."
)

data class LongDescriptions(
    val santorini:String="Aunque hay muchas islas que visitar en Grecia, las más conocidas de " +
            "todas son las que forman el archipiélago de Santorini.\n" +
            "\n" +
            "Las fachadas blancas – y dinteles y puertas azules – de las casas de los pueblos que " +
            "se asoman a acantilados que mueren en el mar, aparecen en cualquier postal de lugares " +
            "turísticos de Grecia.\n" +
            "\n",
    val naxos:String="Otra de las islas que ver en Grecia es Naxos.\n" +
            "\n" +
            "Naxos es la más grande y fértil de las islas Cícladas que reposan en el Egeo. " +
            "La lluvia es generosa en la zona y permite que la población de la isla pueda vivir " +
            "de la recolección de frutas y verduras, apoyándose también en la ganadería y el turismo.\n" +
            "\n" +
            "Como es habitual en Grecia, en Naxos podrás encontrar varios sitios arqueológicos – " +
            "incluyendo una antigua fortaleza veneciana – y preciosas playas. Dos de las que " +
            "merecen la pena están muy cerca de la capital: Agia Anna y Agios Prokopios. " +
            "Esta última es perfecta para los amantes del nudismo.",
    val rodas:String="La isla más grande del Dodecaneso se encuentra a tan sólo 18 km de las " +
            "costas turcas y posee un gran legado de mitos y leyendas, además de algunas de las " +
            "playas más famosas que visitar en Grecia.\n" +
            "\n" +
            "En Rodas, el mito dice que Helios engendró 8 hijos con la ninfa Rodo, hija de Poseidón. " +
            "De ella proviene el nombre de la isla y sus más de 300 días de sol al año.\n" +
            "\n" +
            "La capital de la isla – llamada también Rodas – posee la Ciudad Medieval, " +
            "declarada Patrimonio de la Humanidad por la UNESCO. Los museos Arqueológico " +
            "y Bizantino son los más visitados.",
    val miconos:String="Si irte de fiesta es una de las cosas que quieres hacer en Grecia, " +
            "no puedes dejar de visitar la isla de Miconos.\n" +
            "\n" +
            "En verano, Miconos se convierte en la Ibiza de Grecia. La música electrónica toma los " +
            "múltiples bares y discotecas de las playas y decenas de miles de jóvenes lo dan todo hasta el amanecer.\n" +
            "\n" +
            "Sin embargo, el resto del año Miconos es un lugar tranquilo, con poco más de 10.000 " +
            "habitantes que tienen una vida rutinaria en las calles de casas encaladas de Chora, " +
            "su capital. La mayoría de las playas de la isla son nudistas, destacando las de Paranga, Paradise, Panormos o Platis Gialos.",
    val olimpia:String="Entre las cosas que hacer en Grecia no puedes dejar de conocer el lugar " +
            "donde se celebraban los Juegos Olímpicos en la Antigüedad.\n" +
            "\n" +
            "Los primeros Juegos Olímpicos se celebraron aquí, en honor a Zeus, en el 776 a.C. " +
            "Estos juegos formaban parte de los cuatro Juegos Panhelénicos de la Antigua Grecia, " +
            "siendo los otros los Juegos Píticos – celebrados en Delfos y dedicados a Apolo -, " +
            "los Juegos Nemeos – en Argos y como tributo a Hera – y los Juegos Ístmicos, " +
            "en Corinto y en honor a Poseidón.\n" +
            "\n" +
            "En Olimpia te emocionarás al contemplar las ruinas del estadio original en el que " +
            "se celebraban las competiciones. También existen restos del estudio de Fidias, " +
            "el gimnasio y el Templo de Zeus."
)
