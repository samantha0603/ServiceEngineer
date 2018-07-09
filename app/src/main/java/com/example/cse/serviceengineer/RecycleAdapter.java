package com.example.cse.serviceengineer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.zip.Inflater;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
  private  Context c;
   private String[] str,str2,str3,str4,str5,str6;

    public RecycleAdapter(Context context, String[] str, String[] str2,String[] str3,String[] str4,String[] str5,String[] str6 ) {
        this.c=context;
        this.str= str;
        this.str2=str2;
        this.str3=str3;
        this.str4=str4;
        this.str5=str5;
        this.str6=str6;
        System.out.println("Hello hi"+str[0]);
    }

    @NonNull
    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("Hello hi on create");
        View v = LayoutInflater.from(c).inflate(R.layout.item1,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter.ViewHolder holder, int position) {
        System.out.println("Hello hi on bind");
        System.out.println("Hello hi"+str[position]);
    holder.textView1.setText(str[position]);
    holder.textView2.setText(str2[position]);
    holder.textView3.setText(str3[position]);
    holder.textView4.setText(str4[position]);
    holder.textView5.setText(str5[position]);
    holder.textView6.setText(str6[position]);

    }

    @Override
    public int getItemCount() {
        return str.length;
    }
    public static class  ViewHolder extends RecyclerView.ViewHolder{
TextView textView1,textView2,textView3,textView4,textView5,textView6;
public ViewHolder(View itemView) {
            super(itemView);
    System.out.println("Hello hi on view holder");
            textView1=itemView.findViewById(R.id.productname);
            textView2=itemView.findViewById(R.id.issue);
            textView3=itemView.findViewById(R.id.description);
            textView4=itemView.findViewById(R.id.name);
            textView5=itemView.findViewById(R.id.phone);
            textView6=itemView.findViewById(R.id.address);
        }
    }
}
