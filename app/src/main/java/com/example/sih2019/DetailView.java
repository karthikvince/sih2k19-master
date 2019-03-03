package com.example.sih2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import android.os.Handler;
import android.widget.Toast;

public class DetailView extends AppCompatActivity {
    Button auction;
TextView brand,model,mileage,km,year,serviceno,fuel,number,regdate,enddate,minP,maxP;
ImageView img,fc,in;

    private TextView txtDay,txtHour,txtMinutes,txtSeconds;
    private TextView tvEventStart;
    private Handler handler;
    private Runnable runnable;
    private Integer days;
    String day,hr,min,sec;
    String refid;

    private final DatabaseReference ref =
            FirebaseDatabase.getInstance().getReference("/cars");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view);

        ref.child(refid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Firemodel user = dataSnapshot.getValue(Firemodel.class);
                minP.setText(user.getPushmin_price().toString());
                maxP.setText(user.getPushmax_price().toString());

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });


        txtDay = (TextView) findViewById(R.id.tv_days);
        txtHour = (TextView) findViewById(R.id.tv_hour);
        txtMinutes = (TextView) findViewById(R.id.tv_minute);
        txtSeconds = (TextView) findViewById(R.id.tv_second);

        final Intent intent= getIntent();
        String modelS = intent.getStringExtra("name");
        String links = intent.getStringExtra("link");
        String fclinks = intent.getStringExtra("fclink");
        String inlinks = intent.getStringExtra("inlink");
        String brandS = intent.getStringExtra("brand");
        String kms = intent.getStringExtra("km");
        String fuels = intent.getStringExtra("fuel");
        String mileages = intent.getStringExtra("mileage");
        String servicenos = intent.getStringExtra("serviceno");
        final String numbers = intent.getStringExtra("number");
        String enddates = intent.getStringExtra("enddate");
        String reg = intent.getStringExtra("reg");
        String years = intent.getStringExtra("year");
        String maxp = intent.getStringExtra("max");
        String minp = intent.getStringExtra("min");
        refid = intent.getStringExtra("id");
        Toast.makeText(this, refid, Toast.LENGTH_SHORT).show();
        countDownStart();
        minP = findViewById(R.id.min);
        maxP = findViewById(R.id.max);
        auction = findViewById(R.id.auction);
        brand = findViewById(R.id.brandcar);
        number = findViewById(R.id.Number);
        regdate = findViewById(R.id.Reg);
        enddate = findViewById(R.id.End_date);
        model = findViewById(R.id.Model);
        mileage =findViewById(R.id.Mileage);
        km = findViewById(R.id.km);
        year = findViewById(R.id.Registration_date);
        serviceno = findViewById(R.id.Service_number);
        fuel =findViewById(R.id.Fuel);
        img = findViewById(R.id.Car_image);
        fc = findViewById(R.id.fc);
        in = findViewById(R.id.in);
        brand.setText(brandS);
        km.setText(kms);
        model.setText(modelS);
        year.setText(years);
        fuel.setText(fuels);
        mileage.setText(mileages);
        serviceno.setText(servicenos);
        number.setText(numbers);
        days = Integer.parseInt(numbers);
        enddate.setText(enddates);
        regdate.setText(reg);
//        maxP.setText(maxp);
//        minP.setText(minp);
        Picasso.with(DetailView.this).load(links).into(img);
        Picasso.with(DetailView.this).load(fclinks).resize(520,520).into(fc);
        Picasso.with(DetailView.this).load(inlinks).resize(520,520).into(in);

        auction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(DetailView.this,Auction_page.class);
                intent1.putExtra("day",numbers);
                intent1.putExtra("id",refid);
                startActivity(intent1);

            }
        });

    }

    public void countDownStart() {
       handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String reg_date = sdf.format(new Date());
                    c.add(Calendar.DATE, days);  // number of days to add
                    String futureDate1 = sdf.format(c.getTime());
                    // Toast.makeText(MainActivity.this,st,Toast.LENGTH_LONG).show();
                    Date currentDate = new Date();
                    Date futureDate = sdf.parse(futureDate1);

                    if (!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime()
                                - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;
                        day = String.format("%02d",days);
                        hr = String.format("%02d",hours);
                        min = String.format("%02d",minutes);
                        sec = String.format("%02d",seconds);
                        // String time = day+ hr+ min +sec;
                        // String out = time.replaceAll("..(?!$)","$0:");
                        // txtDay.setText(out);

                        txtDay.setText(day);
                        txtHour.setText(hr);
                        txtMinutes.setText(min);
                        txtSeconds.setText(sec);
                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1 * 1000);
    }

}
