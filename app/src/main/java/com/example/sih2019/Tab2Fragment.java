package com.example.sih2019;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;



public class Tab2Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public Handler handler;
    EditText mileage1,min_price,max_price;
    EditText year1;
    EditText km;
    EditText URL1,In,Fc;
    Button sign,prediction;
    TextInputLayout Mileage_lay;
    Integer kms,decreament_point=0;
    public double pushmin_price,pushmax_price;
    String year,brand,mileage,serviceno,minimum_price,maximum_price;
    String model,link,fuel,number,inLink,fcLink;
    Button btnChoose,btnIn,btnFc,btnUpload,fcUpload,inUpload;
    ImageView imageView,inView,fcView;
    Uri filePath;
    final int PICK_IMAGE_REQUEST = 71;
    DatabaseReference databaseReference;
    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_layout,
                container, false);
//        return inflater.inflate(R.layout.fragment_layout, container, false);
        mileage1 = view.findViewById(R.id.mileage);
        min_price = view.findViewById(R.id.min_price);
        max_price = view.findViewById(R.id.max_price);
        In = view.findViewById(R.id.insurance);
        Fc = view.findViewById(R.id.fc);
        year1 = view.findViewById(R.id.year);
        prediction = view.findViewById(R.id.prediction);
        km = view.findViewById(R.id.km1);
        Mileage_lay = view.findViewById(R.id.Mileage_lay);
        sign = view.findViewById(R.id.sign);
        btnChoose =  view.findViewById(R.id.btnChoose);
        btnUpload = view.findViewById(R.id.btnUp);
        btnIn = view.findViewById(R.id.inchoose);
        inUpload =view.findViewById(R.id.inUpload);
        btnFc = view.findViewById(R.id.fcChoose);
        fcUpload = view.findViewById(R.id.fcUpload);
        imageView = view.findViewById(R.id.imgView);
        inView = view.findViewById(R.id.inView);
        fcView = view.findViewById(R.id.fcView);
        databaseReference = FirebaseDatabase.getInstance().getReference("cars");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        URL1 = view.findViewById(R.id.url);
        final Spinner spinner =  view.findViewById(R.id.fuel_spinner);
        final Spinner spinner1 =  view.findViewById(R.id.no);
        final Spinner Brand =  view.findViewById(R.id.Brand);
        final Spinner Model =  view.findViewById(R.id.ModelName);
        final Spinner Year =  view.findViewById(R.id.Year);
        final Spinner service = view.findViewById(R.id.service);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd,KK:mm:ss a");
        String reg_date = sdf.format(new Date());
        c.add(Calendar.DATE, 1);  // number of days to add
        String end_date = sdf.format(c.getTime());
       // Toast.makeText(getActivity(),end_date,Toast.LENGTH_LONG).show();
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.fuel_type, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(),
                R.array.number, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterBrand = ArrayAdapter.createFromResource(getActivity(),
                R.array.Brand, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapterModel = ArrayAdapter.createFromResource(getActivity(),
                R.array.Model, android.R.layout.simple_spinner_item);

        ArrayAdapter<CharSequence> adapterYear = ArrayAdapter.createFromResource(getActivity(),
                R.array.Year, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterService = ArrayAdapter.createFromResource(getActivity(),
                R.array.Service, android.R.layout.simple_spinner_item);

// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner1.setAdapter(adapter1);
        Brand.setAdapter(adapterBrand);
        Model.setAdapter(adapterModel);
        Year.setAdapter(adapterYear);
        service.setAdapter(adapterService);


        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
        btnFc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });
        btnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
        fcUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FcImage();
            }
        });
        inUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InImage();
            }
        });

        prediction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model = Model.getSelectedItem().toString();
                year = Year.getSelectedItem().toString();
                kms =Integer.parseInt(km.getText().toString());

                if (kms >= 100000){
                    decreament_point=2453;
                }

                if (model.equals("Zen")){

                    if (year.equals("2019")){
                        minimum_price=String.valueOf(579000-decreament_point);
                        maximum_price=String.valueOf(623000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);


                    }else if (year.equals("2018")){

                        minimum_price=String.valueOf(475000-decreament_point);
                        maximum_price=String.valueOf(510000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2017")){

                        minimum_price=String.valueOf(411000-decreament_point);
                        maximum_price=String.valueOf(442000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2016")){

                        minimum_price=String.valueOf(363000-decreament_point);
                        maximum_price=String.valueOf(391000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2015")){

                        minimum_price=String.valueOf(318000-decreament_point);
                        maximum_price=String.valueOf(342000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2014")){

                        minimum_price=String.valueOf(284000-decreament_point);
                        maximum_price=String.valueOf(306000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2013")){

                        minimum_price=String.valueOf(260000-decreament_point);
                        maximum_price=String.valueOf(279000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2012")){
                        minimum_price=String.valueOf(236000-decreament_point);
                        maximum_price=String.valueOf(253000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2011")){

                        minimum_price=String.valueOf(211000-decreament_point);
                        maximum_price=String.valueOf(227000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2010")){
                        minimum_price=String.valueOf(189000-decreament_point);
                        maximum_price=String.valueOf(203000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);


                    }else if (year.equals("2009")){

                        minimum_price=String.valueOf(171000-decreament_point);
                        maximum_price=String.valueOf(184000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2008")){

                        minimum_price=String.valueOf(151000-decreament_point);
                        maximum_price=String.valueOf(162000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2007")){

                        minimum_price=String.valueOf(132000-decreament_point);
                        maximum_price=String.valueOf(142000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2006")){
                        minimum_price=String.valueOf(113000-decreament_point);
                        maximum_price=String.valueOf(122000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2005")){

                        minimum_price=String.valueOf(103000-decreament_point);
                        maximum_price=String.valueOf(110000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2004")){
                        min_price.setText("no production");
                        max_price.setText("no production");

                    }else if (year.equals("2003")){

                        minimum_price=String.valueOf(91000-decreament_point);
                        maximum_price=String.valueOf(98000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }

                }else if (model.equals("Alto")){
                    if(year.equals("2019")){
                        min_price.setText("no production");
                        max_price.setText("no production");

                    } else if(year.equals("2018")){
                        min_price.setText("no production");
                        max_price.setText("no production");

                    } else if(year.equals("2017")){
                        min_price.setText("no production");
                        max_price.setText("no production");

                    } else if(year.equals("2016")){
                        min_price.setText("no production");
                        max_price.setText("no production");

                    } else if(year.equals("2015")){
                        min_price.setText("no production");
                        max_price.setText("no production");

                    } else if(year.equals("2014")){
                        min_price.setText("no production");
                        max_price.setText("no production");

                    }
                    else if (year.equals("2013")){

                        minimum_price=String.valueOf(282000-decreament_point);
                        maximum_price=String.valueOf(304000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2012")){
                        minimum_price=String.valueOf(240000-decreament_point);
                        maximum_price=String.valueOf(258000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2011")){

                        minimum_price=String.valueOf(228000-decreament_point);
                        maximum_price=String.valueOf(245000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2010")){
                        min_price.setText("no production");
                        max_price.setText("no production");



                    }else if (year.equals("2009")){

                        minimum_price=String.valueOf(189000-decreament_point);
                        maximum_price=String.valueOf(203000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2008")){

                        minimum_price=String.valueOf(166000-decreament_point);
                        maximum_price=String.valueOf(179000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2007")){

                        minimum_price=String.valueOf(152000-decreament_point);
                        maximum_price=String.valueOf(164000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2006")){
                        minimum_price=String.valueOf(125000-decreament_point);
                        maximum_price=String.valueOf(135000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2005")){

                        min_price.setText("no production");
                        max_price.setText("no production");

                    }else if (year.equals("2004")){
                        minimum_price=String.valueOf(101000-decreament_point);
                        maximum_price=String.valueOf(109000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);


                    }else if (year.equals("2003")){

                        minimum_price=String.valueOf(91000-decreament_point);
                        maximum_price=String.valueOf(97000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }


                }else if (model.equals("wagon")){
                     if (year.equals("2019")){

                        min_price.setText("no production");
                        max_price.setText("no production");

                    }else if (year.equals("2018")){

                         min_price.setText("no production");
                         max_price.setText("no production");

                     }else if (year.equals("2005")){

                         min_price.setText("no production");
                         max_price.setText("no production");

                     }else if (year.equals("2005")){

                         min_price.setText("no production");
                         max_price.setText("no production");

                     }
                    else if (year.equals("2006")){
                        minimum_price=String.valueOf(152000-decreament_point);
                        maximum_price=String.valueOf(163000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);


                    }else if (year.equals("2005")){

                        minimum_price=String.valueOf(132000-decreament_point);
                        maximum_price=String.valueOf(141000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2003")){

                        minimum_price=String.valueOf(107000-decreament_point);
                        maximum_price=String.valueOf(115000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }


                }else if (model.equals("Gypsy")){
                    if (year.equals("2012")){
                        minimum_price=String.valueOf(387000-decreament_point);
                        maximum_price=String.valueOf(416000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);


                    }else if (year.equals("2006")){

                        minimum_price=String.valueOf(264000-decreament_point);
                        maximum_price=String.valueOf(284000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }

                }else if (model.equals("Esteem")){
                    if (year.equals("2009")){
                        minimum_price=String.valueOf(142000-decreament_point);
                        maximum_price=String.valueOf(153000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);


                    }else if (year.equals("2007")){

                        minimum_price=String.valueOf(106000-decreament_point);
                        maximum_price=String.valueOf(114000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2004")){

                        minimum_price=String.valueOf(888000-decreament_point);
                        maximum_price=String.valueOf(95000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }

                }else if (model.equals("Eeco")){
                    if (year.equals("2019")){
                        minimum_price=String.valueOf(81000-decreament_point);
                        maximum_price=String.valueOf(880000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);


                    }else if (year.equals("2018")){

                        minimum_price=String.valueOf(694000-decreament_point);
                        maximum_price=String.valueOf(746000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2017")){

                        minimum_price=String.valueOf(382000-decreament_point);
                        maximum_price=String.valueOf(410000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2016")){

                        minimum_price=String.valueOf(359000-decreament_point);
                        maximum_price=String.valueOf(387000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2015")){

                        minimum_price=String.valueOf(343000-decreament_point);
                        maximum_price=String.valueOf(368000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2014")){

                        minimum_price=String.valueOf(319000-decreament_point);
                        maximum_price=String.valueOf(343000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2013")){

                        minimum_price=String.valueOf(312000-decreament_point);
                        maximum_price=String.valueOf(335000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2012")){
                        minimum_price=String.valueOf(279000-decreament_point);
                        maximum_price=String.valueOf(301000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2011")){

                        minimum_price=String.valueOf(270000-decreament_point);
                        maximum_price=String.valueOf(291000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2010")){
                        minimum_price=String.valueOf(267000-decreament_point);
                        maximum_price=String.valueOf(287000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);


                    }else if (year.equals("2009")){

                        minimum_price=String.valueOf(243000-decreament_point);
                        maximum_price=String.valueOf(261000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2008")){

                        minimum_price=String.valueOf(214000-decreament_point);
                        maximum_price=String.valueOf(230000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2007")){

                        minimum_price=String.valueOf(186000-decreament_point);
                        maximum_price=String.valueOf(200000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2006")){
                        minimum_price=String.valueOf(161000-decreament_point);
                        maximum_price=String.valueOf(173000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2005")){

                        minimum_price=String.valueOf(146000-decreament_point);
                        maximum_price=String.valueOf(157000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2004")){

                        minimum_price=String.valueOf(13100-decreament_point);
                        maximum_price=String.valueOf(141000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }else if (year.equals("2003")){

                        minimum_price=String.valueOf(116000-decreament_point);
                        maximum_price=String.valueOf(126000-decreament_point);

                        min_price.setText(minimum_price);
                        max_price.setText(maximum_price);

                    }


                }else if (model.equals("Baleno")){


                }


            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = Year.getSelectedItem().toString();
                model = Model.getSelectedItem().toString();
                mileage = mileage1.getText().toString();
                brand = Brand.getSelectedItem().toString();
                fuel = spinner.getSelectedItem().toString();
                number = spinner1.getSelectedItem().toString();
                kms = Integer.parseInt(km.getText().toString());
                String km = kms.toString();
                serviceno = service.getSelectedItem().toString();
                link = URL1.getText().toString();
                inLink = In.getText().toString();
                fcLink = Fc.getText().toString();
                pushmin_price=Double.parseDouble(min_price.getText().toString());
                pushmax_price=Double.parseDouble(max_price.getText().toString());
//                Integer intmin_price = Integer.parseInt(pushmin_price);
//                Integer intmax_price = Integer.parseInt(pushmax_price);
//                Integer pushmin_price = Integer.parseInt(pushmin_price);
//                Integer pushmax_price = Integer.parseInt(pushmax_price1);
//                Calendar c = Calendar.getInstance();
//                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss");
//                String reg_date = df.format(c.getTime());
//                c.add(Calendar.DATE, Integer.parseInt(number));  // number of days to add
//                String end_date = df.format(c.getTime());
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss a");
                String reg_date = sdf.format(new Date());
                c.add(Calendar.DATE, Integer.parseInt(number));  // number of days to add
                String end_date = sdf.format(c.getTime());
                Integer bid = 0;
                String bider = "none";
                String id = databaseReference.push().getKey();
                Car car = new Car(year,brand,mileage,model,fuel,link,km,serviceno,inLink,fcLink,reg_date,end_date,number,bider,bid,pushmin_price,pushmax_price,id);
                databaseReference.child(id).setValue(car);
                Toast.makeText(getActivity(),"Data Saved", Toast.LENGTH_SHORT).show();
                mileage1.setText("");
                Year.setSelection(0);
                Model.setSelection(0);
                Brand.setSelection(0);
                spinner.setSelection(0);
                spinner1.setSelection(0);
                imageView.setImageResource(android.R.color.transparent);
                inView.setImageResource(android.R.color.transparent);
                fcView.setImageResource(android.R.color.transparent);
                min_price.setText("");
                max_price.setText("");
                service.setSelection(0);
            }
        });
        return view;
    }
    public void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case PICK_IMAGE_REQUEST:
                if (resultCode == RESULT_OK) {

                    Toast.makeText(getActivity(),"Image selected, click on upload button",Toast.LENGTH_SHORT).show();
                    filePath = imageReturnedIntent.getData();


//                    try {
//                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
//                        imageView.setImageBitmap(bitmap);
//                    }
//                    catch (IOException e)
//                    {
//                        e.printStackTrace();
//                    }
                }
        }
    }

    public  void FcImage(){
        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            final StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String image = uri.toString();
                                    Fc.setText(image);
                                    Toast.makeText(getActivity(),image, Toast.LENGTH_SHORT).show();
                                    Picasso.with(getActivity()).load(image).into(fcView);
                                }
                            });
                            progressDialog.dismiss();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }

    }
    private void InImage() {
        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            final StorageReference ref = storageReference.child("images/"+ UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String image = uri.toString();
                                    In.setText(image);
                                    Toast.makeText(getActivity(),image, Toast.LENGTH_SHORT).show();
                                    Picasso.with(getActivity()).load(image).into(inView);
                                }
                            });
                            progressDialog.dismiss();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }

    private void uploadImage() {


        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            final StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String image = uri.toString();
                                    URL1.setText(image);
                                    Toast.makeText(getActivity(), image, Toast.LENGTH_SHORT).show();
                                    Picasso.with(getActivity()).load(image).into(imageView);
                                }
                            });
                            progressDialog.dismiss();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
        }
    }
    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {


        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            // An item was selected. You can retrieve the selected item using
            // parent.getItemAtPosition(pos)
            //String selectedItemText = (String) parent.getItemAtPosition(pos);

        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }



}
