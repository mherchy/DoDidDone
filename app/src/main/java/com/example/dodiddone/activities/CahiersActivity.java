package com.example.dodiddone.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dodiddone.R;
import com.example.dodiddone.activities.dialogs.CreateCahierDialogFragment;
import com.example.dodiddone.db.CahierDAO;
import com.example.dodiddone.metier.Cahier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class CahiersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayCahiers();

    }


    protected void displayCahiers() {
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

        card.setOnClickListener(new CardView.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return card;
    }


    public void onCreateCahier(View btn) {
        Log.println(Log.INFO,"touch", "touch");

        CreateCahierDialogFragment dialog = new CreateCahierDialogFragment();
        dialog.show(this.getSupportFragmentManager(),"CreateCahier");
    }

}
