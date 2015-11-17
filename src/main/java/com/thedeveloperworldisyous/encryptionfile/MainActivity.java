package com.thedeveloperworldisyous.encryptionfile;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.thedeveloperworldisyous.encryptionfile.utils.SupportedAlgorithms;
import com.thedeveloperworldisyous.encryptionfile.view.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<String> mList;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.content_main_list_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        mList = new ArrayList();


    }

    @Override
    public void onClick(View view) {
        refrestList();
        Snackbar.make(view, R.string.activity_main_cryptographic_algorithms, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void refrestList() {
        mList = SupportedAlgorithms.listSupportedAlgorithms();
        ListAdapter adapter = new ListAdapter(this, mList);
        mListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //creates a menu inflater
        MenuInflater inflater = getMenuInflater();
        //generates a Menu from a menu resource file
        //R.menu.main_menu represents the ID of the XML resource file
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        boolean returnBoolean = false;
        //check selected menu item
        switch (item.getItemId()) {

            case R.id.action_aes:
                Intent intent = new Intent(this, AESActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                //close the Activity
                returnBoolean = true;
                break;

            case R.id.action_rsa:
                Intent intentActionRSA = new Intent(this, RSAActivity.class);
                startActivity(intentActionRSA);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                //close the Activity
                returnBoolean =  true;
                break;
        }
        return returnBoolean;
    }

}
