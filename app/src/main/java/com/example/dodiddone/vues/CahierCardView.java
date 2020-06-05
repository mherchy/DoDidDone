package com.example.dodiddone.vues;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.example.dodiddone.R;
import com.example.dodiddone.metier.Cahier;
import com.example.dodiddone.metier.Regle;
import com.example.dodiddone.metier.typedValues.calcul.Calcul;

import java.util.zip.Inflater;

public class CahierCardView extends CardView {

    private Cahier cahier;

    private LinearLayout itemContainer;
    private ImageButton addBtn;

    public CahierCardView(@NonNull Context context, Cahier cahier) {
        super(context);
        this.cahier = cahier;
        build();
    }

    public Cahier getCahier() {
        return cahier;
    }

    public void setOnClickSideBtn(CardView.OnClickListener clklist) {
        addBtn.setOnClickListener(clklist);
    }

    private void build() {
        this.setUseCompatPadding(true);
        this.setTag(cahier);

        LinearLayout cardLayout = (LinearLayout) LinearLayout.inflate(getContext(), R.layout.cahier_item_layout, null);
        itemContainer = cardLayout.findViewById(R.id.cahier_info_container);
        addBtn = cardLayout.findViewById(R.id.add_on_item_btn);


        // Filling Card with components
        TextView title = new TextView(getContext());
        title.setText(cahier.getNom());
        itemContainer.addView(title);

        for(Regle regle : cahier.getRegles().values()) {
            for(Calcul calcul : regle.getCalculs().values()) {
                TextView calcultxt = new TextView(getContext());
                String content = calcul.getDesc();
                content = content.concat(" : ").concat(calcul.compute(cahier,regle));
                calcultxt.setText(content);
                itemContainer.addView(calcultxt);
            }
        }


        this.addView(cardLayout);
    }
}
