package com.shapee.android.data.local

import com.shapee.android.model.Child
import javax.inject.Singleton

@Singleton
class LocalDataManager (private val geoPapaDatabase: AppDatabase) {
    fun getAll() = geoPapaDatabase.childrenDao().getAll()

    fun deleteAllKid() = geoPapaDatabase.childrenDao().clear()

    fun deleteKidById(kidId: Long) = geoPapaDatabase.childrenDao().deleteById(kidId)

    fun updateKid(kid: Child) = geoPapaDatabase.childrenDao().insertOrUpdate(kid)

    fun insertChildren(children: List<Child>) {
        geoPapaDatabase.childrenDao().clear()
        geoPapaDatabase.childrenDao().insert(children)
    }
}