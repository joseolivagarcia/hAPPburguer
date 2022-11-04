package joseoliva.com.happburguer.repositorio

import androidx.lifecycle.LiveData
import joseoliva.com.happburguer.bbdd.BurguerPedida
import joseoliva.com.happburguer.bbdd.BurguerPedidaDao

/*
El repositorio es una clase desde la que se decide de donde
obtener los datos. Pueden obtenerse desde una API o desde una
bbdd local como Room.
El repositorio tiene la logica para decidir de donde obtendra los datos.
Esta clase recibe un objeto de tipo DAO
 */
class BurguerRepositorio(val burguerdao: BurguerPedidaDao) {

    //recupero todas las burguers que haya en el pedido
    val listaburguerpedidas: LiveData<List<BurguerPedida>> = burguerdao.getAllBurguerPedidas()

    //y creo funciones para cada accion a realizar en la bbdd
    suspend fun insertburguer(burguer: BurguerPedida){
        burguerdao.insertBurguer(burguer)
    }
    suspend fun deleteburguer(burguer: BurguerPedida){
        burguerdao.deleteBurguer(burguer)
    }
    suspend fun updateburguer(burguer: BurguerPedida){
        burguerdao.updateBurguer(burguer)
    }
}