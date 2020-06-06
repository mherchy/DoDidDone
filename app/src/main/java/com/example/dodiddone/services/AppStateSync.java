package com.example.dodiddone.services;

import com.example.dodiddone.metier.Cahier;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class AppStateSync {

    public static void syncCahier(Cahier cahier) {
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {

            //ObjectOutputStream
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(cahier);

            new Thread(new Runnable(){
                @Override
                public void run() {
                    URL url = null;
                    HttpURLConnection con = null;
                    InputStreamReader reader = null;
                    try {
                        url = new URL("http://10.0.2.2:8008/sync");
                        con = (HttpURLConnection) url.openConnection();
                        con.setRequestMethod("POST");
                        con.setRequestProperty("Content-Type", "application/json; utf-8");
                        con.setRequestProperty("Accept", "application/json");
                        con.setDoOutput(true);
                        try(OutputStream os = con.getOutputStream()) {
                            bos.writeTo(os);
                        }
                        reader = new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8);
                        JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        con.disconnect();
                    }
                }
            }).start();

            oos.close();
            bos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
