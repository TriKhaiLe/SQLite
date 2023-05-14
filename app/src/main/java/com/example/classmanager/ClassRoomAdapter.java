package com.example.classmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ClassRoomAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<ClassRoom> classRoomList;

    public ClassRoomAdapter(Context context, int layout, List<ClassRoom> classRoomList) {
        this.context = context;
        this.layout = layout;
        this.classRoomList = classRoomList;
    }

    @Override
    public int getCount() {
        return classRoomList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView tvId;
        TextView tvName;
        TextView tvStudents;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder.tvId = view.findViewById(R.id.tv_id);
            holder.tvName = view.findViewById(R.id.tv_name);
            holder.tvStudents = view.findViewById(R.id.tv_students);

            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        ClassRoom classRoom = classRoomList.get(i);
        holder.tvId.setText(String.valueOf(classRoom.getClassId()));
        holder.tvName.setText(classRoom.getClassName());
        holder.tvStudents.setText(String.valueOf(classRoom.getStudents()));

        return view;
    }
}
