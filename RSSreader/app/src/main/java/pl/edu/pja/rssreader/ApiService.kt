package pl.edu.pja.rssreader

import pl.edu.pja.rssreader.Model.Image
import pl.edu.pja.rssreader.Model.ItemJava
import pl.edu.pja.rssreader.Model.ItemJavaSet
import pl.edu.pja.rssreader.Model.ItemTest
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("feed")
    suspend fun getItems(): Response<ItemJava>
}