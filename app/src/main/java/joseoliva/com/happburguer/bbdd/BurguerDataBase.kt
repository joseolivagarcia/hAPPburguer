package joseoliva.com.happburguer.bbdd

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = arrayOf(BurguerPedida::class),
    version = 1
)
abstract class BurguerDataBase: RoomDatabase() {

    abstract fun getBurguerDao(): BurguerPedidaDao

    /*
    Metemos el companion object para prevenir que se abran
    multiples instancias de la bbdd al mismo tiempo
     */
    companion object{
        @Volatile
        private var INSTANCE: BurguerDataBase? = null
        fun getDatabase(context: Context): BurguerDataBase{
            //si la instancia no es nula la retorna
            //si es nula, crea la bbdd
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BurguerDataBase::class.java,
                    "burguer_database"
                ).build()
                INSTANCE = instance
                //devolvemos la instance
                instance
            }
        }
    }
}