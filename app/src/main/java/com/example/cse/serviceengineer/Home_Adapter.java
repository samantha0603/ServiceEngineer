package com.example.cse.serviceengineer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Home_Adapter extends RecyclerView.Adapter<Home_Adapter.ViewHolder> {
     Context context;
     int layout;
     List<Home_Model> data;

     public Home_Adapter(List<Home_Model> data, int item1, Context context) {
          this.context = context;
          this.layout = item1;
          this.data = data;
     }

     @NonNull
     @Override
     public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          LayoutInflater layoutInflater = LayoutInflater.from(context);
          View v = layoutInflater.inflate(R.layout.item1,null);
          ViewHolder holder = new ViewHolder(v);
          return  holder;
     }

     @Override
     public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
          holder.productname.setText(data.get(position).getProductname());
          holder.issue.setText(data.get(position).getIssue());
          holder.desc.setText(data.get(position).getDescription());
          holder.cname.setText(data.get(position).getCustomername());
          holder.phno.setText(data.get(position).getPhno());
          holder.add.setText(data.get(position).getAddress());

     }

     @Override
     public int getItemCount() {
          return data.size();
     }

     public  class ViewHolder extends RecyclerView.ViewHolder {
     TextView productname,cname,issue,desc,phno,add;
          public ViewHolder(View itemView) {
               super(itemView);
               productname=itemView.findViewById(R.id.productname);
               cname=itemView.findViewById(R.id.name);
               issue=itemView.findViewById(R.id.issue);
               desc=itemView.findViewById(R.id.description);
               phno=itemView.findViewById(R.id.phone);
               add=itemView.findViewById(R.id.address);

          }
     }
}
