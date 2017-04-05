package com.example.rednone.fooddelivery;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by RedNone on 02.04.2017.
 */

public class DrinksAdapter extends RecyclerView.Adapter<DrinksAdapter.ViewHolder> {

    private List<DataModel> dataModelList;

    public DrinksAdapter(List<DataModel> dataModelList) {
        this.dataModelList = dataModelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.menu_cardview,parent,false);

        return new DrinksAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DrinksAdapter.ViewHolder holder, int position) {

        final DataModel dataModel = dataModelList.get(position);

        holder.name.setText(dataModel.getName());
        holder.cost.setText(String.valueOf(dataModel.getCost()));
        holder.imageView.setImageResource(R.drawable.drinks);




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
                Log.d("DrinksAdapter", App.getDataModels().toString());
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataModelList.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,cost;
        CheckBox checkBox;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.pizzaName);
            cost = (TextView) itemView.findViewById(R.id.pizzaCost);
            checkBox = (CheckBox) itemView.findViewById(R.id.agreeCheckBox);
            imageView = (ImageView) itemView.findViewById(R.id.imageViewCard);
        }
    }
}
