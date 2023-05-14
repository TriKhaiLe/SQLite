package com.example.classmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<Student> studentList;

    public StudentAdapter(Context context, int layout, List<Student> studentList) {
        this.context = context;
        this.layout = layout;
        this.studentList = studentList;
    }

    @Override
    public int getCount() {
        return studentList.size();
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
        TextView tvDob;
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
            holder.tvDob = view.findViewById(R.id.tv_dob);

            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        Student student = studentList.get(i);
        holder.tvId.setText(String.valueOf(student.getStudentId()));
        holder.tvName.setText(student.getName());
        holder.tvDob.setText(student.getDob());

        return view;
    }
}
