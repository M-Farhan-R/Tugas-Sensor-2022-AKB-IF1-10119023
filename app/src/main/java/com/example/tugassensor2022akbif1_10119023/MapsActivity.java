package com.example.tugassensor2022akbif1_10119023;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.tugassensor2022akbif1_10119023.databinding.ActivityMapsBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

/*
Nama: Muhammad Farhan R
NIM : 10119023
Kelas : IF-1
 */

public class MapsActivity extends Fragment implements OnMapReadyCallback{

    private GoogleMap mMap;
    private FusedLocationProviderClient client;
    private MapView mapView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
        getCurrentLocation();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_maps, container, false);
        client = LocationServices.getFusedLocationProviderClient(getActivity());
        return view;
    }


    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    mapView.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {
                            LatLng lokasi = new LatLng(location.getLatitude(),location.getLongitude());

                            MarkerOptions options = new MarkerOptions().position(lokasi).title("Lokasi Saat Ini");

                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lokasi,13));
                            mMap.addMarker(options.icon(BitmapDescriptorFactory.
                                    defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        List<LatLng> resto = new ArrayList<LatLng>();
        resto.add(new LatLng(-6.95766584638914, 107.68025879908926));
        resto.add(new LatLng(-6.943018422846031, 107.66708848796797));
        resto.add(new LatLng(-6.934138655754286, 107.67974764049852));
        resto.add(new LatLng(-6.940613826711624, 107.66735334005831));
        resto.add(new LatLng(-6.950986296239553, 107.6848551913552));

        List<MarkerOptions> mark = new ArrayList<MarkerOptions>();
        mark.add(new MarkerOptions().position(resto.get(0)).title("Nasi Goreng Kemuning"));
        mark.add(new MarkerOptions().position(resto.get(1)).title("Ngikan Yuk Bintara"));
        mark.add(new MarkerOptions().position(resto.get(2)).title("Kebab 88"));
        mark.add(new MarkerOptions().position(resto.get(3)).title("Ayam Crisbar"));
        mark.add(new MarkerOptions().position(resto.get(4)).title("Mie Ayam Bakso"));

        for (MarkerOptions marker : mark) {
            mMap.addMarker(marker);
        }
    }
}