package com.example.sih2019;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Auction_page extends AppCompatActivity {

    private TextView txtDay,txtHour,txtMinutes,txtSeconds;
    private TextView tvEventStart;
    private Handler handler;
    private Runnable runnable;
    private Integer days;
    String day,hr,min,sec,num,refid;

    TextView txt;
    private final DatabaseReference ref =
            FirebaseDatabase.getInstance().getReference("/cars");

    public Integer bid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auction_detail_page);

        final Intent intent= getIntent();

         num= intent.getStringExtra("day");
         refid = intent.getStringExtra("id");
//        String maxp = intent.getStringExtra("maxp");
//        String minp = intent.getStringExtra("minp");
        Toast.makeText(this, refid, Toast.LENGTH_SHORT).show();
         days = Integer.parseInt(num);

        txtDay = (TextView) findViewById(R.id.tv_days);
        txtHour = (TextView) findViewById(R.id.tv_hour);
        txtMinutes = (TextView) findViewById(R.id.tv_minute);
        txtSeconds = (TextView) findViewById(R.id.tv_second);
        countDownStart();

        txt = findViewById(R.id.txt);
        // mDatabase = FirebaseDatabase.getInstance().getReference();
        final TextView bidamount =findViewById(R.id.bid);
        final Button b1000 =findViewById(R.id.bid_1000);
        final Button b2000 = findViewById(R.id.bid_2000);
        final Button b3000 =findViewById(R.id.bid_3000);
        final Button b0 = findViewById(R.id.bid_0);

        final Auction_ViewModel model = ViewModelProviders.of(this).get(Auction_ViewModel.class);


        b1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    ref.child(refid).child("bid").setValue(bid+1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

//                model.bid1000();
            }
        });

        b2000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                model.bid2000();

                try {
                    ref.child(refid).child("bid").setValue(bid+2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        b3000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                model.bid3000();

                try {
                    ref.child(refid).child("bid").setValue(bid+3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    bid=0;
                    ref.child(refid).child("bid").setValue(bid);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Toast.makeText(this, "jsjsjj"+refid, Toast.LENGTH_LONG).show();
//
        LiveData<DataSnapshot> liveData = model.getDataSnapshotLiveData();

        liveData.observe(this, new Observer<DataSnapshot>() {
            @Override
            public void onChanged(@Nullable DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    // update the UI here with values in the snapshot
                    bid = dataSnapshot.child(refid).child("bid").getValue(Integer.class);

                    bidamount.setText(bid.toString());
//                    tvTicker.setText(ticker);
//                    Float price = dataSnapshot.child("price").getValue(Float.class);
//                    tvPrice.setText(String.format(Locale.getDefault(), "%.2f", price));
                }
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


