package com.mayoclinicdemo

import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

/**
 * A native module for handling API-related functionality in the React Native application.
 * This module provides a method for fetching data from an API asynchronously.
 *
 * @param reactContext The ReactApplicationContext provided by React Native.
 */
class ApiModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    private val apiClient = ApiClient()

    /**
     * Gets the name of the native module as used in JavaScript.
     *
     * @return The name of the native module.
     */
    override fun getName(): String {
        return "ApiModule"
    }

    /**
     * Fetches data from an API asynchronously and resolves or rejects a Promise.
     *
     * @param applicationId The identifier for the application making the API request.
     * @param requestDate The date of the API request.
     * @param promise A Promise object to resolve or reject based on the API response.
     */
    @ReactMethod
    @Suppress("unused")
    fun fetchApiData(applicationId: String, requestDate: String, promise: Promise) {
        val apiRequest = ApiRequest(applicationId, requestDate)
        apiClient.fetchData(apiRequest, promise)
    }
}