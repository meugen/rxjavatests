package ua.meugen.android.rxjavatests_client.viewmodel.rest;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ua.meugen.android.rxjavatests_client.model.responses.DataResponse;

/**
 * @author meugen
 */

public interface RestApi {

    @GET("/rs/data/with/delay")
    Observable<DataResponse> dataWithDelay(
            @Query("delay") long delay,
            @Query("count") int count);
}
