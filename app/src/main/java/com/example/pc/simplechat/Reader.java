package com.example.pc.simplechat;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Handler;

/**
 * Created by PC on 23.10.2016 г..
 */
public class Reader extends AsyncTask<String, String, String> {
    private BufferedReader reader;
    private Context context;
    private Handler handler;
    private static String msg;

    public Reader( BufferedReader reader,Context context) {
        this.reader =reader;
        this.context=context;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            msg= readMessage();
            return msg;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        updateMessageBox(msg);
            this.cancel(true);

    }

    public String readMessage() throws IOException {
        String message = reader.readLine();
        Log.e("msg", message);
        return message;
    }
    public void updateMessageBox(final String message) {
        TextView messageBox = (TextView)((Activity)context).findViewById(R.id.textView);
        messageBox.setText(messageBox.getText().toString()+"\n" + message);
    }
}
