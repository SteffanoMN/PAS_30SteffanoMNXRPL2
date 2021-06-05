package com.example.pas_30steffanoxrpl2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WhatsAppAdapter extends RecyclerView.Adapter<WhatsAppAdapter.ListViewHolder> implements Filterable  {

    private ArrayList<ContactModel> dataList;
    private ArrayList<ContactModel> dataListFilter;
    private OnItemClickListener mListener;
    private Context mContext;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public WhatsAppAdapter(Context mContext, ArrayList<ContactModel> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rv_list, parent, false);
        return new ListViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        holder.profile_name.setText(dataList.get(position).getName());
        holder.profile_number.setText(dataList.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String Key = constraint.toString();
                if (Key.isEmpty()){
                    dataListFilter = dataList;
                } else {
                    ArrayList<ContactModel> isFiltered = new ArrayList<>();
                    for (ContactModel row : dataList) {
                        if (row.getName().toLowerCase().contains(Key)) {
                            isFiltered.add(row);
                        }
                    }
                    dataListFilter = isFiltered;
                }
                FilterResults results = new FilterResults();
                results.values = dataListFilter;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                dataListFilter = (ArrayList<ContactModel>) results.values;
            }
        };
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView profile_name, profile_number;
        private ImageView img_list;
        private RelativeLayout relativeLayout;

        public ListViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            profile_name = itemView.findViewById(R.id.profile_name_list);
            profile_number = itemView.findViewById(R.id.profile_number_list);
            img_list = itemView.findViewById(R.id.img_list);
            relativeLayout = itemView.findViewById(R.id.rv_layout_list);

            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}

