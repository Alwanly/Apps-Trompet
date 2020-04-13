package com.example.rpl.trompey;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CostumAdapter extends RecyclerView.Adapter<CostumAdapter.ViewHolder> {
    Context context;
    ArrayList<RowItem>rowItems;

    public CostumAdapter(Context context,  ArrayList<RowItem>rowItems) {
        this.context = context;
        this.rowItems = rowItems;
    }


    @NonNull
    @Override
    public CostumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RowItem ri = rowItems.get(position);
        holder.bindTo(ri);
    }

    @Override
    public int getItemCount() {
        return rowItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profile_pic;
        TextView member_name;
        TextView status;
        TextView contactType;

        public ViewHolder(View inflate) {
            super(inflate);
            member_name = inflate.findViewById(R.id.food_name);
            status = inflate.findViewById(R.id.food_harga);
            profile_pic = inflate.findViewById(R.id.profile_pic);
        }

        public void bindTo(RowItem ri) {

            member_name.setText(ri.getMember_name());
            status.setText(ri.getStatus());
            Glide.with(context).load(ri.getProfile_pic_id()).override(50).into(profile_pic);
        }
    }

//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        ViewHolder holder = null;
//        LayoutInflater mInflater = (LayoutInflater) context
//                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//        if (convertView == null) {
//            convertView = mInflater.inflate(R.layout.list_item, null);
//            holder = new ViewHolder();
//
//            holder.member_name = (TextView) convertView
//                    .findViewById(R.id.food_name);
//            holder.profile_pic = (ImageView) convertView
//                    .findViewById(R.id.profile_pic);
//            holder.status = (TextView) convertView.findViewById(R.id.food_harga);
//            holder.contactType = (TextView) convertView
//                    .findViewById(R.id.contact_type);
//
//            com.example.rpl.trompey.RowItem row_pos = rowItems.get(position);
//
//            holder.profile_pic.setImageResource(row_pos.getProfile_pic_id());
//            holder.member_name.setText(row_pos.getMember_name());
//            holder.status.setText(row_pos.getStatus());
//            holder.contactType.setText(row_pos.getContactType());
//
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//        return convertView;
//    }
}
