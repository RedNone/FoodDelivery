package com.example.rednone.fooddelivery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by RedNone on 23.03.2017.
 */

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private List<DataModel> list;

    public MenuAdapter(List<DataModel> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.menu_cardview,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final DataModel dataModel = list.get(position);

        holder.name.setText(dataModel.getName());
        holder.cost.setText(String.valueOf(dataModel.getCost()));




        for(DataModel obj : App.getDataModels())
        {
            if(dataModel.getId() == obj.getId()) {
                if (obj.getBasket() == true) {
                    holder.checkBox.setChecked(true);
                }
            }

        }


        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    for(DataModel obj : App.getDataModels())
                    {

                        if(obj.getId() == dataModel.getId())
                        {
                            obj.setBasket(true);
                            break;
                        }

                    }


                }
                else
                {
                    for(DataModel obj : App.getDataModels())
                    {

                        if(obj.getId() == dataModel.getId())
                        {
                            obj.setBasket(false);
                            break;
                        }

                    }
                }
                Log.d("MenuAdapter", App.getDataModels().toString());
            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,cost;
        CheckBox checkBox;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.pizzaName);
            cost = (TextView) itemView.findViewById(R.id.pizzaCost);
            checkBox = (CheckBox) itemView.findViewById(R.id.agreeCheckBox);

        }
    }
}
