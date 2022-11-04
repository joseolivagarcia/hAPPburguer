package joseoliva.com.happburguer.bbdd

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BurguerPedidaDao {

    @Query("Select * from burguerpedidas")
    fun getAllBurguerPedidas(): LiveData<List<BurguerPedida>>

    @Insert
    suspend fun insertBurguer(burguer: BurguerPedida)
    @Delete
    suspend fun deleteBurguer(burguer: BurguerPedida)
    @Update
    suspend fun updateBurguer(burguer: BurguerPedida)

    //funcion para sumar el precio del total del pedido
    @Query("Select SUM(precio) From burguerpedidas")
    fun getPrecioTotal(): Float

}