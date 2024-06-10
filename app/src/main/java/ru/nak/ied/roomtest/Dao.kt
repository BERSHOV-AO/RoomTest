package ru.nak.ied.roomtest

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert      // для рум это записть
    fun insertItem(item: Item)

    @Query("SELECT * FROM items") // запрос выбрать все из items
    fun getAllItem(): Flow<List<Item>>
}