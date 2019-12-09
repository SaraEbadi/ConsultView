package com.example.consultview;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.consultview.model.ConsultModel;
import com.example.consultview.model.Content;

import java.util.List;

public class ConsultAdapter extends ListAdapter<Content, ConsultAdapter.ConsultViewHolder> {
    Context mContext;

    List<Content> models;
    String htmlText;
    BtnClickListener bClickListener;
    Boolean btn  = false;

    protected ConsultAdapter(Context context,@NonNull DiffUtil.ItemCallback<Content> diffCallback) {
        super(diffCallback);
    }


    @Override
    public void submitList(@Nullable List<Content> list) {
        super.submitList(list);
        models = list;

    }

    @NonNull
    @Override
    public ConsultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0)
            view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title,parent,false);
        else  if (viewType == 1)
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_consult,parent,false);
        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_html, parent, false);
            btn = true;
        }

        return new ConsultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsultViewHolder holder, int position) {
        String text = models.get(position).getText();

        int align ;

        switch (models.get(position).getAlign()){

            case "right":
                align= View.TEXT_ALIGNMENT_VIEW_START;
                break;
            case "left":
                align= View.TEXT_ALIGNMENT_VIEW_END;
                break;
            case "center":
                align= View.TEXT_ALIGNMENT_CENTER;
                break;
            default:
                align= View.TEXT_ALIGNMENT_GRAVITY;
                break;
        }


        if (models.get(position).getIsHtml()) {
                holder.htmlBtn.setOnClickListener(holder);


        } else if (models.get(position).getIsTitle()) {
                holder.txtSpecific.setText(text);
                holder.txtSpecific.setTextAlignment(align);
        } else {
                holder.txtNormal.setText(text);
                holder.txtNormal.setTextAlignment(align);

            }

    }

    @Override
    public int getItemViewType(int position) {
        if (models.get(position).getIsTitle())
            return 0;
        else if (models.get(position).getIsHtml())
            return 2;
        else
            return 1;
    }


    public void  setBtnClickListener ( BtnClickListener btnClickListener){
        this.bClickListener = btnClickListener;

    }

    public class ConsultViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        TextView txtSpecific;
        TextView txtNormal;
        Button htmlBtn;

        public ConsultViewHolder(@NonNull View itemView) {
            super(itemView);

            txtSpecific = itemView.findViewById(R.id.txtSpecific);
            txtNormal = itemView.findViewById(R.id.txtNormal);
            htmlBtn = itemView.findViewById(R.id.btn_html);



        }

        @Override
        public void onClick(View v) {
            bClickListener.onClickListener(v,getAdapterPosition());

        }
    }
}
