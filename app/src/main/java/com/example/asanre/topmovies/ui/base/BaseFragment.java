package com.example.asanre.topmovies.ui.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;

    protected void prepareView(View view) {

    }

    protected void showLoading() {

    }

    protected void hideLoading() {

    }

    public boolean isSafeManipulateView() {

        return isAdded() && getActivity() != null && !getActivity().isFinishing();
    }

    protected abstract int getFragmentLayout();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(getFragmentLayout(), container, false);

        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        prepareView(view);
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();
        unbinder.unbind();
    }
}