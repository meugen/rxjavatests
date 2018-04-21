package ua.in.meugen.myapplication.ui.activities.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import timber.log.Timber;
import ua.in.meugen.myapplication.app.di.qualifiers.ActivityContext;
import ua.in.meugen.myapplication.databinding.FragmentMainBinding;
import ua.in.meugen.myapplication.model.network.Resource;
import ua.in.meugen.myapplication.model.network.resp.DataResponse;
import ua.in.meugen.myapplication.ui.activities.base.fragment.BaseFragment;
import ua.in.meugen.myapplication.ui.activities.main.fragment.adapters.ItemsAdapter;
import ua.in.meugen.myapplication.ui.activities.main.fragment.vm.MainViewModel;


public class MainFragment extends BaseFragment {

    @Inject @ActivityContext Context context;
    @Inject MainViewModel viewModel;
    @Inject ItemsAdapter adapter;

    private FragmentMainBinding binding;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d(viewModel.toString());
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull final LayoutInflater inflater,
            @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(
                inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(
            @NonNull final View view,
            @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recycler.addItemDecoration(new DividerItemDecoration(
                context, DividerItemDecoration.VERTICAL));
        binding.recycler.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.itemsLiveData.observe(this, this::onItemsResource);
        viewModel.loadItems();
    }

    private void onItemsResource(final Resource<DataResponse> resource) {
        if (resource.status == Resource.SUCCESS) {
            adapter.swapItems(resource.data.data);
        }
    }
}
