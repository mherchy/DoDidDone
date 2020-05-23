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
import com.example.dodiddone.activities.dialogs.CreateCahierDialogFragment;
import com.example.dodiddone.db.CahierDAO;
import com.example.dodiddone.db.EntitiesManager;
import com.example.dodiddone.db.PageDAO;
import com.example.dodiddone.metier.Cahier;
import com.example.dodiddone.metier.Page;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class PagesActivity extends AppCompatActivity {

    public static final String START_ACTIVITY = "com.example.dodiddone.activities.PagesActivity.START_ACTIVITY";

    private Cahier cahier;
    private LinkedList<Page> pages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pages_activity);

        Intent intent = getIntent();
        long cid = intent.getLongExtra(START_ACTIVITY, -1);

        Log.i("IntendID", "page id = "+cid);

        fetchData(cid);
        displayPages();

    }


    private void fetchData(long cid) {
        Context appCtxt = getApplicationContext();
        this.cahier =  EntitiesManager.getCompleteCahier(appCtxt, cid);
        this.pages = this.cahier.getPages();
    }


    private void displayPages() {

        LinearLayout container = (LinearLayout) findViewById(R.id.pageact_pages_container);

        ArrayList<View> cards = new ArrayList<>();

        Iterator<Page> ip = this.pages.iterator();
        while (ip.hasNext()) {
            Page p = ip.next();
            View v = this.getVueFromPage(p);
            container.addView(v);
        }

        container.addTouchables(cards);
    }

    protected View getVueFromPage(Page p) {
        Context appCtxt = getApplicationContext();
        CardView card = new CardView(appCtxt);
        card.setUseCompatPadding(true);
        card.setTag(p);

        // Filling Card with components
        TextView title = new TextView(appCtxt);
        title.setText((CharSequence) "une page");
        card.addView(title);

        return card;
    }



    public void onAddPage(View btn) {
        Log.println(Log.INFO,"touch", "touch");

        AddPageDialogFragment dialog = new AddPageDialogFragment(this.cahier);
        dialog.show(this.getSupportFragmentManager(),"AddPage");
    }

}
