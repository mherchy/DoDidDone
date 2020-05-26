package com.example.dodiddone.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
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

    private Set<Cahier> cahiers;

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
        this.cahiers =  cdao.selectAll();
        cdao.close();

        LinearLayout container = (LinearLayout) findViewById(R.id.cahieract_caihiers_container);

        ArrayList<View> cards = new ArrayList<>();

        Iterator<Cahier> ic = this.cahiers.iterator();
        while (ic.hasNext()) {
            Cahier c = ic.next();
            View v = this.getVueFromCahier(c);
            container.addView(v);
        }
    }

    protected View getVueFromCahier(Cahier c) {
        Context appCtxt = getApplicationContext();
        CardView card = new CardView(appCtxt);
        card.setUseCompatPadding(true);
        card.setTag(c);

        // Filling Card with components
        TextView title = new TextView(appCtxt);
        title.setText(c.getNom());
        card.addView(title);

        card.setOnClickListener(new CardView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cahier cahier = (Cahier) v.getTag();
                Intent intent = new Intent(getApplicationContext(), CahierActivity.class);
                intent.putExtra(CahierActivity.CAHIER_ID, cahier.getId());
                startActivity(intent);
            }
        });

        return card;
    }


    public void onClickCreateCahier(View btn) {
        Log.println(Log.INFO,"touch", "touch");

        CreateCahierDialogFragment dialog = new CreateCahierDialogFragment();
        dialog.show(this.getSupportFragmentManager(),"CreateCahier");
    }

}
