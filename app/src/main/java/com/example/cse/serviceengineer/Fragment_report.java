package com.example.cse.serviceengineer;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

import static com.example.cse.serviceengineer.R.color.white;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_report extends Fragment {

    RecyclerView recyclerView;
    List<Report_Data_Bringer> list;
    ApiInterface apiCall;
    TextView nodata,name1;
    ImageView profile;
    ProgressBar progressBar1;
    Call<List<Report_Data_Bringer>> data;
    public Fragment_report() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         final View v=inflater.inflate(R.layout.fragment_report, container, false);

        //nodata
        nodata=v.findViewById(R.id.nodata);


        recyclerView =v.findViewById(R.id.recycle_id_rp);
        recyclerView.setHasFixedSize(true );
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        progressBar1 =new ProgressBar(getContext());
        //progress bar
        progressBar1=v.findViewById(R.id.progresss_rp);
        progressBar1.setVisibility(View.VISIBLE);
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("login",Context.MODE_PRIVATE);
        final String p=sharedPreferences.getString("username","error");

        final String name = "{\"EngineerName\":\""+ p +"\"}";
        final CoordinatorLayout mylay=v.findViewById(R.id.coordinatorlayout);
        ConnectivityManager conMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        if ( conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED ) {

            // notify user you are online
            //Toast.makeText(getContext(), "online cummed", Toast.LENGTH_SHORT).show();
        }
        else if ( conMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.DISCONNECTED
                || conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.DISCONNECTED) {
            TextView tv=v.findViewById(R.id.checkInternet);
            tv.setVisibility(View.VISIBLE);
            progressBar1.setVisibility(View.GONE);

        }
        //swipe
        final SwipeRefreshLayout refreshLayout=v.findViewById(R.id.swipe_report);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            //    if (list != null) {

                    ApiInterface apiCall= ApiService.getService().create(ApiInterface.class);
                    data=apiCall.getReport_data(name);
                    data.enqueue(new Callback<List<Report_Data_Bringer>>() {
                        @Override
                        public void onResponse(Call<List<Report_Data_Bringer>> call, Response<List<Report_Data_Bringer>> response) {
                            //Toast.makeText(getContext(), "done"+response.body().get(0).getEngineername(), Toast.LENGTH_SHORT).show();

                            list=response.body();

                            if(list.isEmpty())
                            {

                                nodata.setVisibility(View.VISIBLE);
                            }


                            if(list  != null)
                            {

                                nodata.setVisibility(View.GONE);
                                progressBar1.setVisibility(View.GONE);
                            }
                            recyclerView.setAdapter(new Report_Adapter(list,R.layout.report_layout,v.getContext()));
                        }

                        @Override
                        public void onFailure(Call<List<Report_Data_Bringer>> call, Throwable t) {

                            Snackbar snackbar = Snackbar
                                    .make(mylay, "No Internet Connection", Snackbar.LENGTH_LONG);
                            View mView = snackbar.getView();
// get textview inside snackbar view
                            TextView mTextView = mView.findViewById(android.support.design.R.id.snackbar_text);
// set text to center
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                                mTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                            else
                                mTextView.setGravity(Gravity.CENTER_HORIZONTAL);
// show the snackbar
                            snackbar.show(); //Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                        }
                    });
                    //  Toast.makeText(v.getContext(), "hello", Toast.LENGTH_SHORT).show();
                    refreshLayout.setRefreshing(false);
                }
          //  }
        });
        ApiInterface apiCall= ApiService.getService().create(ApiInterface.class);
        data=apiCall.getReport_data(name);
        data.enqueue(new Callback<List<Report_Data_Bringer>>() {
            @Override
            public void onResponse(Call<List<Report_Data_Bringer>> call, Response<List<Report_Data_Bringer>> response) {
                //Toast.makeText(getContext(), "done"+response.body().get(0).getAssignedwork(), Toast.LENGTH_SHORT).show();
                List<Report_Data_Bringer> list;
                list=response.body();

                if(list.isEmpty())
                {

                    nodata.setVisibility(View.VISIBLE);
                }


                if(list  != null)
                {

                    nodata.setVisibility(View.GONE);
                    progressBar1.setVisibility(View.GONE);
                }
                recyclerView.setAdapter(new Report_Adapter(list,R.layout.report_layout,v.getContext()));
            }

            @Override
            public void onFailure(Call<List<Report_Data_Bringer>> call, Throwable t) {

                Snackbar snackbar = Snackbar
                        .make(mylay, "No Internet Connection", Snackbar.LENGTH_LONG);
                View mView = snackbar.getView();
// get textview inside snackbar view
                TextView mTextView = mView.findViewById(android.support.design.R.id.snackbar_text);
// set text to center
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                    mTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                else
                    mTextView.setGravity(Gravity.CENTER_HORIZONTAL);
// show the snackbar
                snackbar.show(); //Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

}
