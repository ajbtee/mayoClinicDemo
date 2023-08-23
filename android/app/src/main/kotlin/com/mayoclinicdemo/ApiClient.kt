package com.mayoclinicdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.facebook.react.bridge.Promise
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * ViewModel class responsible for making API requests and handling responses.
 */
class ApiClient : ViewModel() {

    // Create a Retrofit instance with the base URL
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://content.mcmapp.mayoclinic.org")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Create an instance of the ApiService interface using Retrofit
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
                // Make an API request using the ApiService interface
                val response = apiService.postData(apiRequest)

                // Convert the API response to a JSON string using Gson
                val jsonResponse = Gson().toJson(response)

                // Resolve the Promise with the JSON response
                promise.resolve(jsonResponse)
            } catch (e: Exception) {
                // Reject the Promise with an error message in case of an exception
                promise.reject("API_ERROR", "An API error has occurred.")
            }
        }
    }
}