package com.mayoclinicdemo.kotlinapi

import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/v2/content/list/")
    suspend fun postData(@Body apiRequest: ApiRequest): ApiResponse
}

data class ApiRequest(
    val ApplicationId: String,
    val RequestDate: String
)

data class ApiResponse(
    @SerializedName("Packages")
    val packages: List<Package>
)

data class Package(
    @SerializedName("Id")
    val id: String,
    @SerializedName("Items")
    val items: List<Item>
)

data class Item(
    @SerializedName("Images")
    val images: List<Image>,
    @SerializedName("IsScheduled")
    val isScheduled: Boolean,
    @SerializedName("Author")
    val author: Author,
    @SerializedName("ReadTime")
    val readTime: String,
    @SerializedName("Url")
    val url: String,
    @SerializedName("Id")
    val id: String,
    @SerializedName("Category")
    val category: Category,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Description")
    val description: String?,
    @SerializedName("Type")
    val type: String,
    @SerializedName("Version")
    val version: String?
)

data class Image(
    @SerializedName("Type")
    val type: String,
    @SerializedName("Value")
    val value: String
)

data class Author(
    @SerializedName("Name")
    val name: String,
    @SerializedName("Photo")
    val photo: String
)

data class Category(
    @SerializedName("Id")
    val id: String,
    @SerializedName("Type")
    val type: String,
    @SerializedName("Name")
    val name: String
)