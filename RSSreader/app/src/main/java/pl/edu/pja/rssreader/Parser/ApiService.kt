package pl.edu.pja.rssreader.Parser

import pl.edu.pja.rssreader.Model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("pub/rss/wiadomosci.htm")
    suspend fun getItems(): Response<Rss>
}