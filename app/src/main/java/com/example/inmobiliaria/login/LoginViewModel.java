package com.example.inmobiliaria.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobiliaria.MainActivity;
import com.example.inmobiliaria.modelo.Propietario;
import com.example.inmobiliaria.request.ApiClient;


public class LoginViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> error_visibility;
    private Context context;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public LiveData<Integer> getErrorVisibility() {
        if (error_visibility == null) {
            error_visibility = new MutableLiveData<>();
        }
        return error_visibility;
    }
    public void login(String email, String pass) {
        ApiClient api = ApiClient.getApi();
        Propietario p = api.login(email, pass);

        if (p != null) {
            error_visibility.setValue(View.INVISIBLE);
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        } else {
            error_visibility.setValue(View.VISIBLE);
        }
    }

}
