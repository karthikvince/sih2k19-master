package com.example.sih2019;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Auction_ViewModel extends ViewModel {

    private static final DatabaseReference HOT_STOCK_REF =
            FirebaseDatabase.getInstance().getReference("/cars");

    private final FirebaseQueryLiveData liveData = new FirebaseQueryLiveData(HOT_STOCK_REF);

    @NonNull
    public LiveData<DataSnapshot> getDataSnapshotLiveData() {
        return liveData;
    }


//    private String TAG = this.getClass().getSimpleName();
//
//    private int count = 0;
//
//    private MutableLiveData<Integer> bid;
//
//    public LiveData<Integer> getbid() {
//
//        if (bid == null) {
//            bid = new MutableLiveData<>();
////
//        }
//
//        return bid;
//    }
//
////    public void fetch(){
////        Random random = new Random();
////        bid.setValue(""+(random.nextInt(10 - 1) + 1));
////    }
//
//    public void bid1000(){
//        liveData +=1000;
//        bid.setValue(count);
//    }
//
//    public void bid2000() {
//        count +=2000;
//        bid.setValue(count);
//    }
//
//    public void bid3000(){
//        count +=3000;
//        bid.setValue(count);
//    }
//
//    @Override
//    protected void onCleared() {
//        super.onCleared();
//        Log.i(TAG,"cleared");
//    }
}