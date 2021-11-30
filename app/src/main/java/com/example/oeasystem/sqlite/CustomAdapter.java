package com.example.oeasystem.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oeasystem.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList id, exam_title, date, time;

    public CustomAdapter(Context context,
                         ArrayList id,
                         ArrayList exam_title, ArrayList date, ArrayList time) {
        this.context = context;
        this.id = id;
        this.exam_title = exam_title;
        this.date = date;
        this.time = time;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.id_txt.setText(String.valueOf(id.get(position)));
        holder.exam_title_txt.setText(String.valueOf(exam_title.get(position)));
        holder.date_txt.setText(String.valueOf(date.get(position)));
        holder.time_txt.setText(String.valueOf(time.get(position)));

    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id_txt, exam_title_txt, date_txt, time_txt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id_txt = itemView.findViewById(R.id.id_txt);
            exam_title_txt = itemView.findViewById(R.id.exam_title_txt);
            date_txt = itemView.findViewById(R.id.date_txt);
            time_txt = itemView.findViewById(R.id.time_txt);
        }
    }
}
