package com.example.consultview;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.consultview.model.ConsultModel;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ConsultViewHolder> {


    ConsultModel model;

    public RecyclerViewAdapter(ConsultModel model) {
        this.model = model;
    }

    @NonNull
    @Override
    public ConsultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0)
       view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title,parent,false);
        else
         view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_consult,parent,false);
        return new ConsultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsultViewHolder holder, int position) {
        String text = model.getContent().get(position).getText();
        if (model.getContent().get(position).getIsHtml())
            text = Html.fromHtml(text).toString();
       if (model.getContent().get(position).getIsTitle())
        holder.txtSpecific.setText(text);
       else
        holder.txtNormal.setText(text);

    }

    @Override
    public int getItemCount() {
        return model.getContent().size();
    }

    @Override
    public int getItemViewType(int position) {
        if (model.getContent().get(position).getIsTitle())
            return 0;
        else
            return 1;
    }

    public class ConsultViewHolder extends RecyclerView.ViewHolder {

        TextView txtSpecific;
        TextView txtNormal;
        public ConsultViewHolder(@NonNull View itemView) {
            super(itemView);

            txtSpecific = itemView.findViewById(R.id.txtSpecific);
            txtNormal = itemView.findViewById(R.id.txtNormal);
        }
    }
}
