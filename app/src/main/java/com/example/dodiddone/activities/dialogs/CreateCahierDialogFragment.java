package com.example.dodiddone.activities.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.dodiddone.R;
import com.example.dodiddone.db.CahierDAO;
import com.example.dodiddone.db.RegleDAO;
import com.example.dodiddone.metier.Cahier;
import com.example.dodiddone.metier.Regle;
import com.example.dodiddone.metier.typedValues.TypesEnum;

public class CreateCahierDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View form = inflater.inflate(R.layout.new_cahier_dialog_layout,null);

        builder.setTitle(R.string.dialog_create_cahier_title)
                .setView(form)
                .setPositiveButton(R.string.create, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditText txt = (EditText) form.findViewById(R.id.nameInput);
                        CheckBox hasTitle = (CheckBox) form.findViewById(R.id.hasTitle);
                        CheckBox hasDesc = (CheckBox) form.findViewById(R.id.hasDesc);
                        createCahier(txt.getText().toString(), hasTitle.isChecked(), hasDesc.isChecked());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Log.i("BTN","NOP");
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    private void createCahier(String name, boolean hasTitle, boolean hasDesc) {
        Cahier cahier = new Cahier(name);
        CahierDAO cdao = new CahierDAO(getContext());
        cdao.open();
        cdao.insert(cahier);
        RegleDAO rdao = new RegleDAO(getContext(),cahier);
        if(hasTitle) {
            Regle rTitle = new Regle("Title", null, TypesEnum.TITRE, cahier);
            rdao.insert(rTitle);
        }
        if(hasDesc) {
            Regle rDesc = new Regle("Desc", null, TypesEnum.TXTLONG, cahier);
            rdao.insert(rDesc);
        }
        cdao.close();
    }
}
