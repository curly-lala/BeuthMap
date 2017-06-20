package beuth.beuthmap;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.geojson.GeoJsonLayer;

import org.json.JSONException;

import java.io.IOException;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private GeoJsonLayer layer0;
    private GeoJsonLayer layer1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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
        LatLng beuth = new LatLng(52.544307, 13.352703);
        mMap.addMarker(new MarkerOptions().position(beuth).title("Beuth Hochschule für Technik Berlin"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(beuth));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setIndoorEnabled(false);
        mMap.setBuildingsEnabled(false);
    }

    public void onClick0(View view) {
        if (this.layer0 == null) {
            try {
                this.layer0 = new GeoJsonLayer(mMap, R.raw.level0, getApplicationContext());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

        }
        this.layer0.addLayerToMap();
        if (this.layer1 != null) { this.layer1.removeLayerFromMap(); }
    }


    public void onClick1(View view) {
        if (this.layer1 == null) {
            try {
                this.layer1 = new GeoJsonLayer(mMap, R.raw.level1, getApplicationContext());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

        }
        this.layer1.addLayerToMap();
        if(this.layer0 != null) { this.layer0.removeLayerFromMap(); }
    }

}
