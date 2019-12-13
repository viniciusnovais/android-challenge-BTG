package com.example.myapplication.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.myapplication.R;
import com.example.myapplication.managers.GenresManager;
import com.example.myapplication.managers.PopularManager;

public class Helper {


    public static void initialize(Context context) {

        new PopularManager(context);
        new GenresManager(context);
    }


    public static void alertDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.error));
        builder.setMessage(message);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });


        builder.create().show();


    }

}
