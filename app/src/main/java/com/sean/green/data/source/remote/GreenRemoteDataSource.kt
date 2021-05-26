package com.sean.green.data.source.remote


import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.sean.green.GreenApplication
import com.sean.green.R
import com.sean.green.data.*
import com.sean.green.data.source.GreenDataSource
import com.sean.green.ext.toDisplayFormat
import com.sean.green.util.Logger
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


object GreenRemoteDataSource : GreenDataSource {

    private const val PATH_GREEN = "green"

    private const val PATH_USERS = "users"
    private const val PATH_GREENS = "greens"
    private const val KEY_CREATED_TIME = "createdTime"

    override suspend fun getSaveNum(): Result<List<Save>> = suspendCoroutine { continuation ->
        FirebaseFirestore.getInstance()
            .collection(PATH_GREEN)
            .orderBy(KEY_CREATED_TIME, Query.Direction.DESCENDING)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val list = mutableListOf<Save>()
                    for (document in task.result!!) {
                        Log.d("seanGetSaveNum",document.id + " => " + document.data)

                        val saveNum = document.toObject(Save::class.java)
                        list.add(saveNum)
                    }

                    continuation.resume(Result.Success(list))

                } else {
                    task.exception?.let {

                        Log.w("sean","[${this::class.simpleName}] Error getting documents. ${it.message}")
                        continuation.resume(Result.Error(it))
                        return@addOnCompleteListener
                    }
                    continuation.resume(Result.Fail(GreenApplication.instance.getString(R.string.you_know_nothing)))
                }
            }
    }

    override suspend fun getUseNum(): Result<List<Use>> = suspendCoroutine { continuation ->
        FirebaseFirestore.getInstance()
            .collection(PATH_GREEN)
            .orderBy(KEY_CREATED_TIME, Query.Direction.DESCENDING)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val list = mutableListOf<Use>()
                    for (document in task.result!!) {
                        Log.d("seanGetUseNum",document.id + " => " + document.data)

                        val useNum = document.toObject(Use::class.java)
                        list.add(useNum)
                    }

                    continuation.resume(Result.Success(list))

                } else {
                    task.exception?.let {

                        Log.w("sean","[${this::class.simpleName}] Error getting documents. ${it.message}")
                        continuation.resume(Result.Error(it))
                        return@addOnCompleteListener
                    }
                    continuation.resume(Result.Fail(GreenApplication.instance.getString(R.string.you_know_nothing)))
                }
            }
    }

    override suspend fun getChallengeNum(): Result<List<Challenge>> = suspendCoroutine { continuation ->
        FirebaseFirestore.getInstance()
            .collection(PATH_GREEN)
            .orderBy(KEY_CREATED_TIME, Query.Direction.DESCENDING)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val list = mutableListOf<Challenge>()
                    for (document in task.result!!) {
                        Log.d("seanGetSaveNum",document.id + " => " + document.data)

                        val challengeNum = document.toObject(Challenge::class.java)
                        list.add(challengeNum)
                    }

                    continuation.resume(Result.Success(list))

                } else {
                    task.exception?.let {

                        Log.w("sean","[${this::class.simpleName}] Error getting documents. ${it.message}")
                        continuation.resume(Result.Error(it))
                        return@addOnCompleteListener
                    }
                    continuation.resume(Result.Fail(GreenApplication.instance.getString(R.string.you_know_nothing)))
                }
            }
    }

    override suspend fun addSaveNum2Firebase(save: Save,userId: String): Result<Boolean> = suspendCoroutine { continuation ->
        val users = FirebaseFirestore.getInstance().collection(PATH_USERS)
            .document(userId).collection("greens").document(Calendar.getInstance()
                .timeInMillis.toDisplayFormat()).collection("save").document()
//        val subCollection = users
//        val document =  subCollection.document()
//
//            .document("greens").collection(Date().toString())
//        val plans = FirebaseFirestore.getInstance().collection(PATH_ARTICLES).document(taskID)
//        val subCollection = plans.collection("completedList")
//        val document = subCollection.document()

        save.id = users.id
//        save.createdTime = Calendar.getInstance().timeInMillis

        users
            .set(save)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("dataSource","addSaveNum2Firebase: $save")

                    continuation.resume(Result.Success(true))
                } else {
                    task.exception?.let {

                        Log.d("dataSource","[${this::class.simpleName}] Error getting documents. ${it.message}")
                        continuation.resume(Result.Error(it))
                        return@addOnCompleteListener
                    }
                    continuation.resume(Result.Fail(GreenApplication.instance.getString(R.string.you_know_nothing)))
                }
            }
    }

    override suspend fun addUseNum2Firebase(use: Use): Result<Boolean> = suspendCoroutine { continuation ->
        val useNum = FirebaseFirestore.getInstance().collection(PATH_GREEN)
        val document =  useNum.document()

        use.id = document.id
        use.createdTime = Calendar.getInstance().timeInMillis

        document
            .set(use)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("dataSource","addUseNum2Firebase: $use")

                    continuation.resume(Result.Success(true))
                } else {
                    task.exception?.let {

                        Log.d("dataSource","[${this::class.simpleName}] Error getting documents. ${it.message}")
                        continuation.resume(Result.Error(it))
                        return@addOnCompleteListener
                    }
                    continuation.resume(Result.Fail(GreenApplication.instance.getString(R.string.you_know_nothing)))
                }
            }
    }

    override suspend fun addChallenge2Firebase(challenge: Challenge): Result<Boolean> = suspendCoroutine { continuation ->
        val challengeNum = FirebaseFirestore.getInstance().collection(PATH_GREEN)
        val document =  challengeNum.document()

        challenge.id = document.id
        challenge.createdTime = Calendar.getInstance().timeInMillis

        document
            .set(challenge)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("dataSource","addChallenge2Firebase: $challenge")

                    continuation.resume(Result.Success(true))
                } else {
                    task.exception?.let {

                        Log.d("dataSource","[${this::class.simpleName}] Error getting documents. ${it.message}")
                        continuation.resume(Result.Error(it))
                        return@addOnCompleteListener
                    }
                    continuation.resume(Result.Fail(GreenApplication.instance.getString(R.string.you_know_nothing)))
                }
            }
    }

}