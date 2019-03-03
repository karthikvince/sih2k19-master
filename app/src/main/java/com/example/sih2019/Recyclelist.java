package com.example.sih2019;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;



public class Recyclelist extends Fragment {

    FirebaseDatabase database;
    DatabaseReference myRef ;
    List<Firemodel> list;
    RecyclerView recycle;


    public Recyclelist() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.card,
                container, false);
        final RecyclerView recycle = view.findViewById(R.id.recycle);
        recycle.setHasFixedSize(true);

        // Fetching data from server
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("cars");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                list = new ArrayList<Firemodel>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot
                        .getChildren()) {


                    Firemodel value = dataSnapshot1.getValue(Firemodel.class);
                    Firemodel fire = new Firemodel();
                    String name = value.getModel();
                    String kms = value.getKms();
                    String year = value.getYear();
                    String fuel = value.getFuel();
                    String link = value.getLink();
                    String number = value.getNumber();
                    Integer bid = value.getBid();
                    String serviceno = value.getServiceno();
                    String mileage = value.getMileage();
                    String fcLink = value.getFcLink();
                    String inLink = value.getInLink();
                    String brand =value.getBrand();
                    String id = value.getId();
                    Double pushmax_price = value.getPushmax_price();
                    Double pushmin_price = value.getPushmin_price();
                    String end_date = value.end_date;
                    String reg_date =value.reg_date;
                    fire.setModel(name);
                    fire.setBrand(brand);
                    fire.setKms(kms);
                    fire.setYear(year);
                    fire.setFuel(fuel);
                    fire.setLink(link);
                    fire.setNumber(number);
                    fire.setBid(bid);
                    fire.setFcLink(fcLink);
                    fire.setInLink(inLink);
                    fire.setMileage(mileage);
                    fire.setServiceno(serviceno);
                    fire.setEnd_date(end_date);
                    fire.setReg_date(reg_date);
                    fire.setId(id);
                    fire.setPushmax_price(pushmax_price);
                    fire.setPushmin_price(pushmin_price);

                    list.add(fire);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RecycleAdapter recyclerAdapter = new RecycleAdapter(list, getActivity());
                RecyclerView.LayoutManager recyce = new LinearLayoutManager(getActivity());
                recycle.setLayoutManager(recyce);
                //  recycle.setItemAnimator( new DefaultItemAnimator());
                recycle.setAdapter(recyclerAdapter);

            }
        });

                return view;

            }

}
