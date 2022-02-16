package com.example.aplicationaluguerbicicleta;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.aplicationaluguerbicicleta.databinding.ActivityVisualizarEstacoesBicicletaBinding;

public class Visualizar_Estacoes_Bicicleta extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityVisualizarEstacoesBicicletaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityVisualizarEstacoesBicicletaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng estacao1 = new LatLng(16.864306921743868, -24.986002642863692);
        mMap.addMarker(new MarkerOptions().position(estacao1).title("ESTAÇÃO BICICLETA ISECMAR"));

        LatLng estacao2 = new LatLng(16.88684422202, -24.989640973127994);
        mMap.addMarker(new MarkerOptions().position(estacao2).title("ESTAÇÃO BICICLETA PONTO D`AGUA"));

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

    }
}