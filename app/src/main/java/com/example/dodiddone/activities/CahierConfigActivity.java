package com.example.dodiddone.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dodiddone.R;
import com.example.dodiddone.activities.dialogs.AddCalculDialogFragment;
import com.example.dodiddone.activities.dialogs.AddRegleDialogFragment;
import com.example.dodiddone.db.EntitiesManager;
import com.example.dodiddone.metier.Cahier;
import com.example.dodiddone.metier.Regle;

import java.util.ArrayList;

public class CahierConfigActivity extends AppCompatActivity {

    public static final String CAHIER_ID = "com.example.dodiddone.activities.CahierConfigActivity.CAHIER_ID";

    private long cahierId;
    private Cahier cahier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cahier_config);

        Intent intent = getIntent();
        cahierId = intent.getLongExtra(CAHIER_ID, -1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        reload();
    }

    public void reload() {
        this.cahier = EntitiesManager.getNoPageCahier(getApplicationContext(), cahierId);
        displayRegisteredRegles();
    }

    public void onAddRegle(View btn) {
        Log.println(Log.INFO,"touch", "touch");

        AddRegleDialogFragment dialogFragment = new AddRegleDialogFragment(this.cahier);
        dialogFragment.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                reload();
            }
        });
        dialogFragment.show(this.getSupportFragmentManager(),"AddRegle");
    }

    private void displayRegisteredRegles() {

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

    private View getRegleView(final Regle regle) {
        CardView card = new CardView(this);
        card.setUseCompatPadding(true);
        card.setTag(regle);

        // Filling Card with components
        TextView title = new TextView(this);
        title.setText(getString(R.string.section_cahier_regle_name, regle.getNom(), regle.getType().getNom()));

        card.setOnClickListener(new CardView.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCalculDialogFragment dialogFragment = new AddCalculDialogFragment(regle);
                dialogFragment.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        reload();
                    }
                });
                dialogFragment.show(getSupportFragmentManager(), "AddCalcul");
            }
        });

        card.addView(title);

        return card;
    }
}
