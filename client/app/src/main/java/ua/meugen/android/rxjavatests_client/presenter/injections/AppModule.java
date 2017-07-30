package ua.meugen.android.rxjavatests_client.presenter.injections;

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
import ua.meugen.android.rxjavatests_client.model.rest.Api;

/**
 * @author meugen
 */
@Module
public class AppModule {

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
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides @Singleton
    public Api provideApi(final Retrofit retrofit) {
        return retrofit.create(Api.class);
    }
}
