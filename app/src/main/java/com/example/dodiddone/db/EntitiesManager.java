package com.example.dodiddone.db;

import android.content.Context;

import com.example.dodiddone.metier.Cahier;
import com.example.dodiddone.metier.Ligne;
import com.example.dodiddone.metier.Page;
import com.example.dodiddone.metier.Regle;

import java.util.LinkedList;
import java.util.Set;

public class EntitiesManager {

    public static Cahier getCompleteCahier(Context appCtxt, long cid) {
        CahierDAO cdao = new CahierDAO(appCtxt);
        cdao.open();

        // Base entity
        Cahier cahier =  cdao.select(cid);

        //Regles
        RegleDAO rdao = new RegleDAO(appCtxt, cahier);
        Set<Regle> regles = rdao.selectCahierRegles();
        cahier.addRegle(regles);

        // Pages
        PageDAO pdao = new PageDAO(appCtxt, cahier);
        LinkedList<Page> pages = pdao.selectCahierPages();
        cahier.addPage(pages);

        // Lignes
        for (Page p : pages) {
            LigneDAO ldao = new LigneDAO(appCtxt, p);
            Set<Ligne> lignes = ldao.selectPageLignes();
            p.addLigne(lignes);
        }

        cdao.close();

        return cahier;

    }


    public static Cahier getNoPageCahier(Context appCtxt, long cid) {
        CahierDAO cdao = new CahierDAO(appCtxt);
        cdao.open();

        // Base entity
        Cahier cahier =  cdao.select(cid);

        //Regles
        RegleDAO rdao = new RegleDAO(appCtxt, cahier);
        Set<Regle> regles = rdao.selectCahierRegles();
        cahier.addRegle(regles);

        cdao.close();

        return cahier;

    }


    public static void insertNewPage(Context context, Cahier cahier, Page page) {
        PageDAO pdao = new PageDAO(context, cahier);
        pdao.open();
        pdao.insert(page);

        LigneDAO ldao = new LigneDAO(context, page);

        for (Ligne l : page.getLignes().values()) {
            ldao.insert(l);
        }

        ldao.close();
    }


}
