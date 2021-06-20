package pl.edu.pja.rssreader.Parser

import pl.edu.pja.rssreader.Model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("feed")
    suspend fun getItems(): Response<Item>
}