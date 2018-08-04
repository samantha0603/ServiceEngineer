package com.example.cse.serviceengineer;



import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Password extends Activity {
    EditText phone,otp,pass;
    Button b;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        phone=findViewById(R.id.phone);
        otp=findViewById(R.id.otp);
        pass=findViewById(R.id.newpassword);
        b=findViewById(R.id.b3);
        progressBar=findViewById(R.id.passpb);
        progressBar.setVisibility(View.GONE);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),"Your request is being processed",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}

