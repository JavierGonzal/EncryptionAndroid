package com.thedeveloperworldisyous.encryptionfile.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

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

    public static void hideKeyboard(Activity activity) {
        // Check if no view has focus:
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
