package com.example.rednone.fooddelivery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by RedNone on 24.03.2017.
 */

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {

    private List<DataModel> list;

    public BasketAdapter(List<DataModel> dataModels) {
        this.list = dataModels;
    }

    @Override
    public BasketAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.basket_view,parent,false);

        return new BasketAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BasketAdapter.ViewHolder holder, int position) {
        DataModel dataModel = list.get(position);

        holder.name.setText(dataModel.getName());
        holder.cost.setText(dataModel.getCost());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,cost;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.NameTextView);
            cost = (TextView) itemView.findViewById(R.id.costTextView);
        }
    }
}
