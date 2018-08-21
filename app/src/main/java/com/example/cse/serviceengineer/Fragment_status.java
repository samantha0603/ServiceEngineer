package com.example.cse.serviceengineer;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_status extends Fragment {
    String qrcode;
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
                            qrcode=rawResult.getText();
                           final String str=qrcode.substring(18,qrcode.length()-5);
                        SharedPreferences sp = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
                        final String name = sp.getString("username","error");

                        String q="{\"Assigned Engineer\":\""+ name+"\",\"customerName\":\""+ str+"\"}";

                        //retrofit
                           ApiInterface api =ApiClient.getClient().create(ApiInterface.class);
                        Call<List<Home_Model>> data =api.getHomeData(q);
                        data.enqueue(new Callback<List<Home_Model>>() {
                            @Override
                            public void onResponse(Call<List<Home_Model>> call, Response<List<Home_Model>> response1) {
                                   //update
                                String pr=response1.body().get(0).getProductname();
                                String iss=response1.body().get(0).getIssue();
                                String des=response1.body().get(0).getDescription();
                                String cn=response1.body().get(0).getCustomername();
                                String cp=response1.body().get(0).getPhno();

                                //update
                                JSONObject j = new JSONObject();
                                try {
                                    j.put("productname", pr);
                                    j.put("issue", iss);
                                    j.put("description", des);
                                    j.put("customerName", cn);
                                    j.put("customerPhno", cp);
                                    j.put("Assigned Engineer",name);
                                    j.put("status","completed");

                                    ApiInterfacePut apiPut = ApiClient.getClient().create(ApiInterfacePut.class);
                                    Call<ResponseBody> body = apiPut.savePut(j.toString());
                                    body.enqueue(new Callback<ResponseBody>() {
                                        @Override
                                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                            Toast.makeText(v.getContext(), "Successfully completed ", Toast.LENGTH_LONG).show();
                                        }

                                        @Override
                                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                                            Toast.makeText(v.getContext(), "error while posting", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                //sms
                                    SmsManager smsManager = SmsManager.getDefault();
                                    smsManager.sendTextMessage("9100784817", null, name+" has attended the service for "+response1.body().get(0).getCustomername(), null, null);
                                    String customerNumber = response1.body().get(0).getPhno();
                                    //manger
                                    //  SmsManager sms = SmsManager.getDefault();
                                    //  sms.sendTextMessage(managerphone,null,"task done",null,null);
                                    SmsManager sms1 = SmsManager.getDefault();
                                    sms1.sendTextMessage(customerNumber, null, "Your service was started by "+name, null, null);
                                    startActivity(new Intent(getActivity(), MainActivity.class));

                            }

                            @Override
                            public void onFailure(Call<List<Home_Model>> call, Throwable t) {
                                    Toast.makeText(getActivity(),"try again",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getActivity(),MainActivity.class));
                            }
                        });
                             Toast.makeText(getContext(),str,Toast.LENGTH_SHORT).show();
                            zXingScannerView.stopCamera();
                            getActivity().setContentView(R.layout.fragment_home3);
                    }
                });
                zXingScannerView.startCamera();

            }
        });


        return v;
    }
}