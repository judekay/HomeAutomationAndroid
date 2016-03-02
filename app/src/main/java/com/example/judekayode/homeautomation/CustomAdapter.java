package com.example.judekayode.homeautomation;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

	Context context;
	List<Devices> rowItems;

	CustomAdapter(Context context, List<Devices> rowItems) {
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

		TextView device_group_name;
		TextView device_group_count;
		TextView device_on_count;
        ImageView device_group_image;
		TextView device_group_id;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder = null;

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.listitem_layout, null);
			holder = new ViewHolder();

			holder.device_group_name = (TextView) convertView
					.findViewById(R.id.roomnametextView);
			holder.device_group_count = (TextView) convertView.findViewById(R.id.roomdevicenumbertextView);
			holder.device_on_count = (TextView) convertView
					.findViewById(R.id.prevtextView);
            holder.device_group_image = (ImageView) convertView
                    .findViewById(R.id.roomimage);
			holder.device_group_id = (TextView) convertView
					.findViewById(R.id.devicegroupid);

            Devices dev_pos = rowItems.get(position);

            //set the group device image
            holder.device_group_image.setImageResource(dev_pos.getDevice_group_image());

            //set the device group name
            holder.device_group_name.setText(dev_pos.getDevice_group_name());

            //set the device group count
            holder.device_group_count.setText(dev_pos.getDevice_group_count());

            //set the device on count
            holder.device_on_count.setText(dev_pos.getDevice_on_count());

			//set the device_group_id
			holder.device_group_id.setText(dev_pos.getDevice_group_id());

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
            convertView.setTag(holder);
		}

		return convertView;
	}

}


