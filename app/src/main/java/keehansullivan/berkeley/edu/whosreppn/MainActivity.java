package keehansullivan.berkeley.edu.whosreppn;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
//import android.location.LocationListener;
import com.google.android.gms.location.LocationListener;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.io.IOException;
import java.util.Random;


public class MainActivity extends AppCompatActivity  {
    private ImageButton zipButton;
    private Button randButton;
    private Button curButton;
    private EditText zipInput;
    private HashSet intList = new HashSet();
    private ArrayList<Integer> zipps = new ArrayList<>();
    private Location loc;
    private FusedLocationProviderClient locat;
    Random rzip = new Random();
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    private double longitude;
    public double latitude;
    private Integer zip = 0;
    Intent intent = new Intent();
    Bundle bundle = new Bundle();
    String source;
    TextView txt;

    private HashSet<Integer> zips;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        curButton = (Button) findViewById(R.id.curloc);
        randButton = (Button) findViewById(R.id.randloc);
        zipInput = (EditText) findViewById(R.id.editText);
        //txt = (TextView) findViewById(R.id.textView3);
        //txtCoordinates = (TextView) findViewById(R.id.textView6);
        curButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MainActivity.this.callPermissions();
                MainActivity.this.requestLocationUpdates();


               //source = "curButton";
                //System.out.println(longitude);
                //System.out.println(latitude);
                //openMain2Activity();

            }

        });














        String data;
        StringBuffer buff = new StringBuffer();
        InputStream is = MainActivity.this.getResources().openRawResource(R.raw.zipcodes);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        if (is != null) {
            try {
                while ((data = reader.readLine()) != null) {
                    buff.append(data);
                }
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String strZipCodes = buff.toString();
        String[] strList = strZipCodes.split(", ");




        for (String s : strList) {
            while (s.length() < 5) {
                s = "0" + s;
            }
            intList.add(Integer.valueOf(s));
            zipps.add(Integer.valueOf(s));
        }
        //for (String s: strList) zipps.add(Integer.valueOf(s));

        zipButton = (ImageButton) findViewById(R.id.zipCodeButton);
        zipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = zipInput.getText().toString().trim();
                if (temp.equals("")) {
                    Toast.makeText(MainActivity.this, "Enter a valid zip code", Toast.LENGTH_SHORT).show();
                } else if (intList.contains(Integer.parseInt(temp))) {
                    zip = Integer.parseInt(temp);
                    source = "zipButton";
                    openMain2Activity();
                } else {
                    Toast.makeText(MainActivity.this, "Enter a valid zip code", Toast.LENGTH_SHORT).show();

                }
            }
        });

        randButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = rzip.nextInt(zipps.size());
                zip = zipps.get(index); //This is my random zipcode
                System.out.println(zip);
                source = "randButton";
                openMain2Activity();


            }
        });





    }





    //ON CREATE ENDS HERE


    public void openMain2Activity () {
        intent = new Intent(MainActivity.this, Main2Activity.class);

        bundle = new Bundle();

        bundle.putInt("zip",zip);
        bundle.putDouble("longitude", longitude);
        bundle.putDouble("latitude", latitude);
        bundle.putString("source", source);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    public void requestLocationUpdates() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PermissionChecker.PERMISSION_GRANTED &&
                (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PermissionChecker.PERMISSION_GRANTED)) {


            fusedLocationProviderClient = new FusedLocationProviderClient(this);
            locationRequest = new LocationRequest();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setFastestInterval(2000);
            locationRequest.setInterval(4000);
            locationRequest.setNumUpdates(1);


            fusedLocationProviderClient.requestLocationUpdates(locationRequest, new LocationCallback() {

                @Override
                public void onLocationResult(LocationResult locationResult) {
                    super.onLocationResult(locationResult);
                    longitude = locationResult.getLastLocation().getLongitude();
                    latitude = locationResult.getLastLocation().getLatitude();
                    //txt.setText(longitude + "/" + latitude);
                    //System.out.println(longitude +"/" + latitude);
                    //txtCoordinates.setText(longitude +"/" + latitude);
                    source = "curButton";
                    System.out.println(longitude);
                    System.out.println(latitude);
                    openMain2Activity();
                }
            }, getMainLooper());

        } else {
            callPermissions();
        }
    }

    public void callPermissions() {
        Permissions.check(this/*context*/, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, "Location Access is required to use current location",
                new Permissions.Options().setSettingsDialogTitle("Warning").setRationaleDialogTitle("Info"),
                new PermissionHandler() {
                    @Override
                    public void onGranted() {
                        requestLocationUpdates();
                        // do your task.
                    }
                    @Override
                    public void onDenied(Context context, ArrayList<String> deniedPerimissions){
                        super.onDenied(context, deniedPerimissions);
                        callPermissions();

                    }
                });


    }
}