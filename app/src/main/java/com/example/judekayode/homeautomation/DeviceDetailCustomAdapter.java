package com.example.judekayode.homeautomation;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class DeviceDetailCustomAdapter extends BaseAdapter {

    Context context;
    List<DevicesDetails> rowItems;

    DeviceDetailCustomAdapter(Context context, List<DevicesDetails> rowItems) {
        this.context = context;
        this.rowItems = rowItems;
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position) {

        return rowItems.indexOf(getItem(position));
    }

    /* private view holder class */
    private class ViewHolder {

        TextView device_id;
        TextView device_name;
        ToggleButton device_value;
        SeekBar device_value2;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.content_detail, null);
            holder = new ViewHolder();

            holder.device_id = (TextView) convertView
                    .findViewById(R.id.device_idtextview);
            holder.device_name = (TextView) convertView.findViewById(R.id.device_nametextview);
            holder.device_value = (ToggleButton) convertView
                    .findViewById(R.id.myswitchbut);
            holder.device_value2 = (SeekBar) convertView.findViewById(R.id.seekbar);

            DevicesDetails dev_pos = rowItems.get(position);

            //set the device_id
            holder.device_id.setText(dev_pos.getDevice_id());

            //set the device name
            holder.device_name.setText(dev_pos.getDevice_name());

            String device_type_id = dev_pos.getDevice_type_id();

            if(device_type_id.equals("1")){
                String devcurrentval = dev_pos.getDevice_value();
                holder.device_value.setVisibility(View.VISIBLE);
                if(devcurrentval.equals("1")){
                    holder.device_value.setText("ON");
                    holder.device_value.setTextColor(Color.RED);
                }
                else{
                    holder.device_value.setText("OFF");
                    holder.device_value.setTextColor(Color.BLUE);
                }

                holder.device_value2.setVisibility(View.INVISIBLE);
            }
            else {
                String deevcurrent = dev_pos.getDevice_value();
                holder.device_value.setVisibility(View.INVISIBLE);
                holder.device_value2.setVisibility(View.VISIBLE);
                holder.device_value2.setProgress(Integer.parseInt(deevcurrent));

            }
            //set the device value
//            holder.device_value.setText(dev_pos.getDevice_value());

//            holder.device_value.setVisibility(View.VISIBLE);
//            holder.dev_value.setProgress(Integer.parseInt(dev_pos.getDevice_value()));

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            convertView.setTag(holder);
        }

        return convertView;
    }

}


