package com.example.myapplication.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.myapplication.IResulService;
import com.example.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

public class AsynTaskMovies extends AsyncTask {

    private IResulService iResulService;
    private Context context;
    private ProgressDialog progressDialog;

    public AsynTaskMovies(Context context) {
        this.context = context;
    }

    public void setOnResulService(IResulService iResulService) {
        this.iResulService = iResulService;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getString(R.string.loading));
        progressDialog.show();

    }

    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            return HttpRequest.getRequest(objects[0].toString());
        } catch (Exception e) {
            return e;
        }

    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);

        if (progressDialog != null)
            progressDialog.dismiss();
        if (!(o instanceof Exception)) {
            iResulService.onSucess(o.toString());
        } else {
            iResulService.onError((Exception) o);
        }
    }
}
