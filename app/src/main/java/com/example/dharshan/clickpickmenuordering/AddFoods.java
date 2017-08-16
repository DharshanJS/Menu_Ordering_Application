package com.example.dharshan.clickpickmenuordering;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

public class AddFoods extends AppCompatActivity {
    EditText id, name, price;
    Button btnAdd, btnDelete, btnEdit, btnView;
    private HashMap<String,Location> locations;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_foods);

        id=(EditText)findViewById(R.id.et_food_id);
        name=(EditText)findViewById(R.id.et_food_name);
        price=(EditText)findViewById(R.id.et_price);
        db=openOrCreateDatabase("Foods_DB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS table_foods(food_id int, food_name VARCHAR, price int);");

        addListenerOnButton();
    }

    private void addListenerOnButton(){
        final Context context = this;
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnEdit = (Button) findViewById(R.id.btn_edit);
        btnView = (Button) findViewById(R.id.btn_view_all);

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                db.execSQL("INSERT INTO table_foods VALUES('"+id.getText()+"', '"+name.getText()+"', '"+price.getText()+"');");
                showMessage("success", "Record added");
            }
        });


        btnEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                db.execSQL("UPDATE table_foods SET food_name = '"+name.getText()+"', price='"+price.getText()+"'" +
                        "WHERE food_id= '"+ id.getText()+"'");
                showMessage("success", "Record edited");
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                db.execSQL("DELETE FROM table_foods WHERE food_id='"+ id.getText() +"'");
                showMessage("success", "Record deleted");
            }
        });

        btnView.setOnClickListener(new  View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Cursor c=db.rawQuery("SELECT * FROM table_foods", null);
                if(c.getCount()==0)
                {
                    showMessage("Error", "No Records Found");
                    return;

                }
                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("Id: "+c.getString(0)+"\n");
                    buffer.append("Name: "+c.getString(1)+"\n");
                    buffer.append("Price: "+c.getString(2)+"\n\n");

                } 

                showMessage("Food Details", buffer.toString());
            }
        });



    }
    public void  showMessage(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
