package joseoliva.com.happburguer.provider

import joseoliva.com.happburguer.R
import joseoliva.com.happburguer.modeloviewpager.BurguerModelViewPager

//esta clase es para tener ordenada la lista de burguers que vamos a crear manuealmente para el viewpager
class BurguersProvider {
    companion object{
        val burguersListViewpager = listOf<BurguerModelViewPager>(
            BurguerModelViewPager(
                fotoburguer = R.drawable.campera,
                precio = "18€",
                nombre = "Campera",
                descripcion = "Magnífica hamburguesa con ingredientes de proximidad para que sientas todo el sabor!",
                ingrediente1 = "Pepino",
                ingrediente2 = "Cebolla",
                ingrediente3 = "Tomate",
                ingrediente4 = "Lechuga",
                ingrediente5 = "Jamón Serrano"
            ),
            BurguerModelViewPager(
                fotoburguer = R.drawable.clasica,
                precio = "15€",
                nombre = "Clásica",
                descripcion = "Magnífica hamburguesa con carne de vacuno 100% y pan brioche. Requetebuena!",
                ingrediente1 = "Pepino",
                ingrediente2 = "Cebolla",
                ingrediente3 = "Tomate",
                ingrediente4 = "Lechuga",
                ingrediente5 = "Queso"
            ),
            BurguerModelViewPager(
                fotoburguer = R.drawable.infantil,
                precio = "12€",
                nombre = "Infantil",
                descripcion = "Magnífica hamburguesa con un tamaño ideal para los más pequeños sin renunciar a todo su sabor!",
                ingrediente1 = "Pepino",
                ingrediente2 = "Cebolla",
                ingrediente3 = "Tomate",
                ingrediente4 = "Lechuga",
                ingrediente5 = "Queso"
            ),
            BurguerModelViewPager(
                fotoburguer = R.drawable.doblecompleta,
                precio = "25€",
                nombre = "Doble Completa",
                descripcion = "Impresionante doble ración de carne de ternera gallega 100%. Solo apta para los más valientes!",
                ingrediente1 = "Pepino",
                ingrediente2 = "Cebolla",
                ingrediente3 = "Tomate",
                ingrediente4 = "Lechuga",
                ingrediente5 = "Huevo frito"
            ),
            BurguerModelViewPager(
                fotoburguer = R.drawable.vegana,
                precio = "20€",
                nombre = "Vegana",
                descripcion = "Magnífica hamburguesa con 'carne' de lentejas y soja para los que quieren disfrutar del sabor sin renunciar a una buena hamburguesa ",
                ingrediente1 = "Pepino",
                ingrediente2 = "Cebolla",
                ingrediente3 = "Tomate",
                ingrediente4 = "Lechuga",
                ingrediente5 = "Calabacín"
            )
        )
    }
}