package com.example.inmobiliaria.ui.pagos;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobiliaria.modelo.Contrato;
import com.example.inmobiliaria.modelo.Pago;
import com.example.inmobiliaria.request.ApiClient;

import java.util.ArrayList;


public class PagoViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<ArrayList<Pago>> pagos;

    public LiveData<ArrayList<Pago>> getPagos () {
        if (pagos == null) {
            pagos = new MutableLiveData<>();
        }
        return pagos;
    }
    public void cargarPagos(Bundle bundle) {
        //Acá recibiríamos un inmueble o Id de inmueble, y buscaríamos en la base de datos el contrato vigente de ese inmueble y sus pagos
        //En caso de no existir, la vista mostraría un mensaje (Si partimos de la pestaña de contratos se supone que el inmueble tiene un contrato y pagos vigente)
        Contrato contrato = (Contrato) bundle.get("contrato");
        ApiClient apiClient= ApiClient.getApi();
        ArrayList<Pago> pagos = apiClient.obtenerPagos(contrato);
        this.pagos.setValue(pagos);
    }

}