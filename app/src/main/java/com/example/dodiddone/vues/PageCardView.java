package com.example.dodiddone.vues;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.example.dodiddone.metier.Ligne;
import com.example.dodiddone.metier.Page;

import java.text.DateFormat;

public class PageCardView extends CardView {

    private Page page;

    public PageCardView(@NonNull Context context, Page page) {
        super(context);
        setPage(page);
    }

    public void setPage(Page page) {
        this.page = page;
        this.setTag(page);

        LinearLayout lignesContainer = new LinearLayout(this.getContext());
        lignesContainer.setOrientation(LinearLayout.VERTICAL);

        //Date
        TextView date = new TextView(this.getContext());
        date.setText("Le " + DateFormat.getDateInstance().format(page.getCreation()));

        for(Ligne ligne : this.page.getLignes().values()) {
            TextView vuetxt = new TextView(this.getContext());
            String lignetxt = ligne.getRegle().getNom() + ": "+ligne.getValeur().toString();
            if(ligne.getRegle().getUnite() != null && ligne.getRegle().getUnite().length() > 0) {
                lignetxt += " " + ligne.getRegle().getUnite();
            }
            vuetxt.setText(lignetxt);
            lignesContainer.addView(vuetxt);
        }

        this.addView(lignesContainer);
    }

    public Page getPage() {
        return page;
    }
}
