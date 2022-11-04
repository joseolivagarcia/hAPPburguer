package joseoliva.com.happburguer.bbdd

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//esta clase representa a cada tabla de la bbdd que guarda cada hamburguesa que pidamos
@Entity(tableName = "burguerpedidas")
data class BurguerPedida(
    @ColumnInfo(name = "foto")
    val foto: Int,
    @ColumnInfo(name = "nombre")
    val nombre: String,
    @ColumnInfo(name = "precio")
    val precio: Int,
    @ColumnInfo(name = "personalizacion")
    val personalizacion: String
)
{
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
