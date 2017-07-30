package ua.meugen.android.rxjavatests_client.model.rest;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import ua.meugen.android.rxjavatests_client.model.responses.DataResponse;

/**
 * @author meugen
 */

public interface Api {

    @GET("/rs/data/with/delay")
    Observable<DataResponse> dataWithDelay(
            @Query("delay") long delay,
            @Query("count") int count);
}