package com.example.cse.serviceengineer;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.design.widget.Snackbar.make;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_home1 extends Fragment {

    TextView nocon,nodata;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    List<Home_Model> list;
    String pr, iss, ds, cn, cp, ca;
    ApiInterface apiCall;
    Call<List<Home_Model>> datacall;
    CoordinatorLayout coordinatorLayout;


    public Fragment_home1() {// Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)

    {

        final View v = inflater.inflate(R.layout.fragment_fragment_home1, container, false);

        recyclerView = v.findViewById(R.id.homerv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        progressBar = new ProgressBar(getContext());
        nocon=v.findViewById(R.id.nocon);
        nodata=v.findViewById(R.id.nodata);
        nodata.setVisibility(View.INVISIBLE);
        //progress bar
        progressBar = v.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        coordinatorLayout=v.findViewById(R.id.homefrag);
        //query
        SharedPreferences sp = this.getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        final String p = sp.getString("username","error");

        final String q = "{\"Assigned Engineer\":\""+ p +"\"}";

        ConnectivityManager cm =
                (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (!isConnected){
            progressBar.setVisibility(View.GONE);
            nocon.setVisibility(View.VISIBLE);
        }
        //swiperefresh

        final SwipeRefreshLayout refreshLayout = v.findViewById(R.id.swipid);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(list.isEmpty()){
                    nodata.setVisibility(View.VISIBLE);
                }

                if (list != null) {
                    nodata.setVisibility(View.INVISIBLE);
                    apiCall = ApiClient.getClient().create(ApiInterface.class);
                    datacall = apiCall.getHomeData(q);
                    datacall.enqueue(new Callback<List<Home_Model>>() {
                        @Override
                        public void onResponse(Call<List<Home_Model>> call, Response<List<Home_Model>> response) {
                            list = response.body();
                            if(list.isEmpty()){
                                nodata.setVisibility(View.VISIBLE);
                            }

                            recyclerView.setAdapter(new Home_Adapter(list, R.layout.item1, v.getContext()));


                        }

                        @Override
                        public void onFailure(Call<List<Home_Model>> call, Throwable t) {
                            Snackbar snackbar= Snackbar.make(coordinatorLayout,"Check Your Connection",Snackbar.LENGTH_SHORT);
                            View mView = snackbar.getView();

                            TextView mTextView =  mView.findViewById(android.support.design.R.id.snackbar_text);

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                                mTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            else
                                mTextView.setGravity(Gravity.CENTER_HORIZONTAL);

                            snackbar.show();
                        }


                    });

                    refreshLayout.setRefreshing(false);
                }
            }
        });


        //Retrofit connection
        apiCall = ApiClient.getClient().create(ApiInterface.class);
        datacall = apiCall.getHomeData(q);
        datacall.enqueue(new Callback<List<Home_Model>>() {
            @Override
            public void onResponse(Call<List<Home_Model>> call, Response<List<Home_Model>> response) {
                list = response.body();

                if(list.isEmpty()){
                    nodata.setVisibility(View.VISIBLE);
                }
                if (list != null) {
                    progressBar.setVisibility(View.GONE);
                }
                //   Toast.makeText(getContext(),"hi"+list.get(0).getCustomername(),Toast.LENGTH_LONG).show();

                recyclerView.setAdapter(new Home_Adapter(list, R.layout.item1, v.getContext()));

            }

            @Override
            public void onFailure(Call<List<Home_Model>> call, Throwable t) {
                Snackbar snackbar= Snackbar.make(coordinatorLayout,"Check Your Connection",Snackbar.LENGTH_SHORT);
                View mView = snackbar.getView();

                TextView mTextView =  mView.findViewById(android.support.design.R.id.snackbar_text);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                    mTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                else
                    mTextView.setGravity(Gravity.CENTER_HORIZONTAL);

                snackbar.show();
            }

        });
    return v;

    }
}
