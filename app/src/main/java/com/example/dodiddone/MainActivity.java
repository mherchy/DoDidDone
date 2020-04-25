package com.example.dodiddone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.example.dodiddone.db.CahierDAO;
import com.example.dodiddone.metier.Cahier;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.testCahier();
    }

    public void testCahier() {
        Cahier detached = new Cahier("MonCahier");

        Context context = getApplicationContext();
        CahierDAO cdao = new CahierDAO(context);
        cdao.open();
        long id = cdao.insert(detached);
        Cahier persistant = cdao.select(id);
        cdao.close();
    }
}
