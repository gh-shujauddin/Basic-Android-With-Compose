package com.example.inventory.data

import kotlinx.coroutines.flow.Flow

class OfflineItemsRepository(private val itemDao: ItemDao) : ItemsRepository {

    override fun getAllItems(): Flow<List<Item>> = itemDao.getAllItems()

    override fun getItem(id: Int): Flow<Item> = itemDao.getItem(id)

    override suspend fun insert(item: Item)  = itemDao.insert(item)

    override suspend fun update(item: Item) = itemDao.update(item)

    override suspend fun delete(item: Item) = itemDao.delete(item)
}
