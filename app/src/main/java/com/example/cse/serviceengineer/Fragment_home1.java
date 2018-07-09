package com.example.cse.serviceengineer;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_home1 extends Fragment {
RecyclerView.LayoutManager layoutManager;
RecyclerView recyclerView;
RecyclerView.Adapter adapter;
String[] str  ;
String[] str2;
String [] str3;
String [] str4;
String [] str5;
String [] str6;
    public Fragment_home1() {
        // Required empty public constructor
    }


        @Override
        public View onCreateView (@NonNull LayoutInflater inflater, final ViewGroup container,
                                  Bundle savedInstanceState){
            // Inflate the layout for this fragment

          //  listItem = container.getResources().getStringArray(R.array.array_home);
            View v= inflater.inflate(R.layout.fragment_fragment_home1,container,false);
            str=container.getResources().getStringArray(R.array.productname);

            str2=container.getResources().getStringArray(R.array.issue);
            str3=container.getResources().getStringArray(R.array.description);
            str4=container.getResources().getStringArray(R.array.name);
            str5=container.getResources().getStringArray(R.array.phone);
            str6=container.getResources().getStringArray(R.array.address);
            recyclerView=v.findViewById(R.id.recycle);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
            RecycleAdapter adapter=new RecycleAdapter(container.getContext(),str,str2,str3,str4,str5,str6);
            System.out.println("Hello hi");
            recyclerView.setAdapter(adapter);
            System.out.println("setting adapter");
            return v;


}
}