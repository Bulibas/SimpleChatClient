package com.example.pc.simplechat;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by PC on 23.10.2016 г..
 */
public class Writer extends  Activity implements  Runnable {
    String msg;
    Context context;

    public Writer(String msg, Context context) {
        this.msg = msg;
        this.context=context;
    }

    @Override
    public void run() {
        try {
            sendMessage(this.msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendMessage(String message) throws IOException {
        Connector.writer.write(message.getBytes());
        Connector.writer.flush();
        //updateMessageBox("Me: " + message);
    }

    public void updateMessageBox(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView messageBox = (TextView)((Activity)context).findViewById(R.id.textView);
                messageBox.setText(messageBox.getText().toString()+"\n" + message);
                //TODO THREAD ENDING OR
            }
        });

    }
}
