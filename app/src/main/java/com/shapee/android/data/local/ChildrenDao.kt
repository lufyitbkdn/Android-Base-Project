package com.shapee.android.data.local

import androidx.room.*
import com.shapee.android.model.Child

@Dao
interface ChildrenDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<Child>)

    @Delete
    fun delete(list: Child)

    @Query("SELECT * FROM Children")
    fun getAll(): List<Child>

    @Query("DELETE FROM Children")
    fun clear()

    @Query("DELETE FROM Children WHERE id = :kidId")
    fun deleteById(kidId: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(child: Child)
}