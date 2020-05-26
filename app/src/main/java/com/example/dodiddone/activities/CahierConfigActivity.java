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
import com.example.dodiddone.activities.dialogs.AddPageDialogFragment;
import com.example.dodiddone.activities.dialogs.AddRegleDialogFragment;
import com.example.dodiddone.db.EntitiesManager;
import com.example.dodiddone.metier.Cahier;
import com.example.dodiddone.metier.Regle;

import java.util.ArrayList;

public class CahierConfigActivity extends AppCompatActivity {

    public static final String CAHIER_ID = "com.example.dodiddone.activities.CahierConfigActivity.CAHIER_ID";

    private Cahier cahier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cahier_config);

        Intent intent = getIntent();
        long cid = intent.getLongExtra(CAHIER_ID, -1);
        this.cahier = EntitiesManager.getNoPageCahier(getApplicationContext(), cid);

        loadRegisteredRegles();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRegisteredRegles();
    }

    public void onAddRegle(View btn) {
        Log.println(Log.INFO,"touch", "touch");

        AddRegleDialogFragment dialog = new AddRegleDialogFragment(this.cahier);
        dialog.show(this.getSupportFragmentManager(),"AddRegle");
    }

    private void loadRegisteredRegles() {

        LinearLayout container = (LinearLayout) findViewById(R.id.registered_regles_container);
        container.removeAllViews();

        if(this.cahier.getRegles().size() == 0) {
            TextView message = new TextView(this);
            message.setText(R.string.section_cahier_config_no_regle_msg);
            container.addView(message);
        }
        else {
            ArrayList<View> list = new ArrayList<>();
            for(Regle regle : this.cahier.getRegles().values()) {
                container.addView(getRegleView(regle));
            }
        }
    }

    private View getRegleView(Regle regle) {
        CardView card = new CardView(this);
        card.setUseCompatPadding(true);
        card.setTag(regle);

        // Filling Card with components
        TextView title = new TextView(this);
        title.setText(getString(R.string.section_cahier_regle_name, regle.getNom(), regle.getType().getNom()));


        card.addView(title);

        return card;
    }
}
