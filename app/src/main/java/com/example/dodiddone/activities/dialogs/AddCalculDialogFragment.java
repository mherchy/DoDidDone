package com.example.dodiddone.activities.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.example.dodiddone.R;
import com.example.dodiddone.db.CalculDAO;
import com.example.dodiddone.db.RegleDAO;
import com.example.dodiddone.metier.Regle;
import com.example.dodiddone.metier.typedValues.ExplicitTypesEnumItem;
import com.example.dodiddone.metier.typedValues.TypesEnum;
import com.example.dodiddone.metier.typedValues.calcul.Calcul;
import com.example.dodiddone.metier.typedValues.calcul.Calculs;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AddCalculDialogFragment extends MyDialogFragment {

    private Regle regle;

    List<Calcul> possibilities;
    Map<Calcul, Boolean> pendingChanges;

    public AddCalculDialogFragment(Regle regle) {
        this.regle = regle;
        this.pendingChanges = new HashMap<>();
        this.possibilities = Calculs.get(regle.getType());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle(R.string.dialog_manage_regle_calculs_title);


        builder.setView(this.getDialogVue());


        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CalculDAO cldao = new CalculDAO(getContext(), regle);
                cldao.open();
                for (Map.Entry<Calcul, Boolean> change : pendingChanges.entrySet()) {
                    Calcul calcul = change.getKey();
                    if (change.getValue()) {
                        cldao.insert(calcul);
                    } else {
                        cldao.remove(calcul);
                    }
                }
                cldao.close();
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }


    private View getDialogVue() {

        LinearLayout base = new LinearLayout(getContext());
        base.setOrientation(LinearLayout.VERTICAL);

        if(possibilities.isEmpty()) {
            TextView msg = new TextView(getContext());
            msg.setText(R.string.dialog_manage_regle_calculs_no_choices);
            base.addView(msg);
        }
        else {
            for (final Calcul calcul : possibilities) {
                CheckBox cb = new CheckBox(getContext());
                cb.setText(calcul.getDesc());
                cb.setChecked(regle.hasCalcul(calcul));

                cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (pendingChanges.containsKey(calcul)) {
                            pendingChanges.remove(calcul);
                        } else {
                            pendingChanges.put(calcul, isChecked);
                        }
                    }
                });

                base.addView(cb);
            }
        }

        return base;
    }
}
