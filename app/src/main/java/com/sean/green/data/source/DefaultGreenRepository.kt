package com.sean.green.data.source

import com.sean.green.data.Result
import com.sean.green.data.Save
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import java.sql.Timestamp

/**
 * Created by Wayne Chen in Jul. 2019.
 *
 * Concrete implementation to load Stylish sources.
 */
class DefaultGreenRepository(private val greenRemoteDataSource: GreenDataSource,
                               private val greenLocalDataSource: GreenDataSource,
                               private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : GreenRepository {

    override suspend fun getSaveNum(): Result<List<Save>> {
        return greenRemoteDataSource.getSaveNum()
    }
//    override suspend fun getObjects(collection: String, start: Timestamp, end: Timestamp): List<Any> {
//        return greenRemoteDataSource.getObjects(collection= collection)
//    }

//    override suspend fun getProductDetails(id: Long?):Result<DetailResult> {
//        return stylishRemoteDataSource.getProductDetails(id = id)
//    }
}