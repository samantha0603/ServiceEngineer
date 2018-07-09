package com.example.cse.serviceengineer;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class Fragment_status extends Fragment {
    ImageView imageView;

    public Fragment_status(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_home3, container, false);
        imageView=v.findViewById(R.id.imageView);
        assert container != null;
        ConnectivityManager manager = (ConnectivityManager) container.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert manager != null;
        NetworkInfo net=manager.getActiveNetworkInfo();
        if(net!=null && net.isConnected()){
            Glide.with(v.getContext()).load("http://lh3.googleusercontent.com/6Qykd-rRMkN2o6Xb4QoxnX7qkwjBnBDetlVY3CyWFm0xL3tZT-ITP6Oqo5V43eGmjcg=w300").into(imageView);
        }
        else {
            Toast.makeText(container.getContext(),"Please connect to network",Toast.LENGTH_SHORT).show();
        }



        return v;
    }
}