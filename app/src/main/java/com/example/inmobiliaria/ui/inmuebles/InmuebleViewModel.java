package com.example.inmobiliaria.ui.inmuebles;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria.modelo.Inmueble;
import com.example.inmobiliaria.modelo.Inquilino;
import com.example.inmobiliaria.request.ApiClient;

public class InmuebleViewModel extends ViewModel {

    private MutableLiveData<Inmueble> inmueble;


    public InmuebleViewModel() {
        super();
    }
    public LiveData<Inmueble> getInmueble() {
        if (inmueble == null) {
            inmueble = new MutableLiveData<>();
        }
        return inmueble;
    }

    public void cargarInmuebles(Bundle bundle) {
        Inmueble inmueble = (Inmueble) bundle.getSerializable("inmueble");

        this.inmueble.setValue(inmueble);

    }
}
