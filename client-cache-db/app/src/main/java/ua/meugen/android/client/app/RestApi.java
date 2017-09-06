package ua.meugen.android.client.app;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ua.meugen.android.client.data.responses.DataResponse;

public interface RestApi {

    @GET("/rs/storage/with/delay")
    Single<DataResponse> storageWithDelay(
            @Query("id") int id, @Query("delay") long delay);
}
