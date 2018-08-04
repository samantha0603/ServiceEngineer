package com.example.cse.serviceengineer;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;



public class Fragment_status extends Fragment {

    Button button;
    ZXingScannerView zXingScannerView;

    public Fragment_status(){

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        final View v=inflater.inflate(R.layout.fragment_home3, container, false);
        assert container != null;
       /* ConnectivityManager manager = (ConnectivityManager) container.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert manager != null;
        NetworkInfo net=manager.getActiveNetworkInfo();
        if(net!=null && net.isConnected()){
            Glide.with(v.getContext()).load("http://lh3.googleusercontent.com/6Qykd-rRMkN2o6Xb4QoxnX7qkwjBnBDetlVY3CyWFm0xL3tZT-ITP6Oqo5V43eGmjcg=w300").into(imageView);
        }
        else {
            Toast.makeText(container.getContext(),"Please connect to network",Toast.LENGTH_SHORT).show();
        }*/


        button = v.findViewById(R.id.scan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                zXingScannerView = new ZXingScannerView(getActivity());
                getActivity().setContentView(zXingScannerView);
                zXingScannerView.setResultHandler(new ZXingScannerView.ResultHandler() {
                    @Override
                    public void handleResult(Result rawResult) {
                            Toast.makeText(getContext(),rawResult.getText(),Toast.LENGTH_SHORT).show();
                            getActivity().setContentView(R.layout.fragment_home3);
                            zXingScannerView.stopCamera();
                    }
                });
                zXingScannerView.startCamera();

            }
        });


        return v;
    }
}