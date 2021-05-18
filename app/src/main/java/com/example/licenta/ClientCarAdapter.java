package com.example.licenta;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ClientCarAdapter extends BaseAdapter {
    private Context ctx;
    private List<ClientCar> cars;

    public ClientCarAdapter(Context ctxt, List<ClientCar> carslist) {
        this.ctx = ctxt;
        this.cars = new ArrayList<>();
        for (ClientCar c : carslist){
            if(c.isRemembered())
                this.cars.add(c);
        }
    }

    public List<ClientCar> getCars() {
        return cars;
    }

    @Override
    public int getCount() {
        return this.cars.size();
    }

    @Override
    public Object getItem(int position) {
        return this.cars.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        @SuppressLint("ViewHolder") View v = inflater.inflate(R.layout.layout_clientcars, parent,false);
        ClientCar cc = (ClientCar) getItem(position);
        TextView tv_model = v.findViewById(R.id.textViewModel);
        tv_model.setText(cc.getmModel());
        TextView tv_ip = v.findViewById(R.id.textViewIP);
        tv_ip.setText(cc.getmIP());
        TextView tv_ll = v.findViewById(R.id.textViewLastLogin);
        tv_ll.setText("Last login on:\n" + cc.getmLastLogin());
        return v;
    }
}
