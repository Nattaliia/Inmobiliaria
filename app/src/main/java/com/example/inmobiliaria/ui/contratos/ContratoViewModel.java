package com.example.inmobiliaria.ui.contratos;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliaria.modelo.Contrato;
import com.example.inmobiliaria.modelo.Inmueble;
import com.example.inmobiliaria.request.ApiClient;


public class ContratoViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<Contrato> contrato;

    public ContratoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Contrato> getContrato() {
        if (contrato == null) {
            contrato = new MutableLiveData<>();
        }
        return contrato;
    }

    public void cargarContrato(Bundle bundle) {
        Inmueble inmueble = (Inmueble) bundle.get("inmueble");
        ApiClient apiClient=ApiClient.getApi();
        Contrato contrato=apiClient.obtenerContratoVigente(inmueble);

        this.contrato.setValue(contrato);
    }


}