package com.mayoclinicdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facebook.react.bridge.Promise
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient : ViewModel() {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://content.mcmapp.mayoclinic.org")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService: ApiService = retrofit.create(ApiService::class.java)

    /**
     * Asynchronously fetches data from the API and resolves or rejects a Promise.
     *
     * @param apiRequest The request data to send to the API.
     * @param promise A Promise object to resolve or reject based on the API response.
     */
    fun fetchData(apiRequest: ApiRequest, promise: Promise) {
        viewModelScope.launch {
            try {
                val response = apiService.postData(apiRequest)
                val jsonResponse = Gson().toJson(response)
                promise.resolve(jsonResponse)
            } catch (e: Exception) {
                promise.reject("API_ERROR", "An API error has occurred.")
            }
        }
    }
}