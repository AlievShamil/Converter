package com.example.devcom.converter.controller;

import android.os.AsyncTask;
import android.provider.ContactsContract;

import com.example.devcom.converter.listener.IUrlListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class URLManager extends AsyncTask<String, String, String> {
    private IUrlListener urlListener;

    public String getContent() {
        return content;
    }

    private String content = "";
    @Override
    public String doInBackground(String... params) {
        try{

            content = getContent(params[0]);
        }
        catch (IOException ex){
            content = ex.getMessage();
        }

        return content;

    }

    public void setUrlListener(IUrlListener urlListener) {
        this.urlListener = urlListener;
    }

    @Override
    protected void onPostExecute(String s) {
        if(urlListener!=null) {
            urlListener.whenDownloadContent(s);
        }
    }
    public String getContent(String path) throws IOException {
        URL url = new URL(path);
        HttpURLConnection c = (HttpURLConnection) url.openConnection();
        c.setRequestMethod("GET");
        c.connect();
        return DataController.readFromStream(new InputStreamReader(c.getInputStream(),DataController.charsetName));
    }
}
