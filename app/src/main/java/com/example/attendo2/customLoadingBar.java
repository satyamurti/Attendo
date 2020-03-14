package com.example.attendo2;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;

class customLoadingBar {
    Activity activity;
    AlertDialog dialog;

    customLoadingBar(Activity myActivity){

        activity = myActivity;

    }
    void startLoader(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);


        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_loading_bar,null));

        dialog= builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();
    }
    void dismissLoader(){
        dialog.dismiss();
    }
}

//package com.dev_planet.custom_progress_dialog
//
//        import android.app.Dialog
//        import android.content.Context
//        import android.graphics.Color
//        import androidx.core.graphics.drawable.toDrawable
//
//        object CommonUtils{
//
//        fun showLoadingDialog(context:Context):Dialog{
//        val progressDialog = Dialog(context)
//
//        progressDialog.let {
//        it.show()
//        it.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
//        it.setContentView(R.layout.progress_dialog)
//        it.setCancelable(false)
//        it.setCanceledOnTouchOutside(false)
//        return it
//        }
//        }
//
//        }