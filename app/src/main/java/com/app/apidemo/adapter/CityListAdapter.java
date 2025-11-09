package com.app.apidemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.apidemo.R;
import com.app.apidemo.model.City;
import com.bumptech.glide.Glide;

import java.util.List;

public class CityListAdapter extends BaseAdapter {
    private Context context;
    private List<City> cityList;
    private OnItemTap onItemTap;

    private LayoutInflater inflter;

    public interface OnItemTap {
        void onTap(City city);
    }

    public CityListAdapter(Context context, List<City> cityList, OnItemTap onItemTap) {
        this.context = context;
        this.cityList = cityList;
        this.onItemTap = onItemTap;
        inflter = (LayoutInflater.from(context));

    }

    public class MyViewHolder {
        TextView tvName, tvAge, tvCity;
        ImageView imageView;


        public MyViewHolder(View convertView) {
            tvName = convertView.findViewById(R.id.tvName);
            tvAge = convertView.findViewById(R.id.tvAge);
            tvCity = convertView.findViewById(R.id.tvCity);
            imageView = convertView.findViewById(R.id.imageView);
        }
    }

    @Override
    public int getCount() {
        return cityList.size();
    }

    @Override
    public Object getItem(int position) {
        return cityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflter.inflate(R.layout.item_layout, parent, false);

        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvAge = convertView.findViewById(R.id.tvAge);
        TextView tvCity = convertView.findViewById(R.id.tvCity);
        ImageView imageView = convertView.findViewById(R.id.imageView);

        City city = cityList.get(position);
        tvCity.setText(city.getCity());
        tvAge.setText(city.getAge());
        tvName.setText(city.getName());
        Glide.with(context).load(city.getImage()).into(imageView);


        return convertView;
    }

}
