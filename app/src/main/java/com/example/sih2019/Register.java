package com.example.sih2019;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity  {
    EditText email;
    EditText phone;
    EditText name;
    TextInputLayout email_lay;
    TextInputLayout no_lay;
    TextInputLayout name_lay;
    TextView tt;
    Button sign;
    String val_name, val_email, val_no;
    Integer no;
    DatabaseReference databaseReference;
    LocationManager locationManager;
//    Button getLocationBtn;
//    TextView locationText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        email = findViewById(R.id.email);
        phone = findViewById(R.id.no);
        name = findViewById(R.id.name);
        email_lay = findViewById(R.id.email_lay);
        no_lay = findViewById(R.id.no_lay);
        name_lay = findViewById(R.id.name_lay);
        tt = findViewById(R.id.tt);
//        locationText = findViewById(R.id.txt);
//        getLocationBtn = findViewById(R.id.btn);
        sign = findViewById(R.id.sign);


//        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);}
//        getLocationBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               getLocation();
//            }
//        });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                android.net.NetworkInfo wifi = cm
                        .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                android.net.NetworkInfo data = cm
                        .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                if ((wifi != null & data != null)
                        && (wifi.isConnected() | data.isConnected())) {
                    //connection is available
                    //validate
                    val_email = email.getText().toString();
                    val_name = name.getText().toString();
                    val_no = phone.getText().toString();
                    Log.d("tag", val_email);
                    validate(val_email, val_name, val_no);

                } else {
                    Toast.makeText(getApplicationContext(), "no internet connection", Toast.LENGTH_LONG).show();
                }
            }
        });
        tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, SignUp.class);
                startActivity(i);
            }
        });
    }

//    public void getLocation() {
//        try {
//            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
//        }
//        catch(SecurityException e) {
//            e.printStackTrace();
//        }
//    }

    private void validate(String email,String name,String no) {
        boolean f =true;
        String s = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        Log.d("vallidbefore",email);
        if(email.matches(s)){
            Log.d("valid",email);
            email_lay.setError(null);
        }
        else{
            email_lay.setError("Invalid EmailAddress");
            f = false;
        }
        String na = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$";
        if(name.matches(na)){
            name_lay.setError(null);
        }
        else if (name.length()==0){
            name_lay.setError("Please fill the field");
            f=false;
        }
        else{
            name_lay.setError("Invalid name");
            f = false;
        }
        int len = String.valueOf(no).length();
        if (len == 10 && !Pattern.matches("[a-zA-Z]+", no)){
            no_lay.setError(null);
        }
        else {
            no_lay.setError("Invalid phone no");
            f = false;
        }
        if(f == true){
            String id = databaseReference.push().getKey();
            User user = new User(name,email,no);
            databaseReference.child(id).setValue(user);
            Intent i = new Intent(Register.this, SignUp.class);
            startActivity(i);
        }
    }

//    @Override
//    public void onLocationChanged(Location location) {
//        locationText.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());
//
//        try {
//            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
//            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//            locationText.setText(locationText.getText() + "\n"+addresses.get(0).getAddressLine(0)+", "+
//                    addresses.get(0).getAddressLine(1)+", "+addresses.get(0).getAddressLine(2));
//        }catch(Exception e)
//        {
//
//        }
//    }

//    @Override
//    public void onStatusChanged(String s, int i, Bundle bundle) {
//
//    }
//
//    @Override
//    public void onProviderEnabled(String s) {
//
//    }
//
//    @Override
//    public void onProviderDisabled(String s) {
//        Toast.makeText(Register.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
//
//    }
}
