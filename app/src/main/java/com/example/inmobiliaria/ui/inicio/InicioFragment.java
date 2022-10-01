package com.example.inmobiliaria.ui.inicio;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.inmobiliaria.R;
import com.example.inmobiliaria.ui.inmuebles.InmuebleFragment;
import com.google.android.gms.maps.SupportMapFragment;


public class InicioFragment extends Fragment {

    private InicioViewModel mViewModel;


    public static InicioFragment newInstance() {
        return new InicioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

       View root =  inflater.inflate(R.layout.inicio_fragment, container, false);

        mViewModel =  new ViewModelProvider(this).get(InicioViewModel.class);
        mViewModel.getMutableMapa().observe(getViewLifecycleOwner(), new Observer<LeerMapa>() {
            @Override
            public void onChanged(LeerMapa leerMapa) {
                SupportMapFragment mapFragment =
                        (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapa);
                if (mapFragment != null) {
                    mapFragment.getMapAsync(leerMapa);
                }
            }
        });
        mViewModel.leerMapa();
        return root;

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }

}