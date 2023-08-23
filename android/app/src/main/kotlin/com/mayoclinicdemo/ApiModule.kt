package com.mayoclinicdemo

import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class ApiModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    private val apiClient = ApiClient()

    override fun getName(): String {
        return "ApiModule"
    }

    @ReactMethod
    @Suppress("unused")
    fun fetchApiData(applicationId: String, requestDate: String, promise: Promise) {
        val apiRequest = ApiRequest(applicationId, requestDate)
        apiClient.fetchData(apiRequest, promise)
    }
}