package com.example.dodiddone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.example.dodiddone.db.CahierDAO;
import com.example.dodiddone.db.PageDAO;
import com.example.dodiddone.metier.Cahier;
import com.example.dodiddone.metier.Page;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.testCahierPages();
    }

    public void testCahier() {
        Cahier detached = new Cahier("Cahier 1");

        Context context = getApplicationContext();
        CahierDAO cdao = new CahierDAO(context);
        cdao.open();
        long id = cdao.insert(detached);
        Cahier persistant = cdao.select(id);
        cdao.close();
    }

    public void testCahierPages() {
        Cahier detached = new Cahier("Cahier 2");

        Context context = getApplicationContext();
        CahierDAO cdao = new CahierDAO(context);
        cdao.open();
        long idCahier = cdao.insert(detached);
        Cahier persistant = cdao.select(idCahier);

        //Lister les cahiers
        HashSet<Cahier> listCahier = (HashSet<Cahier>) cdao.selectAll();

        cdao.close();

        //Créer une page
        Page p = new Page(idCahier);

        PageDAO pdao = new PageDAO(context);
        pdao.open();
        long idPage = pdao.insert(p);
        HashSet<Page> listPages = (HashSet<Page>) pdao.selectCahierPages(idCahier);


        persistant.addPage(listPages);

        pdao.close();
    }
}
