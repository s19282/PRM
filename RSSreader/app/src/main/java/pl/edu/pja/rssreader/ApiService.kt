package pl.edu.pja.rssreader

import pl.edu.pja.rssreader.Model.Item
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("niebezpiecznik")
    suspend fun getItems(): Response<List<Item>>
}