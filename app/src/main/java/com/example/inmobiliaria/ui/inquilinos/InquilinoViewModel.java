package com.example.inmobiliaria.ui.inquilinos;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.inmobiliaria.modelo.Inmueble;
import com.example.inmobiliaria.modelo.Inquilino;
import com.example.inmobiliaria.request.ApiClient;


public class InquilinoViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<Inquilino> inquilino;

    public LiveData<Inquilino> getInquilino() {
        if (inquilino == null) {
            inquilino = new MutableLiveData<>();
        }
        return inquilino;
    }

    public void cargarInquilino(Bundle bundle) {
        Inmueble inmueble = (Inmueble) bundle.get("inmueble");
        Inquilino inquilino= ApiClient.getApi().obtenerInquilino(inmueble);

        this.inquilino.setValue(inquilino);
    }
}