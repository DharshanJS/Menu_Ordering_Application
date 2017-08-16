package com.example.dharshan.clickpickmenuordering;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Customer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        addListenerOnButton();
    }

    private void addListenerOnButton() {
        final Context context = this;

        Button button = (Button) findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FoodsActivity.class);
                startActivity(intent);
            }
        });
        Button button2 = (Button) findViewById(R.id.btn_drinks);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DrinksActivity.class);
                startActivity(intent);
            }
        });

        Button button3 = (Button) findViewById(R.id.button7);
        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TotalsActivity.class);
                startActivity(intent);
            }
        });
    }
}
