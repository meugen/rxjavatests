package ua.meugen.android.rxjavatests_client.presenter.rest

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable
import ua.meugen.android.rxjavatests_client.model.DataResponse

/**
 * @author meugen
 */

interface RestApi {

    @GET("/rs/data/with/delay")
    fun dataWithDelay(
            @Query("delay") delay: Long,
            @Query("count") count: Int): Observable<DataResponse>
}
