package com.example.dodiddone.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dodiddone.R;
import com.example.dodiddone.activities.dialogs.AddPageDialogFragment;
import com.example.dodiddone.activities.dialogs.CreateCahierDialogFragment;
import com.example.dodiddone.db.CahierDAO;
import com.example.dodiddone.db.EntitiesManager;
import com.example.dodiddone.metier.Cahier;
import com.example.dodiddone.vues.CahierCardView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class CahiersActivity extends AppCompatActivity {

    private Set<Cahier> cahiers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        reload();
    }

    public void reload() {
        this.cahiers = EntitiesManager.getCompleteCahiers(this);
        displayCahiers();
    }

    public void displayCahiers() {

        LinearLayout container = (LinearLayout) findViewById(R.id.cahieract_caihiers_container);
        container.removeAllViews();

        ArrayList<View> cards = new ArrayList<>();

        Iterator<Cahier> ic = this.cahiers.iterator();
        while (ic.hasNext()) {
            Cahier c = ic.next();
            CahierCardView v = this.getVueFromCahier(c);
            container.addView(v);
        }
    }


    protected CahierCardView getVueFromCahier(final Cahier cahier) {

        CahierCardView card = new CahierCardView(this, cahier);

        card.setOnClickListener(new CardView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cahier cahier = (Cahier) v.getTag();
                Intent intent = new Intent(getApplicationContext(), CahierActivity.class);
                intent.putExtra(CahierActivity.CAHIER_ID, cahier.getId());
                startActivity(intent);
            }
        });

        card.setOnClickSideBtn(new CardView.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPageDialogFragment dialog = new AddPageDialogFragment(cahier);
                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        //reload();
                    }
                });
                dialog.show(getSupportFragmentManager(),"AddPage");
            }
        });

        return card;
    }


    public void onClickCreateCahier(View btn) {
        Log.println(Log.INFO,"touch", "touch");

        CreateCahierDialogFragment dialogf = new CreateCahierDialogFragment();
        dialogf.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                reload();
            }
        });
        dialogf.show(this.getSupportFragmentManager(),"CreateCahier");
    }

}
