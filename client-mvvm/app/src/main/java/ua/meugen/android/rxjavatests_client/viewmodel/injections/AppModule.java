package ua.meugen.android.rxjavatests_client.viewmodel.injections;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ua.meugen.android.rxjavatests_client.BuildConfig;
import ua.meugen.android.rxjavatests_client.viewmodel.rest.RestApi;

/**
 * @author meugen
 */
@Module
public class AppModule {

    private static final String API_BASE_URL = "http://restapi.meugen.in.ua/";

    private final Context context;

    public AppModule(final Context context) {
        this.context = context;
    }

    @Provides @Singleton
    public Context provideContext() {
        return context;
    }

    @Provides @Singleton
    public OkHttpClient provideOkHttp() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        return builder.build();
    }

    @Provides @Singleton
    public Retrofit provideRetrofit(final OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides @Singleton
    public RestApi provideApi(final Retrofit retrofit) {
        return retrofit.create(RestApi.class);
    }
}
