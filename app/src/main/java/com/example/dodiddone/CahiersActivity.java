package com.example.dodiddone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dodiddone.db.CahierDAO;
import com.example.dodiddone.db.LigneDAO;
import com.example.dodiddone.db.PageDAO;
import com.example.dodiddone.db.RegleDAO;
import com.example.dodiddone.metier.Cahier;
import com.example.dodiddone.metier.Ligne;
import com.example.dodiddone.metier.Page;
import com.example.dodiddone.metier.Regle;
import com.example.dodiddone.metier.typedValues.DataType;
import com.example.dodiddone.metier.typedValues.NumericDataType;
import com.example.dodiddone.metier.typedValues.TypesEnum;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CahiersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userCahier();

    }


    protected void userCahier() {
        Context appCtxt = getApplicationContext();
        CahierDAO cdao = new CahierDAO(appCtxt);
        cdao.open();
        Set<Cahier> cahiers =  cdao.selectAll();
        cdao.close();

        LinearLayout container = (LinearLayout) findViewById(R.id.cahier_container);

        ArrayList<View> cards = new ArrayList<>();

        Iterator<Cahier> ic = cahiers.iterator();
        while (ic.hasNext()) {
            Cahier c = ic.next();
            View v = this.getVueFromCahier(c);
//            cards.add(v);
            container.addView(v);
        }

        container.addTouchables(cards);

    }

    protected View getVueFromCahier(Cahier c) {
        Context appCtxt = getApplicationContext();
        CardView card = new CardView(appCtxt);
        card.setUseCompatPadding(true);

        // Filling Card with components
        TextView title = new TextView(appCtxt);
        title.setText(c.getNom());
        card.addView(title);

        return card;
    }

}
