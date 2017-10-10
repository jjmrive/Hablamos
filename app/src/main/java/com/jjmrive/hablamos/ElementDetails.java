package com.jjmrive.hablamos;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class ElementDetails extends AppCompatActivity {

    private static final String EXTRA_ELEMENT_POS = "element_pos";

    private int initialPos;
    private ArrayList<Element> elementsList;

    private ElementDetailsAdapter elementDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.element_details_title));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initialPos = getIntent().getIntExtra(EXTRA_ELEMENT_POS, 0);
        elementsList = DataHolder.getElementsList();

        elementDetailsAdapter = new ElementDetailsAdapter(getSupportFragmentManager(), elementsList);

        ViewPager viewPager = (ViewPager) findViewById(R.id.element_view_pager);
        viewPager.setAdapter(elementDetailsAdapter);
        viewPager.setCurrentItem(initialPos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.delete_menu:
                return false;
        }
        return false;
    }

}
