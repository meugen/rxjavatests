package ua.in.meugen.myapplication.app.di.modules;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ua.in.meugen.myapplication.app.di.scopes.PerActivity;
import ua.in.meugen.myapplication.ui.activities.main.MainActivity;
import ua.in.meugen.myapplication.ui.activities.main.MainActivityModule;

@Module
public abstract class ContributesModule {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    @PerActivity
    abstract MainActivity contributeMainActivity();
}
