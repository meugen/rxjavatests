package ua.in.meugen.myapplication.app.di.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ua.in.meugen.myapplication.BuildConfig;
import ua.in.meugen.myapplication.app.di.scopes.PerApplication;
import ua.in.meugen.myapplication.model.network.ServiceApi;

@Module
public abstract class NetworkModule {

    @Provides @PerApplication
    static Interceptor[] provideInterceptors() {
        return new Interceptor[0];
    }

    @Provides @PerApplication
    static OkHttpClient provideClient(
            final Interceptor[] interceptors) {
        final OkHttpClient.Builder builder
                = new OkHttpClient.Builder();
        for (Interceptor interceptor : interceptors) {
            builder.addInterceptor(interceptor);
        }
        if (BuildConfig.DEBUG) {
            final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        return builder.build();
    }

    @Provides @PerApplication
    static Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides @PerApplication
    static Retrofit provideRetrofit(
            final OkHttpClient client,
            final Gson gson) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides @PerApplication
    static ServiceApi provideServiceApi(final Retrofit retrofit) {
        return retrofit.create(ServiceApi.class);
    }
}
