package ua.meugen.android.client.coroutines.model.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ua.meugen.android.client.coroutines.model.resp.DataResponse

interface ServerApi {

    @GET("/rs/storage/with/delay")
    fun storageWithDelay(
            @Query("id") id: Int,
            @Query("delay") delay: Long): Call<DataResponse>
}