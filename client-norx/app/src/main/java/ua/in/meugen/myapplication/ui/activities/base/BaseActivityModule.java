package ua.in.meugen.myapplication.ui.activities.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import dagger.Binds;
import dagger.Module;
import ua.in.meugen.myapplication.app.di.qualifiers.ActivityContext;
import ua.in.meugen.myapplication.app.di.scopes.PerActivity;

@Module
public abstract class BaseActivityModule {

    @Binds @PerActivity
    abstract AppCompatActivity bindCompatActivuity(final BaseActivity activity);

    @Binds @ActivityContext @PerActivity
    abstract Context bindContext(final AppCompatActivity activity);
}
