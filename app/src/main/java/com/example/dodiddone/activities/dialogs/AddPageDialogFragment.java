package com.example.dodiddone.activities.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.dodiddone.R;
import com.example.dodiddone.db.CahierDAO;
import com.example.dodiddone.db.EntitiesManager;
import com.example.dodiddone.db.RegleDAO;
import com.example.dodiddone.metier.Cahier;
import com.example.dodiddone.metier.Ligne;
import com.example.dodiddone.metier.Page;
import com.example.dodiddone.metier.Regle;
import com.example.dodiddone.metier.typedValues.TypesEnum;
import com.example.dodiddone.metier.typedValues.input.DataTypeInput;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

public class AddPageDialogFragment extends MyDialogFragment {

    private Cahier cahier;

    public AddPageDialogFragment(Cahier c) {
        this.cahier = c;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.dialog_create_page_title);

        // todo add fields based on the rules
        final LinearLayout base = new LinearLayout(getContext());
        base.setOrientation(LinearLayout.VERTICAL);

        for (Regle r : this.cahier.getRegles().values()) {
            DataTypeInput input = r.getType().newInput(getContext());
            input.setLabel(r.getNom());
            input.setRegle(r);
            base.addView((View) input);
        }

        builder.setView(base);

        builder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Page page = new Page(cahier, new Date());
                for (int i=0; i<base.getChildCount(); i++) {
                    DataTypeInput input = (DataTypeInput) base.getChildAt(i);
                    Regle regle = input.getRegle();
                    Ligne ligne = new Ligne(page,regle);
                    ligne.getValeur().setTyped(input.getValue());
                    page.addLigne(ligne);
                }
                EntitiesManager.insertNewPage(getContext(), cahier, page);

                Toast toast = Toast.makeText(getContext(), R.string.dialog_create_page_confirm_msg, Toast.LENGTH_LONG);
                toast.show();
            }
        });

        return builder.create();
    }

}
