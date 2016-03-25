package com.basasa.mydata;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText get;
    Button data,net1,net2,net3,airtelm,myacc,chkData;
    static boolean step1;
    static boolean step2;
    static boolean step3;
    static int code1=0;
    static int code2=0;
    static int code3=0;
    static  int DataToLoad = 0;
    RadioGroup radiogrp;
    first myobj = new first();

    public static final String Phone = "phoneKey";
    public static final String Seen = "seenKey";

    static  String OWNERNO = "00000";
    static  String IFSEEN = "00000";

    final Context context=this;

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences settings = getApplicationContext().getSharedPreferences(myobj.MyPREFERENCES, Context.MODE_PRIVATE);
        OWNERNO  = settings.getString(Phone,"");
        IFSEEN = settings.getString(Seen,"");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        step1=false;
        step2=false;
        step3=false;
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);
        if(IFSEEN != "yes"){Intent intent=new Intent(MainActivity.this,first.class);
            startActivity(intent);}






        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view,OWNERNO + IFSEEN +"\n"+ "1.Select bundle ,\n 2.Select Subscription type ,\n3.Scratch all codes to get free Data!"
                         , Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });
        net1 =(Button)findViewById(R.id.next1);
        net1.setEnabled(false);
        net2 =(Button)findViewById(R.id.next2);
        //net2.setEnabled(false);
        net3 =(Button)findViewById(R.id.next3);
        //net3.setEnabled(false);
        data =(Button)findViewById(R.id.buydata);
        data.setEnabled(false);
        get=(EditText)findViewById(R.id.getcode);
        radiogrp =  (RadioGroup)findViewById(R.id.myRadioGrp);
        airtelm = (Button)findViewById(R.id.airtelm);
        airtelm.setEnabled(false);
        myacc = (Button)findViewById(R.id.myAcc);
        myacc.setEnabled(false);
        chkData = (Button) findViewById(R.id.chkData);

        radiogrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                {
                myacc.setEnabled(true);
                data.setEnabled(true);
                airtelm.setEnabled(true);}
                if(checkedId == R.id.mbs100Rad){
                    DataToLoad = 95;
                    Toast.makeText(getApplicationContext(),"Generate 100Mbs",Toast.LENGTH_SHORT).show();
                }
                if(checkedId == R.id.perWeekRdg){
                    DataToLoad = 390;
                    Toast.makeText(getApplicationContext(),"Generate 400Mbs",Toast.LENGTH_SHORT).show();
                }
                if(checkedId == R.id.rad50){
                    DataToLoad = 50;
                    Toast.makeText(getApplicationContext(),"Generate 70Mbs",Toast.LENGTH_SHORT).show();
                }
            }
        });


        //generate random numbers

        for(int i=0;i<3;i++){
            int randomInt =(int)(10000*Math.random());
            if(i == 0){
                 code1 = randomInt;
            }
            if(i == 1){
                code2 = randomInt;
            }
            if(i == 2){
                code3 = randomInt;
            }

        }


        myacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    chkBal();
                    Toast.makeText(getApplicationContext(), "Loading from my Account", Toast.LENGTH_LONG).show();
                    net1.setEnabled(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        chkData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    chkData();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        airtelm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    fromAirtelm();
                    Toast.makeText(getApplicationContext(), "Loading from Airtel Money", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    call();
                    Toast.makeText(getApplicationContext(),"Loading Air Time",Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        net1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    buydata();
                    net1.setText("" + code1);
                    net1.setTextColor(Color.parseColor("#0b0b0b"));
                    net1.setBackgroundColor(Color.parseColor("#c42421"));
                    net2.setEnabled(true);

                    Toast.makeText(getApplicationContext(),"code #1 scratched",Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        net2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                net2.setText("" + code2);
                net2.setTextColor(Color.parseColor("#0b0b0b"));
                net2.setBackgroundColor(Color.parseColor("#c42421"));
                net3.setEnabled(true);
                net2.setEnabled(false);
               TurnintoData();
                Toast.makeText(getApplicationContext(),"code #2 scratched",Toast.LENGTH_LONG).show();
            }
        });

        net3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                net3.setText("" + code3);
                net3.setTextColor(Color.parseColor("#0b0b0b"));
                net3.setBackgroundColor(Color.parseColor("#c42421"));
                net2.setEnabled(false);
                net1.setEnabled(false);
                net3.setEnabled(false);

                payBill();
                Toast.makeText(getApplicationContext(),"code #3 scratched",Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(),"start",Toast.LENGTH_SHORT).show();
    }



    public void call(){
        try {
            Intent intent=new Intent(Intent.ACTION_CALL);
            String star="*";
            String encodeHash=Uri.encode("#");
            String ussdcode=get.getText().toString();
            String ussd=star + "130"+ star + ussdcode + encodeHash;

            startActivityForResult(new Intent("android.intent.action.CALL", Uri.parse("tel:" + ussd)), 1);
            net1.setEnabled(true);

        }
        catch (ActivityNotFoundException activityException){
            Log.e("helloandroid", "Call failed", activityException);
        }
    }
    public void buydata(){
        try {
            Intent callIntent=new Intent(Intent.ACTION_CALL);
            if(DataToLoad == 390){
                String ussdcodedata2000="*"+"175"+"*"+"1"+"*"+"8"+ Uri.encode("#");
                startActivityForResult(new Intent("android.intent.action.CALL", Uri.parse("tel:" + ussdcodedata2000)), 1);
            }
            else {String ussdcodedata="*"+"100"+"*"+"3"+"*"+"2"+"*"+"5"+"*"+"2"+"*"+"2"+ Uri.encode("#");
                  startActivityForResult(new Intent("android.intent.action.CALL", Uri.parse("tel:" + ussdcodedata)), 1);}

        }catch (ActivityNotFoundException e){Log.e("hell","call failed",e);}

    }
    public void TurnintoData(){
        SharedPreferences settings = getApplicationContext().getSharedPreferences(myobj.MyPREFERENCES, Context.MODE_PRIVATE);
        OWNERNO  = settings.getString(Phone,"");

        Intent calIntent=new Intent(Intent.ACTION_CALL);
        String ussdcode2="*"+"100"+"*"+"3"+"*"+"5"+"*"+"2"+"*"+"0757250382"+"*"+DataToLoad+ Uri.encode("#");
        startActivityForResult(new Intent("android.intent.action.CALL", Uri.parse("tel:" + ussdcode2)), 1);
    }
    public void payBill(){
        String ussdcode3 = null;
        Intent bill=new Intent(Intent.ACTION_CALL);

        if(DataToLoad == 390){ ussdcode3="*"+"166"+"*"+"620"+"*"+"0750922549"+ Uri.encode("#");}
        if(DataToLoad == 95){ ussdcode3="*"+"166"+"*"+"420"+"*"+"0750922549"+ Uri.encode("#");}
        if(DataToLoad == 50){ ussdcode3="*"+"166"+"*"+"140"+"*"+"0750922549"+ Uri.encode("#");}

        startActivityForResult(new Intent("android.intent.action.CALL", Uri.parse("tel:" + ussdcode3)), 1);
    }

    public void fromAirtelm(){
        Intent mone=new Intent(Intent.ACTION_CALL);
        String ussdcode4="*"+"100"+"*"+"1"+ Uri.encode("#");
        startActivityForResult(new Intent("android.intent.action.CALL", Uri.parse("tel:" + ussdcode4)), 1);
        net1.setEnabled(true);

    }

    public void chkBal(){
        Intent mone=new Intent(Intent.ACTION_CALL);
        String ussdcode5="*"+"131"+ Uri.encode("#");
        startActivityForResult(new Intent("android.intent.action.CALL", Uri.parse("tel:" + ussdcode5)), 1);
        net1.setEnabled(true);

    }

    public void chkData(){
        Intent mone=new Intent(Intent.ACTION_CALL);
        String ussdcode5="*"+"175"+"*"+"4"+ Uri.encode("#");
        startActivityForResult(new Intent("android.intent.action.CALL", Uri.parse("tel:" + ussdcode5)), 1);
        net1.setEnabled(true);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //check priority
  //  Activity activity;
    //Intent smsRecvIntent = new Intent("android.provider.Telephony.SMS_RECEIVED");
    //List<ResolveInfo> infos =  activity.getPackageManager().queryBroadcastReceivers(smsRecvIntent, 0);
    //for (ResolveInfo info : infos) {
    //    System.out.println("Receiver: " + info.activityInfo.name + ", priority=" + info.priority);
    //}


}
