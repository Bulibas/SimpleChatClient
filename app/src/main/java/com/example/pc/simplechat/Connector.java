package com.example.pc.simplechat;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Random;

/**
 * Created by PC on 23.10.2016 г..
 */
public class Connector implements Runnable {
    static Socket socket;
    private Context context;
    static PrintStream writer;
    static BufferedReader bufferedReader;

    public Connector(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        try {
            socket = new Socket("192.168.0.100", 8080);
            socket.setTcpNoDelay(true);
            writer = new PrintStream(Connector.socket.getOutputStream());
            Random rand = new Random();
            int num = rand.nextInt(50);
            String connString = "Client" + num;
            writer.write(connString.getBytes());
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (socket.isConnected()) {
                Reader reader = new Reader(bufferedReader,context);
                reader.execute();
                while(!reader.isCancelled()) {

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
