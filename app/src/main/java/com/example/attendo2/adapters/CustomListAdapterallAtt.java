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
import com.example.attendo2.models.allsubjectsatt;

import java.util.ArrayList;

public class CustomListAdapterallAtt extends BaseAdapter implements View.OnClickListener{
    private ArrayList<allsubjectsatt> subjects;
    private Context context;

    public CustomListAdapterallAtt(ArrayList<allsubjectsatt> subjects, Context context) {
        this.subjects = subjects;
        this.context = context;
    }

    @Override
    public int getCount() {
        return subjects.size();
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
        view=layoutInflater.inflate(R.layout.custom_list_view_layout_allatt,null);
        RelativeLayout option;
        ImageView photo;
        if(view==null){
            photo=new ImageView(context);
        }
        allsubjectsatt userInfo= subjects.get(i);
        photo=(ImageView)view.findViewById(R.id.photo);
        option=(RelativeLayout)view.findViewById(R.id.option);
        TextView subjects=(TextView)view.findViewById(R.id.name);
        TextView allattendance=(TextView)view.findViewById(R.id.profession);
        photo.setImageResource(userInfo.getPhoto());
        subjects.setText("Subject : "+userInfo.getSubjects());
        allattendance.setText("Your attendance is "+userInfo.getAttendanceall()+" %");
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

//     getting the popup menu
//    private void showPopupMenu(View view){
//        PopupMenu popupMenu=new PopupMenu(context,view);
//        popupMenu.getMenuInflater().inflate(R.menu.option_menu,popupMenu.getMenu());
//        popupMenu.show();
//        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                switch (menuItem.getItemId()){
//                    case R.id.edit:
//                        Toast.makeText(context, "Edit !", Toast.LENGTH_SHORT).show();
//                        return true;
//                    case R.id.remove:
//                        Toast.makeText(context, "Remove !", Toast.LENGTH_SHORT).show();
//                        return true;
//                    default:
//                        return false;
//                }
//            }
//        });
//    }

    //file search result
    public void filterResult(ArrayList<allsubjectsatt> newUserInfos){
        subjects =new ArrayList<>();
        subjects.addAll(newUserInfos);
        notifyDataSetChanged();
    }
}
