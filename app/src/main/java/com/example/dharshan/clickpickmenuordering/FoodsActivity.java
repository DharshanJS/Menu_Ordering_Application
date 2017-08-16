package com.example.dharshan.clickpickmenuordering;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;

public class FoodsActivity extends AppCompatActivity {

    MyApp mApp;
    private HashMap<String, Location> locations;
    ListView listView1;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods);
        db=openOrCreateDatabase("Foods_DB", Context.MODE_PRIVATE, null);
        setContentView(R.layout.activity_drinks);
        locations = loadLocationData();
        addListenerOnButton();
        initializeUI();

    }

    private void addListenerOnButton() {
        final Context context = this;
        listView1 = (ListView) findViewById(R.id.listView2);
        listView1.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    //@Override
                    public void onItemClick(AdapterView<?> arg0, View view,
                                            int position, long id) {
                        try {
                            Object o = listView1.getItemAtPosition(position);
                            String pen = o.toString();

                            mApp = ((MyApp)getApplicationContext());
                            mApp.setGlobalVarValue(pen);
                            Toast.makeText(getApplicationContext(), "You have chosen the" + " " + pen, Toast.LENGTH_LONG).show();
                            //showMessage("test", "test");
                        }catch(Exception e)
                        {
                            String s=e.getMessage();
                        }
                    }
                }
        );



    }

    private void initializeUI()
    {
        String[] cities = getCityNames();

        // simple_list_item_1 is a SDK provided layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,cities);
        listView1.setAdapter(adapter);
    }

    private String[] getCityNames()
    {
        String[] cities = new String[locations.size()];
        cities = locations.keySet().toArray(cities);
        return cities;
    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    private HashMap<String, Location> loadLocationData() {
        HashMap<String, Location> locations = new HashMap<String, Location>();
        Cursor c=db.rawQuery("SELECT * FROM table_foods order by food_id asc", null);
        StringBuffer buffer=new StringBuffer();
        while(c.moveToNext())
        {

            locations.put("- "+c.getString(1).toString()+" [$"+c.getString(2).toString()+"]", new Location(Integer.parseInt(c.getString(0)),c.getString(1).toString(),Double.parseDouble(c.getString(2))));
        }

        return locations;



    }
}
