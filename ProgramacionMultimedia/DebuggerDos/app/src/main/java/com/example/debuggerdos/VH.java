package com.example.debuggerdos;


import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class VH extends RecyclerView.ViewHolder {

    private TextView tv;

    public VH(View itemView) {
        super(itemView);
        tv = itemView.findViewById(R.id.tvItem);
    }

    public void bind(String dato) {
        tv.setText(dato);
    }
}
