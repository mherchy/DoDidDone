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
import com.example.dodiddone.db.EntitiesManager;
import com.example.dodiddone.metier.Cahier;
import com.example.dodiddone.metier.Page;
import com.example.dodiddone.vues.PageCardView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class CahierActivity extends AppCompatActivity {

    public static final String CAHIER_ID = "com.example.dodiddone.activities.CahierActivity.CAHIER_ID";

    private Cahier cahier;
    private LinkedList<Page> pages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cahier_activity);

        Intent intent = getIntent();
        long cid = intent.getLongExtra(CAHIER_ID, -1);

        Log.i("IntendID", "page id = "+cid);

        fetchData(cid);

        ((TextView)findViewById(R.id.section_page_title)).setText(this.cahier.getNom());

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
    }

    protected View getVueFromPage(Page p) {
        PageCardView card = new PageCardView(this, p);
        card.setUseCompatPadding(true);
        return card;
    }



    public void onAddPageClick(View btn) {
        Log.println(Log.INFO,"touch", "touch");

        AddPageDialogFragment dialog = new AddPageDialogFragment(this.cahier);
        dialog.show(this.getSupportFragmentManager(),"AddPage");
    }

    public void onConfigClick(View btn) {
        Intent intent = new Intent(this, CahierConfigActivity.class);
        intent.putExtra(CahierConfigActivity.CAHIER_ID, cahier.getId());
        startActivity(intent);
    }

}
