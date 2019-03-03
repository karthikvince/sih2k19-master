package com.example.sih2019;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SignUp extends AppCompatActivity {
    TextView tt;
    EditText no;
    EditText otp;
    TextInputLayout no_lay;
    TextInputLayout otp_lay;
    Button bt;
    Button gen;
    String get_no,get_otp,verificationCode;
    FirebaseAuth auth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        tt = findViewById(R.id.tt);
        no = findViewById(R.id.no);
        otp = findViewById(R.id.otp);
        no_lay = findViewById(R.id.no_lay);
        otp_lay = findViewById(R.id.otp_lay);
        bt = findViewById(R.id.log);
        gen = findViewById(R.id.gen);
        StartFirebaseLogin();
        gen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(no.getText().length() != 10)
                {
                    no_lay.setError("phone no must contain 10 numbers");
                }
                else {
                    no_lay.setError(null);
                    get_no = no.getText().toString();
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            get_no,
                            60,
                            TimeUnit.SECONDS,
                            SignUp.this,
                            mcallbacks
                    );
                }

            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
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
                    if(otp.length()==0)
                    {
                        otp_lay.setError("invalid otp");
                    }
                    else {
                        get_otp = otp.getText().toString();
                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode, get_otp);
                        SigninWithPhone(credential);
                    }

                }else{
                    //no connection
                    Toast toast = Toast.makeText(SignUp.this, "No Internet Connection",
                            Toast.LENGTH_LONG);
                    toast.show();
                }


            }
        });
        tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUp.this, Register.class);
                startActivity(i);
            }
        });


    }

    private void SigninWithPhone(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent = new Intent(SignUp.this,Register.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(SignUp.this,"incorrect otp",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void StartFirebaseLogin() {
        auth = FirebaseAuth.getInstance();
        mcallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(SignUp.this,"verification completed",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(SignUp.this,"verification Failed",Toast.LENGTH_SHORT).show();
            }
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken){
                super.onCodeSent(s, forceResendingToken);
                verificationCode =s;
                Toast.makeText(SignUp.this,"code sent",Toast.LENGTH_SHORT).show();
            }
        };
    }
}


