package com.example.sih2019;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import android.os.Handler;
import android.widget.Toast;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyHoder> implements View.OnClickListener{



    public Integer bid;

    List<Firemodel> list;
    Context context;
    String day,hr,min,sec,out;
    public Handler handler;



    public RecycleAdapter(List<Firemodel> list, Context context) {
        this.list = list;
        this.context = context;

    }


    @Override
    public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.fragment_recyclelist,parent,false);
        MyHoder myHoder = new MyHoder(view);
        return myHoder;
    }



    @Override
    public void onBindViewHolder(final MyHoder holder, final int position) {

                final Firemodel mylist = list.get(position);
                holder.model.setText(mylist.getModel());
                holder.kms.setText(mylist.getKms());
                holder.fuel.setText(mylist.getFuel());
                holder.year.setText(mylist.getYear());
                Picasso.with(context)
                        .load(mylist.getLink())
                        .into(holder.link);
                holder.number.setText(mylist.getNumber());
                bid = mylist.getBid();
                holder.bidP.setText(bid.toString());
                holder.max.setText(mylist.getPushmax_price().toString());
                holder.min.setText(mylist.getPushmin_price().toString());


                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    public void run() {

                        // do something
                        handler = new Handler();
                        Runnable runnable = new Runnable() {
                            @Override
                            public void run() {
                                handler.postDelayed(this, 1000);

                                try {
                                    String no = holder.number.getText().toString();
                                    int no1 = Integer.parseInt(no);
                                    Calendar c = Calendar.getInstance();
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    String reg_date = sdf.format(new Date());
                                    c.add(Calendar.DATE, no1);  // number of days to add
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
                                        String time = day+ hr+ min +sec;
                                        out = time.replaceAll("..(?!$)","$0:");
                                        holder.counter.setText(out);
                                    } else {

                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        handler.postDelayed(runnable, 1 * 1000);

                    }
                });
          holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailView.class);
                intent.putExtra("name",list.get(position).getModel());
                intent.putExtra("link",list.get(position).getLink());
                intent.putExtra("fclink",list.get(position).getFcLink());
                intent.putExtra("inlink",list.get(position).getInLink());
                intent.putExtra("brand",list.get(position).getBrand());
                intent.putExtra("km",list.get(position).getKms());
                intent.putExtra("fuel",list.get(position).getFuel());
                intent.putExtra("mileage",list.get(position).getMileage());
                intent.putExtra("serviceno",list.get(position).getServiceno());
                intent.putExtra("year",list.get(position).getYear());
                intent.putExtra("number",list.get(position).getNumber());
                intent.putExtra("enddate",list.get(position).getEnd_date());
                intent.putExtra("reg",list.get(position).getReg_date());
                intent.putExtra("max",list.get(position).getPushmax_price());
                intent.putExtra("min",list.get(position).getPushmin_price());
                intent.putExtra("id",list.get(position).getId());


                context.startActivity(intent);
            }
        });



            }

    @Override
    public int getItemCount() {

        int arr = 0;

        try{
            if(list.size()==0){

                arr = 0;

            }
            else{

                arr=list.size();
            }



        }catch (Exception e){



        }

        return arr;

    }

    @Override
    public void onClick(View view) {

    }

    class MyHoder extends RecyclerView.ViewHolder{
        TextView model,kms,year,fuel,maxP,counter,number,bidP,min,max;
        ImageView link;

        public MyHoder(View itemView) {
            super(itemView);
            model =  itemView.findViewById(R.id.carname);
            kms=  itemView.findViewById(R.id.kms);
            year=  itemView.findViewById(R.id.year);
            fuel=  itemView.findViewById(R.id.fueltype);
            link = itemView.findViewById(R.id.img);
            counter = itemView.findViewById(R.id.counter);
            number = itemView.findViewById(R.id.number);
            bidP = itemView.findViewById(R.id.bidP);
            max = itemView.findViewById(R.id.max);
            min = itemView.findViewById(R.id.min);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("tag",getAdapterPosition()+"clicked");
//                    Integer i = getAdapterPosition();
//
//                   // Intent intent = new Intent(context,DetailView.class);

                }
            });


        }

        }


    }

