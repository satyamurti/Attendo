package com.example.attendo2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attendo2.R;
import com.example.attendo2.attendance_of_each_subject;

import java.util.ArrayList;

public class CustomListAdapterForEachSub extends BaseAdapter implements View.OnClickListener{
    private ArrayList<attendance_of_each_subject> dates;
    private Context context;

    public CustomListAdapterForEachSub(ArrayList<attendance_of_each_subject>dates, Context context) {
        this.dates = dates;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dates.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=layoutInflater.inflate(R.layout.custom_list_view_foreachsub,null);
        RelativeLayout option;
        ImageView photo;
        if(view==null){
            photo=new ImageView(context);
        }
        attendance_of_each_subject userInfo= dates.get(i);
        photo=(ImageView)view.findViewById(R.id.photo);
        option=(RelativeLayout)view.findViewById(R.id.option);
        TextView subjects=(TextView)view.findViewById(R.id.name);
        TextView allattendance=(TextView)view.findViewById(R.id.profession);
        //photo.setImageResource(userInfo.getPhoto());
        subjects.setText(userInfo.getDates());
        allattendance.setText(userInfo.getPresenty());
        option.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.option:
//                showPopupMenu(view);
                Toast.makeText(context, "Lol You just clicked Me!!!", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    //file search result
    public void filterResult(ArrayList<attendance_of_each_subject> newUserInfos){
        dates =new ArrayList<>();
        dates.addAll(newUserInfos);
        notifyDataSetChanged();
    }

}
