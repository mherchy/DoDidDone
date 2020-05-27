package com.example.dodiddone.activities.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.dodiddone.R;
import com.example.dodiddone.db.EntitiesManager;
import com.example.dodiddone.db.RegleDAO;
import com.example.dodiddone.metier.Cahier;
import com.example.dodiddone.metier.Ligne;
import com.example.dodiddone.metier.Page;
import com.example.dodiddone.metier.Regle;
import com.example.dodiddone.metier.typedValues.DataType;
import com.example.dodiddone.metier.typedValues.ExplicitTypesEnumItem;
import com.example.dodiddone.metier.typedValues.TypesEnum;
import com.example.dodiddone.metier.typedValues.input.DataTypeInput;

import java.util.Date;

public class AddRegleDialogFragment extends MyDialogFragment {

    private Cahier cahier;

    //Vues
    private LinearLayout form;
    private EditText nameInput;
    private EditText unitInput;
    private Spinner spinner;

    public AddRegleDialogFragment(Cahier c) {
        this.cahier = c;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle(R.string.dialog_create_regle_title);


        builder.setView(this.getDialogVue());


        //TODO: unité

        builder.setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = nameInput.getText().toString();
                String unit = unitInput.getText().length() > 0 ? unitInput.getText().toString() : null;
                ExplicitTypesEnumItem type = (ExplicitTypesEnumItem) spinner.getSelectedItem();
                Regle regle = new Regle(
                        name,
                        unit,
                        type.getItem(),
                        cahier
                );

                RegleDAO rdao = new RegleDAO(getContext(), cahier);
                rdao.open();
                rdao.insert(regle);
                rdao.close();
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
        this.form = new LinearLayout(getContext());
        form.setOrientation(LinearLayout.VERTICAL);

        // Name Input
        this.nameInput = new EditText(getContext());
        nameInput.setHint(R.string.dialog_create_regle_name_input_hint);
        form.addView(nameInput);

        //TODO: add labels

        // DataType Selector
        this.spinner = new Spinner(getContext());
        ArrayAdapter<ExplicitTypesEnumItem> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                TypesEnum.explicitValues()
        );
        spinner.setAdapter(adapter);
        form.addView(spinner);

        // Unit Input
        this.unitInput = new EditText(getContext());
        unitInput.setHint(R.string.dialog_create_regle_unit_input_hint);
        form.addView(unitInput);

        return form;
    }
}
