package ua.in.meugen.myapplication.ui.activities.base.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import dagger.android.support.AndroidSupportInjection;


public class BaseFragment extends Fragment {

    @Override
    public void onAttach(final Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }
}
