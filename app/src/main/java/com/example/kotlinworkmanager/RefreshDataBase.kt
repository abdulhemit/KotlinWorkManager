package com.example.kotlinworkmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class RefreshDataBase(val context: Context, workerParams: WorkerParameters) : Worker(context,
    workerParams
) {


    override fun doWork(): Result {
        val getData = inputData
        val myNumber = getData.getInt("intKey",0)
        refreshDataBase(myNumber)
        return Result.success()
    }

    private fun refreshDataBase(myNumber:Int){
        val sharedPreferences = context.getSharedPreferences("com.example.kotlinworkmanager",Context.MODE_PRIVATE)
        var mySavedNumber = sharedPreferences.getInt("myNumber",0)
        mySavedNumber = mySavedNumber + myNumber
        println("Number: ${mySavedNumber}")
        sharedPreferences.edit().putInt("myNumber",mySavedNumber).apply()

    }
}