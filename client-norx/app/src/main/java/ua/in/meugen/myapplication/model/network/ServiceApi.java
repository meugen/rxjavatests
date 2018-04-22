package ua.in.meugen.myapplication.model.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ua.in.meugen.myapplication.model.network.resp.ItemsResponse;

public interface ServiceApi {

    @GET("/rs/storage/with/delay")
    Call<ItemsResponse> storageWithDelay(
            @Query("id") int id, @Query("delay") long delay);
}
