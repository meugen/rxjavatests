package ua.in.meugen.myapplication.ui.activities.main;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ua.in.meugen.myapplication.app.di.scopes.PerActivity;
import ua.in.meugen.myapplication.app.di.scopes.PerFragment;
import ua.in.meugen.myapplication.ui.activities.base.BaseActivity;
import ua.in.meugen.myapplication.ui.activities.base.BaseActivityModule;
import ua.in.meugen.myapplication.ui.activities.main.fragment.MainFragment;
import ua.in.meugen.myapplication.ui.activities.main.fragment.MainFragmentModule;

@Module(includes = BaseActivityModule.class)
public abstract class MainActivityModule {

    @Binds @PerActivity
    abstract BaseActivity bindActivity(final MainActivity activity);

    @ContributesAndroidInjector(modules = MainFragmentModule.class)
    @PerFragment
    abstract MainFragment contributeMainFragment();
}
