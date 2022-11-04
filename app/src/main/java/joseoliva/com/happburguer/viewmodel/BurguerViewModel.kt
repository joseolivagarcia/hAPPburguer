package joseoliva.com.happburguer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import joseoliva.com.happburguer.bbdd.BurguerDataBase
import joseoliva.com.happburguer.bbdd.BurguerPedida
import joseoliva.com.happburguer.repositorio.BurguerRepositorio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
El viewModel es la clase que se ocupa de proveer de datos a la UI
Es una especie de intermediaria entre el repositorio y la UI.
 */
class BurguerViewModel(application: Application): AndroidViewModel(application) {
    /*
   creamos una var donde guardaremos todas las burguers pedidas (una lista)
   y una var para referenciar nuestro repositorio
   Daran error hasta que las inicialicemos
    */
    val listaburguers: LiveData<List<BurguerPedida>>
    val repositorio: BurguerRepositorio

    init {
        /* el dao lo obtengo desde la clase BurguerDataBase llamando a la fun getDatabase
            y pasandola el contexto que estoy cogiendo de esta misma clase
            y llamando a la fun getBurguerDao de la misma clase BurguerDataBase
         */
        val dao = BurguerDataBase.getDatabase(application).getBurguerDao()
        //inicializo el repo pasandole el dao que acabo de obtener
        repositorio = BurguerRepositorio(dao)
        //y obtengo todas las notas en la var que creé arriba
        listaburguers = repositorio.listaburguerpedidas
    }
    /*
     Me creo las funciones para insertat,borrar o editar burguers. LLamare a las funciones que
     hay en el repositorio.
     Lo hago con viewModelScope para no hacerlo en el hilo ppal y no bloquear la app
     */
    fun insertburguer(burguer: BurguerPedida) = viewModelScope.launch(Dispatchers.IO) {
        repositorio.insertburguer(burguer)
    }
    fun deleteburguer(burguer: BurguerPedida) = viewModelScope.launch(Dispatchers.IO) {
        repositorio.deleteburguer(burguer)
    }
    fun updateburguer(burguer: BurguerPedida) = viewModelScope.launch(Dispatchers.IO) {
        repositorio.updateburguer(burguer)
    }
}