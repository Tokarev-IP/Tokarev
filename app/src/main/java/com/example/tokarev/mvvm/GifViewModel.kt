package com.example.tokarev.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokarev.api.ResultData
import com.example.tokarev.db.LatestEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
        number++
        Log.d("API", "number is $number")
        latestLiveData.postValue(latestDataList[number])
        if (number-2<latestDataList.size)
            viewModelScope.launch(Dispatchers.IO) {
                numberApi++
                insertToArrayList(gifRep.getLatestData(numberApi).result, numberApi)
            }
    }

    fun clickBack(){
        if (number!=0) {
            number--
            latestLiveData.postValue(latestDataList[number])
        }
    }

    fun getDataFromApi(number: Int){
        try {
            viewModelScope.launch(Dispatchers.IO) {
                insertToArrayList(gifRep.getLatestData(numberApi).result, number)
                latestLiveData.postValue(latestDataList[number])
                Log.d("API", gifRep.getLatestData(number).toString())
            }
        }
        catch (e: Throwable){
            Log.d("API", e.toString())
        }
    }

    fun getAllFromLatestRoom(){
        viewModelScope.launch(Dispatchers.IO) {
            gifRep.getAllFromRoom()
        }
    }

    private fun insertToArrayList(dataList: List<ResultData>, number: Int){
        for (i in dataList.indices)
            latestDataList.add(LatestEntity(
                UUID.randomUUID().toString(),
                dataList[i].gif_url,
                dataList[i].description,
                number,
            ))
    }
}