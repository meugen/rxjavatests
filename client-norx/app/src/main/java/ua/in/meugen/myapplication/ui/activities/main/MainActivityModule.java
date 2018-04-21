package ua.in.meugen.myapplication.ui.activities.main;

import dagger.Binds;
import dagger.Module;
import ua.in.meugen.myapplication.app.di.scopes.PerActivity;
import ua.in.meugen.myapplication.ui.activities.base.BaseActivity;
import ua.in.meugen.myapplication.ui.activities.base.BaseActivityModule;

@Module(includes = BaseActivityModule.class)
public abstract class MainActivityModule {

    @Binds @PerActivity
    abstract BaseActivity bindActivity(final MainActivity activity);
}
