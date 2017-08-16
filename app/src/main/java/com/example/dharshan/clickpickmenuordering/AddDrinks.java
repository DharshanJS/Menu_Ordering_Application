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

public class AddDrinks extends AppCompatActivity {

    EditText id, name, price;
    Button btnAdd, btnDelete, btnEdit, btnView;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drinks);
        id=(EditText)findViewById(R.id.et_drink_id);
        name=(EditText)findViewById(R.id.et_drink_name);
        price=(EditText)findViewById(R.id.et_drink_price);
        db=openOrCreateDatabase("Drinks_DB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS table_drinks(drink_id int, drink_name VARCHAR, price int);");

        addListenerOnButton();
    }

    private void addListenerOnButton(){
        final Context context = this;
        btnAdd = (Button) findViewById(R.id.btn_drink_add);
        btnDelete = (Button) findViewById(R.id.btn_drink_delete);
        btnEdit = (Button) findViewById(R.id.btn_drink_edit);
        btnView = (Button) findViewById(R.id.btn_drink_view_all);

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                db.execSQL("INSERT INTO table_drinks VALUES('"+id.getText()+"', '"+name.getText()+"', '"+price.getText()+"');");
                showMessage("success", "Record added");
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                db.execSQL("UPDATE table_drinks SET drink_name = '"+name.getText()+"', price='"+price.getText()+"'" +
                        "WHERE drink_id= '"+ id.getText()+"'");
                showMessage("success", "Record edited");
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                db.execSQL("DELETE FROM table_drinks WHERE drink_id='"+ id.getText() +"'");
                showMessage("success", "Record deleted");
            }
        });

        btnView.setOnClickListener(new  View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Cursor c=db.rawQuery("SELECT * FROM table_drinks", null);
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
                showMessage("Drink Details", buffer.toString());
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
