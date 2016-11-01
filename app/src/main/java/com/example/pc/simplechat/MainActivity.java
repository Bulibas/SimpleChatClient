package com.example.pc.simplechat;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = (EditText)findViewById(R.id.editText);
        final Context context = this;

        //connector runs of other thread, cause connections cant be made on android main thread
        Connector connector = new Connector(context);
        Thread thread = new Thread(connector);
        thread.start();

        Button sendButton = (Button)findViewById(R.id.button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = editText.getText().toString();
                editText.setText("");
                Writer writer = new Writer(msg, context);
                Thread writerThread = new Thread(writer);
                writerThread.start();
            }
        });


    }

}
