package ua.meugen.android.client.di;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Random;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ua.meugen.android.client.BuildConfig;
import ua.meugen.android.client.Client;
import ua.meugen.android.client.activities.main.MainActivity;
import ua.meugen.android.client.activities.main.MainActivityModule;
import ua.meugen.android.client.app.RestApi;

@Module(includes = AndroidSupportInjectionModule.class)
public abstract class AppModule {

    @Provides @Singleton
    public static Random provideRandom() {
        return new Random();
    }

    @Provides @Singleton
    public static Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides @Singleton
    public static OkHttpClient provideOkHttp() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        return builder.build();
    }

    @Provides @Singleton
    public static Retrofit provideRetrofit(
            final OkHttpClient client, final Gson gson) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides @Singleton
    public static RestApi provideRestApi(final Retrofit retrofit) {
        return retrofit.create(RestApi.class);
    }

    @Binds @Singleton
    public abstract Application bindApplication(final Client client);

    @Binds @Singleton
    public abstract Context bindContext(final Application application);

    @PerActivity
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    public abstract MainActivity mainActivity();
}
