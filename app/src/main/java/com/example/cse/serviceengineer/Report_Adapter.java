package com.example.cse.serviceengineer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;

public class Report_Adapter extends RecyclerView.Adapter<Report_Adapter.ViewHolder> {

    Context ct;
    int layout;
    List<Report_Data_Bringer> data;
    public Report_Adapter(List<Report_Data_Bringer> data, int report_layout, Context context) {

        this.ct=context;
        this.layout=report_layout;
        this.data=data;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(ct);
        View view =layoutInflater.inflate(R.layout.report_layout,null);
        ViewHolder holder=new ViewHolder(view);
        return  holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.engineername.setText(data.get(position).getEngineername());
        holder.assign_works.setText(data.get(position).getAssignedwork());
        holder.complete.setText(data.get(position).getCompleted());
        holder.totalrev.setText(data.get(position).getTotalrevenue());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView engineername,assign_works,complete,totalrev,status;
        public ViewHolder(View itemView) {
            super(itemView);
            engineername=itemView.findViewById(R.id.engineer_name_rp);
            assign_works=itemView.findViewById(R.id.assigned_rp);
            complete=itemView.findViewById(R.id.completed_rp);
            totalrev=itemView.findViewById(R.id.total_rp);

        }
    }
}
