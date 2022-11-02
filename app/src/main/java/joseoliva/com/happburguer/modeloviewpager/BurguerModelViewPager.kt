package joseoliva.com.happburguer.modeloviewpager
//este sera el modelo con el que relleno cada hamburguesa de la carta ppal, las que van al ViewPager
data class BurguerModelViewPager(
    val fotoburguer: Int,
    val precio: String,
    val nombre: String,
    val descripcion: String,
    val ingrediente1: String,
    val ingrediente2: String,
    val ingrediente3: String,
    val ingrediente4: String,
    val ingrediente5: String
)
