package com.example.myspacialstalker;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import static android.Manifest.permission.*;

public class MainActivity extends AppCompatActivity {

    static boolean finished_phone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //SharedPreferences
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();


        //BroadcastReceiver
        BroadcastReceiver br = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_NEW_OUTGOING_CALL);


        this.registerReceiver(br, filter);

        //while (ContextCompat.checkSelfPermission(this, SEND_SMS)!= PackageManager.PERMISSION_GRANTED
        String[] permissions = {PROCESS_OUTGOING_CALLS, SEND_SMS,READ_PHONE_STATE};
        requestPermissions(permissions, 0);
        int[] permission_resulst = {0, 0, 0};
        onRequestPermissionsResult(0, permissions, permission_resulst);
        //while above?????


        final EditText phone = (EditText) findViewById(R.id.phone);
        final EditText msg = (EditText) findViewById(R.id.msg);
        final TextView state_info = (TextView) findViewById(R.id.state_info);

        MyBroadcastReceiver.msg = msg.getText().toString();

        msg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                MyBroadcastReceiver.msg = msg.getText().toString();
            }
        });

        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().isEmpty()){
                    finished_phone = true;
                }
                else {
                    finished_phone = false;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(finished_phone){
                    state_info.setText("The app is ready to send SMS at outgoing calls");
                }
                else{
                    state_info.setText("please fill the fields above");
                }
                MyBroadcastReceiver.sendTo = phone.getText().toString();
            }
        });

    }


}
