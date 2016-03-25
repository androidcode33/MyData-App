package com.basasa.mydata;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by basasa on 3/24/2016.
 */
public class first extends AppCompatActivity {
    EditText etx;
    Button btn;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Phone = "phoneKey";
    public static final String Seen = "seenKey";
    static  String phn = null;
    SharedPreferences settings;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);



         etx=(EditText)findViewById(R.id.editText);
         btn=(Button)findViewById(R.id.button);
         btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 phn = etx.getText().toString();

                 settings = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                 SharedPreferences.Editor editor = settings.edit();
                 editor.putString(Phone, phn);
                 editor.putString(Seen, "yes");

                 editor.apply();

                 Intent intent = new Intent(first.this, MainActivity.class);
                 startActivity(intent);
             }
         });





    }

}
