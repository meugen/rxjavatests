package ua.in.meugen.myapplication.ui.activities.base.fragment;

import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import ua.in.meugen.myapplication.app.di.scopes.PerFragment;

@Module
public abstract class BaseFragmentModule {

    @Binds @PerFragment
    abstract Fragment bindFragment(final BaseFragment fragment);
}
