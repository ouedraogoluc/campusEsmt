package com.example.campusesmt;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class UniversityFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_university, container, false);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return  view;
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
        LatLng esmtSN = new LatLng(13.6589267, -17.6166701);
        mMap.addMarker(new MarkerOptions().position(esmtSN).title("ESMT-SENEGAL").snippet("Tel:+221338690300,Site:esmt.sn")) ;
        LatLng ucad = new LatLng(14.6974508, -17.4715337);
        mMap.addMarker(new MarkerOptions().position(ucad).title("UCAD").snippet("Tel:+221338690300,Site:ucad.sn")) ;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(esmtSN, 12));
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker.getTitle().equals("ESMT-SENEGAL")){
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:778165690"));
                    startActivity(intent);
                }else if (marker.getTitle().equals("UCAD")){
                    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:778165690"));
                    intent.putExtra("sms_body","Hello ucad team ,are you ready for project?");
                    startActivity(intent);
                }
                return false;
            }
        });
    }
}