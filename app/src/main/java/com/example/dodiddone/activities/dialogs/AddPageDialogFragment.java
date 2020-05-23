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

public class AddPageDialogFragment extends DialogFragment {

    private Cahier cahier;

    public AddPageDialogFragment(Cahier c) {
        //Todo fetch the rules
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View form = inflater.inflate(R.layout.new_page_dialog_layout,null);

        builder.setTitle(R.string.dialog_create_page_title);

        // todo add fields based on the rules

        return builder.create();
    }

}
