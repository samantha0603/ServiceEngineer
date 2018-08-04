package com.example.cse.serviceengineer;

import android.app.Dialog;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_home1 extends Fragment {

    TextView textView;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    List<Home_Model> list;
    String pr, iss, ds, cn, cp, ca;
    ApiInterface apiCall;
    Call<List<Home_Model>> datacall;


    public Fragment_home1() {// Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)

    {

        final View v = inflater.inflate(R.layout.fragment_fragment_home1, container, false);

        recyclerView = v.findViewById(R.id.homerv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        textView=v.findViewById(R.id.no);
        progressBar = new ProgressBar(getContext());
        //progress bar
        progressBar = v.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        textView.setVisibility(View.GONE);
        //swiperefresh

        final SwipeRefreshLayout refreshLayout = v.findViewById(R.id.swipid);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (list == null)
                {
                    textView.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(),"list empty",Toast.LENGTH_SHORT).show();
                }

                if (list != null) {

                    Toast.makeText(getActivity(), "refreshing", Toast.LENGTH_LONG).show();
                    apiCall = ApiClient.getClient().create(ApiInterface.class);
                    datacall = apiCall.getHomeData("h3IQbkK_J7am5m45-Wd-FryEVIqrvOJI");
                    datacall.enqueue(new Callback<List<Home_Model>>() {
                        @Override
                        public void onResponse(Call<List<Home_Model>> call, Response<List<Home_Model>> response) {
                            list = response.body();
                            recyclerView.setAdapter(new Home_Adapter(list, R.layout.item1, v.getContext()));


                        }

                        @Override
                        public void onFailure(Call<List<Home_Model>> call, Throwable t) {
                            Toast.makeText(getContext(), "Please connect to internet", Toast.LENGTH_SHORT).show();
                        }


                    });

                    refreshLayout.setRefreshing(false);
                }
            }
        });


        //Retrofit connection
        apiCall = ApiClient.getClient().create(ApiInterface.class);
        datacall = apiCall.getHomeData("h3IQbkK_J7am5m45-Wd-FryEVIqrvOJI");
        datacall.enqueue(new Callback<List<Home_Model>>() {
            @Override
            public void onResponse(Call<List<Home_Model>> call, Response<List<Home_Model>> response) {
                list = response.body();

                if(list == null){
                    textView.setVisibility(View.GONE);
                }
                if (list != null) {
                    progressBar.setVisibility(View.GONE);
                }
                //   Toast.makeText(getContext(),"hi"+list.get(0).getCustomername(),Toast.LENGTH_LONG).show();

                recyclerView.setAdapter(new Home_Adapter(list, R.layout.item1, v.getContext()));

            }

            @Override
            public void onFailure(Call<List<Home_Model>> call, Throwable t) {
                Toast.makeText(getContext(), "please connect to internet", Toast.LENGTH_LONG).show();
            }
        });
    return v;

    }
}
