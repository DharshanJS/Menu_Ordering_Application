package com.example.dharshan.clickpickmenuordering;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.StringTokenizer;

public class TotalsActivity extends AppCompatActivity {

    MyApp mApp;
    EditText et_summary;
    TextView tv_total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_totals);
        mApp=((MyApp)getApplicationContext());
        et_summary = (EditText) findViewById(R.id.et_summary);
        tv_total = (TextView) findViewById(R.id.tv_total);
        et_summary.setText(mApp.getGlobalVarValue());
        String str = mApp.getGlobalVarValue();
        StringTokenizer st = new StringTokenizer(str,"$");
        String test="";
        double total=000.0;
        int count=0;
        while (st.hasMoreElements()) {

            test = st.nextElement().toString().substring(0,2);
            if(count>0)
                total+= Double.parseDouble(test);
            count++;
        }

        tv_total.setText("Total :" + total+"");
        mApp.setGlobalClear();


    }
    public boolean isFloat( String input )
    {
        try
        {
            Float.parseFloat( input );
            return true;
        }
        catch( Exception e)
        {
            return false;
        }
    }
}
