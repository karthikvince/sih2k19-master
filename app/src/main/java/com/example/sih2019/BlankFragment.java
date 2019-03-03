package com.example.sih2019;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class BlankFragment extends Fragment  {

    Button car;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_blank,
                container, false);
        car = view.findViewById(R.id.btncar);
        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                swapfragment();
            }
        });

        return view;
    }

    public void swapfragment() {
        Fragment fragment = new Tab2Fragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainin,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


    }





}
