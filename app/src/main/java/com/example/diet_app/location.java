package com.example.diet_app;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.diet_app.databinding.ActivityLocationBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class location extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMarkerDragListener ,GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener{

    private GoogleMap mMap;
    private ActivityLocationBinding binding;
    private Marker marker;
    String address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.view_map);
        assert mapFragment != null;
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
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        LatLng m = new LatLng(37.55827, 126.998425);

        marker = mMap.addMarker(new MarkerOptions()
                .position(m)
                .draggable(true)
                .title("저장하기")
        );

        mMap.moveCamera(CameraUpdateFactory.newLatLng(m));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(m));
        mMap.setOnMarkerDragListener(this);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
    }
    public String getAddress(Context mContext, double lat, double lng)
    {
        String nowAddr ="현재 위치를 확인 할 수 없습니다.";
        Geocoder geocoder = new Geocoder(mContext, Locale.KOREA);
        List<Address> address;
        try
        {
            address = geocoder.getFromLocation(lat, lng, 1);
            if (address != null && address.size() > 0)
            {
                nowAddr = address.get(0).getAddressLine(0).toString();
            }
        }
        catch (IOException e)
        {
            Toast.makeText(mContext, "주소를 가져 올 수 없습니다.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return nowAddr;
    }

    @Override
    public void onMarkerDrag(@NonNull Marker marker) {}
    @Override
    public void onMarkerDragStart(@NonNull Marker marker) {}
    @Override
    public void onMarkerDragEnd(@NonNull Marker marker) {
        Toast.makeText(this, "position : "+marker.getPosition(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInfoWindowClick(@NonNull Marker marker) {
        Intent intent = new Intent();

        intent.putExtra("location",address);
        setResult(RESULT_OK,intent);
        Toast.makeText(this, "위치가 저장되었습니다.",Toast.LENGTH_LONG).show();
        finish();
    }


    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        address = getAddress(this,
                marker.getPosition().latitude,marker.getPosition().longitude);
        if (address == "현재 위치를 확인 할 수 없습니다." || address == "주소를 가져 올 수 없습니다."){
            Toast.makeText(this, "이곳의 주소를 알 수 없습니다!",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "이곳은 "+address+"입니다!",Toast.LENGTH_LONG).show();
        }
        return false;
    }
}