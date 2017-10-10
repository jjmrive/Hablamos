package com.jjmrive.hablamos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.jjmrive.hablamos.dialogs.AddDialog;

public class Home extends AppCompatActivity implements ElementItemClickListener, AddDialog.OnAddDialogListener{

    private static final String EXTRA_ELEMENT_POS = "element_pos";
    private HomeAdapter homeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.app_name));

        DataHolder.loadElementsList(this.getApplicationContext());
        DataHolder.saveElementsList(this.getApplicationContext());

        homeAdapter = new HomeAdapter(DataHolder.getElementsList(), this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(homeAdapter);
    }

    @Override
    protected void onResume(){
        super.onResume();
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_menu) {

            new AddDialog().show(getSupportFragmentManager(), "AddDialog");

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onElementItemClick(int pos) {

        Intent intent = new Intent(Home.this, ElementDetails.class);
        intent.putExtra(EXTRA_ELEMENT_POS, pos);
        startActivity(intent);

    }

    @Override
    public void onAddElement(String title, String msg, String urlPhoto) {

        Element newElement = new Element(title, urlPhoto, msg);

        DataHolder.getElementsList().add(newElement);
        DataHolder.saveElementsList(this.getApplicationContext());

        homeAdapter.notifyDataSetChanged();

        Toast.makeText(this.getApplicationContext(), getResources().getString(R.string.element_added_ok), Toast.LENGTH_SHORT).show();

    }
}
