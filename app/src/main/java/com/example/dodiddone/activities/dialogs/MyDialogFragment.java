package com.example.dodiddone.activities.dialogs;

import android.content.DialogInterface;

import androidx.fragment.app.DialogFragment;

public abstract class MyDialogFragment extends DialogFragment {

    protected DialogInterface.OnDismissListener onDismissListener;

    public MyDialogFragment() {
        this.onDismissListener = new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {}
        };
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        onDismissListener.onDismiss(dialog);
    }
}
