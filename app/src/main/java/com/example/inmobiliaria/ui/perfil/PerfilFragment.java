package com.example.inmobiliaria.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.inmobiliaria.R;
import com.example.inmobiliaria.modelo.Propietario;


public class PerfilFragment extends Fragment {

    private PerfilViewModel vmPerfil;
    private EditText etId,etNombre,etApellido,etDni,etEmail,etTelefono,etPasswordEditar;
    private ImageView ivAvatar;
    private Button btAccion;
    private int idAvatar;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vmPerfil = new ViewModelProvider(this).get(PerfilViewModel.class);
        View view = inflater.inflate(R.layout.perfil_fragment, container, false);
        vmPerfil.getMutablePropietario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                etId.setText(propietario.getId()+"");
                etNombre.setText(propietario.getNombre());
                etApellido.setText(propietario.getApellido());
                etDni.setText(propietario.getDni()+"");
                etTelefono.setText(propietario.getTelefono());
                etEmail.setText(propietario.getEmail());
                etPasswordEditar.setText(propietario.getContraseña());
                ivAvatar.setImageResource(propietario.getAvatar());
                idAvatar= propietario.getAvatar();
            }
        });
        vmPerfil.getMutablePropiedadEnable().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean valor) {

                etNombre.setEnabled(valor);
                etApellido.setEnabled(valor);
                etDni.setEnabled(valor);
                etTelefono.setEnabled(valor);

            }
        });
        vmPerfil.getMutableTextoBoton().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String textoBoton) {
                btAccion.setText(textoBoton);
            }
        });
        inicializarVista(view);
        return view;
    }
    public void inicializarVista(View view){
        etId=view.findViewById(R.id.etId);
        etNombre = view.findViewById(R.id.etNombre);
        etApellido= view.findViewById(R.id.etApellido);
        etDni= view.findViewById(R.id.etDni);
        etTelefono= view.findViewById(R.id.etTelefono);
        etEmail=view.findViewById(R.id.etEmail);
        etPasswordEditar= view.findViewById(R.id.etPasswordEditar);
        ivAvatar=view.findViewById(R.id.ivAvatar);
        btAccion= view.findViewById(R.id.btAccion);
        vmPerfil.ObtenerUsuario();
        btAccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoBoton=  btAccion.getText().toString();

                int id = Integer.parseInt(etId.getText().toString());
                String nombre = etNombre.getText().toString();
                String apellido= etApellido.getText().toString();
                long dni=Long.parseLong(etDni.getText().toString()) ;
                String telefono= etTelefono.getText().toString();
                String email= etEmail.getText().toString();
                String password= etPasswordEditar.getText().toString();

                Propietario p = new Propietario(id,dni,nombre,apellido,email,password,telefono,idAvatar);
                vmPerfil.actualizarPropietario(textoBoton,p);
            }
        });

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}