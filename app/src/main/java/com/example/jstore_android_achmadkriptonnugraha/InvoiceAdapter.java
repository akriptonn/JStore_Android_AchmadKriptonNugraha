package com.example.jstore_android_achmadkriptonnugraha;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jstore_android_achmadkriptonnugraha.model.Invoice;

import java.util.ArrayList;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.InvoiceViewHolder> {

    private ArrayList<Invoice> dataList;
    private View.OnClickListener listener;

    public InvoiceAdapter(ArrayList<Invoice> dataList, View.OnClickListener listener) {
        this.dataList = dataList;
        this.listener = listener;
    }

    @Override
    public InvoiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_invoice, parent, false);
        return new InvoiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(InvoiceViewHolder holder, int position) {

        holder.txtId.setText("Invoice ID: "+Integer.toString(dataList.get(position).getId()));
        holder.txtTanggal.setText("Invoice Date: "+(dataList.get(position).getDate()));
        holder.txtHarga.setText("Total Price: "+Integer.toString(dataList.get(position).getTotalPrice()));
        holder.txtStatus.setText("Status: "+dataList.get(position).getINVOICE_STATUS());
        holder.txtType.setText("Payment type: "+dataList.get(position).getINVOICE_TYPE());
        boolean isActive = dataList.get(position).getIsActive();
        if (isActive)
            holder.txtActive.setText("Not Completed");
        else
            holder.txtActive.setText("Completed");
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class InvoiceViewHolder extends RecyclerView.ViewHolder{
        private TextView txtId, txtTanggal, txtHarga, txtStatus, txtType, txtActive;

        public InvoiceViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(listener);

            txtId =  itemView.findViewById(R.id.id_invoice);
            txtTanggal =  itemView.findViewById(R.id.tanggal_invoice);
            txtHarga =  itemView.findViewById(R.id.total_price);
            txtStatus =  itemView.findViewById(R.id.status_invoice);
            txtType =  itemView.findViewById(R.id.type_invoice);
            txtActive=  itemView.findViewById(R.id.active_invoice);
        }
    }

}
