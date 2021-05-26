package com.sean.green.data.source

import com.sean.green.data.Challenge
import com.sean.green.data.Result
import com.sean.green.data.Save
import com.sean.green.data.Use
import java.sql.Timestamp

/**
 *
 * Interface to the Environment layers.
 */

interface GreenRepository {

    suspend fun addSaveNum2Firebase(save: Save,userId: String): Result<Boolean>

    suspend fun addUseNum2Firebase(use: Use): Result<Boolean>

    suspend fun addChallenge2Firebase(challenge: Challenge): Result<Boolean>

    suspend fun getSaveNum(collection: String): Result<List<Save>>

    suspend fun getChallengeNum(collection: String): Result<List<Challenge>>

    suspend fun getUseNum(collection: String): Result<List<Use>>

}