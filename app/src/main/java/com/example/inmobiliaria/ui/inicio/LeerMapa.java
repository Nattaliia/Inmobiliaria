package com.example.inmobiliaria.ui.inicio;

import android.content.Context;


import androidx.annotation.NonNull;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LeerMapa implements OnMapReadyCallback {
    static final LatLng INMOVP = new LatLng(-32.913044, -66.085106);
    static final LatLng CASA = new LatLng(-32.898877, -66.109481);
    private GoogleMap mapa;
    private Context context;

    public LeerMapa(Context context){
        this.context = context;
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mapa = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        mapa.addMarker(new MarkerOptions().position(INMOVP))

                .setTitle("Camargo_Inmobiliaria");


        CameraPosition camPos = new CameraPosition.Builder()
                .target(INMOVP)
                .zoom(19)
                .bearing(45)
                .tilt(70)
                .build();

        CameraUpdate ca = CameraUpdateFactory.newCameraPosition(camPos);
        mapa.animateCamera(ca);

        mapa .addMarker(new MarkerOptions().position(CASA))
                .setTitle(("Ubicacion Actual"));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(CASA)
                .zoom(19)
                .bearing(45)
                .tilt(70)
                .build();

        CameraUpdate caU = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mapa.animateCamera(caU);
        //obtenerUltimaUbicacion();
    }



    /*private void obtenerUltimaUbicacion() {
        FusedLocationProviderClient fl = LocationServices.getFusedLocationProviderClient(context);
        Log.d("salida", "Entrando");
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            fl.getLastLocation().addOnSuccessListener(context.getMainExecutor(), new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    Log.d("salida", "En el if");
                    if (location != null) {
                        LatLng CASA = new LatLng(location.getLatitude(),location.getLongitude());
                        mapa.addMarker(new MarkerOptions().position(CASA))
                                .setTitle("Ubicacion Actual");
                        Log.d("salida", "Salida");

                        CameraPosition cam = new CameraPosition.Builder()
                                .target(CASA)
                                .zoom(17)
                                .bearing(8)
                                .build();
                        CameraUpdate caU = CameraUpdateFactory.newCameraPosition(cam);
                        mapa.animateCamera(caU);
                    }

                }

            });
        }


    }*/
}

