package com.example.sih2019;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class BlankFragment3 extends Fragment {
    TextView txt;
    private final DatabaseReference ref =
            FirebaseDatabase.getInstance().getReference("/cars");

    public Integer bid;


    public BlankFragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.auction_detail_page, container, false);
//        txt = view.findViewById(R.id.txt);
//       // mDatabase = FirebaseDatabase.getInstance().getReference();
//        final TextView bidamount = view.findViewById(R.id.bid);
//        final Button b1000 = view.findViewById(R.id.bid_1000);
//        final Button b2000 = view.findViewById(R.id.bid_2000);
//        final Button b3000 = view.findViewById(R.id.bid_3000);
//        final Button b0 = view.findViewById(R.id.bid_0);
//
//        final Auction_ViewModel model = ViewModelProviders.of(this).get(Auction_ViewModel.class);
//
//
//        b1000.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                try {
//                    ref.child("-LZyY2xBe1aNvY_-3kTN").child("bid").setValue(bid+1000);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
////                model.bid1000();
//            }
//        });
//
//        b2000.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                model.bid2000();
//
//                try {
//                    ref.child("-LZyY2xBe1aNvY_-3kTN").child("bid").setValue(bid+2000);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        b3000.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                model.bid3000();
//
//                try {
//                    ref.child("-LZyY2xBe1aNvY_-3kTN").child("bid").setValue(bid+3000);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        b0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                try {
////                    bid=0;
////                    ref.child("-LZyY2xBe1aNvY_-3kTN").child("bid").setValue(bid);
////                } catch (Exception e) {
////                    e.printStackTrace();
////                }
//            }
//        });
////
//        LiveData<DataSnapshot> liveData = model.getDataSnapshotLiveData();
//
//        liveData.observe(this, new Observer<DataSnapshot>() {
//            @Override
//            public void onChanged(@Nullable DataSnapshot dataSnapshot) {
//                if (dataSnapshot != null) {
//                    // update the UI here with values in the snapshot
////                    bid = dataSnapshot.child("-LZyY2xBe1aNvY_-3kTN").child("bid").getValue(Integer.class);
////
////                    bidamount.setText(bid.toString());
////                    tvTicker.setText(ticker);
////                    Float price = dataSnapshot.child("price").getValue(Float.class);
////                    tvPrice.setText(String.format(Locale.getDefault(), "%.2f", price));
//                }
//            }
//        });
//
//
//

        return view;
    }

}
