package com.example.tokarev.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokarev.api.ResultData
import com.example.tokarev.db.LatestEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates

class GifViewModel: ViewModel() {

    private val gifRep = GifRepository()

    private var latestDataList = ArrayList<LatestEntity>()
    private var latestLiveData: MutableLiveData<LatestEntity> = MutableLiveData()
    var number by Delegates.notNull<Int>()
    var numberApi by Delegates.notNull<Int>()

    init {
        number = 0
        numberApi = 0
    }

    fun getLatestData() = latestLiveData

    fun clickGo(){
        if (latestDataList.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                if (latestDataList.size - number != 1) {
                    number++
                    postDataToLive()
                }
                if (latestDataList.size - number < 2) {
                    numberApi++
                    try {
                        insertToArrayList(gifRep.getLatestData(numberApi).result, numberApi)
                    } catch (e: Throwable) {
                        Log.e("API", e.toString())
                    }
                }
            }
        }
    }

    private fun postDataToLive(){
        latestLiveData.postValue(latestDataList[number])
    }

    fun clickBack(){
        if (number!=0) {
            number--
            latestLiveData.postValue(latestDataList[number])
        }
    }

    fun getDataFromApi(){
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    insertToArrayList(gifRep.getLatestData(numberApi).result, numberApi)
                    postDataToLive()
                } catch (e: Throwable){
                    Log.e("API", e.toString())
                }
            }
    }

    fun getAllFromLatestRoom(){
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val roomData = gifRep.getAllFromRoom()
                    if (roomData.isEmpty()) {
                        getDataFromApi()
                    } else {
                        insertToArrayListFromRoom(roomData)
                        numberApi = roomData[roomData.size - 2].number
                        postDataToLive()
                    }
                }
                catch (e: Throwable) {
                    Log.e("API", e.toString())
                }
            }
    }

    private fun insertToArrayListFromRoom(dataList: List<LatestEntity>){
        for (i in dataList.indices) {
            latestDataList.add(
                LatestEntity(
                    UUID.randomUUID().toString(),
                    dataList[i].gifUrl,
                    dataList[i].description,
                    dataList[i].number
                )
            )
        }
    }

    private suspend fun insertToArrayList(dataList: List<ResultData>, number: Int){
        for (i in dataList.indices) {
            latestDataList.add(
                LatestEntity(
                    UUID.randomUUID().toString(),
                    dataList[i].gif_url,
                    dataList[i].description,
                    number,
                )
            )
            insertToArrayListRoom(LatestEntity(
                UUID.randomUUID().toString(),
                dataList[i].gif_url,
                dataList[i].description,
                number,
            ))
        }
    }

    private suspend fun insertToArrayListRoom(data: LatestEntity){
            gifRep.insertToRoom(data)
    }
}