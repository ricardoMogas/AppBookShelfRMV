package com.main.appbookshelfrmv.Core
import com.main.appbookshelfrmv.Core.Book
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
interface ApiService {
    @GET("volumes")
    suspend fun getBooks(@Query("q") query: String): BookResponse
}
object RetrofitInstance {
    private const val BASE_URL = "https://www.googleapis.com/books/v1/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}