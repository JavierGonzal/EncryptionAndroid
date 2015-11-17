package com.thedeveloperworldisyous.encryptionfile.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.util.Log;

/**
 * Created by javiergonzalezcabezas on 3/11/15.
 */
public class Utils {

    public static final String sTAG = "Utils";

    public static void writeToFile(OutputStreamWriter outputStreamWriter, String data) {
        try {
//            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
//                    activity.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            //File write failed
            Log.e( sTAG, e.toString());
        }
    }

    public static String readFromFile(Activity activity) {

        String ret = "";

        try {
            InputStream inputStream = activity.openFileInput("config.txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(
                        inputStream);
                BufferedReader bufferedReader = new BufferedReader(
                        inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            //File not found
            Log.e( sTAG, e.toString());
        } catch (IOException e) {
            //Can not read file
            Log.e( sTAG, e.toString());
        }
        return ret;
    }
}
