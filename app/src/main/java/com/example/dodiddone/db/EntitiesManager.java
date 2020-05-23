package com.example.dodiddone.db;

import android.content.Context;

import com.example.dodiddone.metier.Cahier;
import com.example.dodiddone.metier.Ligne;
import com.example.dodiddone.metier.Page;
import com.example.dodiddone.metier.Regle;

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
        Set<Page> pages = pdao.selectCahierPages();
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


}
