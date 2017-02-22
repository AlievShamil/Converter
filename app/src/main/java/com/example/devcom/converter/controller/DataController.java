package com.example.devcom.converter.controller;

import android.content.Context;

import com.example.devcom.converter.models.ValCurs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;


public class DataController {
    private static ValCurs valCurs;
    public static final String charsetName  = "Windows-1251";

    public static ValCurs getValCurs() {
        return valCurs;
    }

    public static void setValCurs(ValCurs valCurs) {
        DataController.valCurs = valCurs;
    }
    public static void loadData(Context context) {
        File file = new File(context.getCacheDir(),"simple.xml");
        try {
            FileInputStream stream = new FileInputStream(file);
            String Content =readFromStream(new InputStreamReader(stream,charsetName));
            valCurs=ValCurs.deserializer(Content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveData(Context context,String Content) {
        try {
            File file = new File(context.getCacheDir(), "simple.xml");
            if (file.exists())
                file.delete();

            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter outStream =new OutputStreamWriter(fos,charsetName);
            outStream.write(Content);
            outStream.flush();
            outStream.close();
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readFromStream(Reader in) throws IOException {
        BufferedReader reader =null;
        String sResult=null;
        try{
            reader= new BufferedReader(in);
            StringBuilder buf=new StringBuilder();
            String line;
            while ((line=reader.readLine()) != null) {
                buf.append(line + "\n");
            }
            sResult=buf.toString();
        }finally {
            if (reader != null) {
                reader.close();
            }

        }
        return sResult;
    }
}
