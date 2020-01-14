package com.johnmelodyme.bluetooth;
/*
* 나는 블루투스에서 배우고 있기 때문에나는이
* 프로젝트를 시작했다. 블루투스는 나를 위해
* 어렵다,하지만 난 튜토리얼로하고있다.
 */
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * @AUTHOR : JOHN MELODY ME
 * @COPYRIGHT : JOHN MELODY ME
 * @PROJECT: BLUETOOTH
 * @MESSAGE: """I STARTED THIS PROJECT BECAUSE I AM LEARNING ON BLUETOOTH """
 */

public class MainActivity extends AppCompatActivity {
    private Context context = MainActivity.this;
    private static final String THIS_APPLICATION = MainActivity.class.getName();
    private BluetoothAdapter BLUETOOTHADAPTER;
    private Button DISCOVERED_NEARBY_BLUETOOTH_DEVICE, APPLICATION_SETTING;
    private int  REQUEST_ENABLE_BLUETOOTH = 0b1;
    private  AlertDialog.Builder ALERTDIALOGUE;
    private ListView NEARBY_DEVICE_LIST;
    private ArrayAdapter<String> ADAPTER;

    private void INIT() {
        BLUETOOTHADAPTER = BluetoothAdapter.getDefaultAdapter();
        DISCOVERED_NEARBY_BLUETOOTH_DEVICE = (Button) findViewById(R.id.BluetoothDevice);
        APPLICATION_SETTING = (Button) findViewById(R.id.setting);
        ALERTDIALOGUE = (AlertDialog.Builder) new AlertDialog.Builder(context);
    }

    @Override
    public void onStart(){
        super.onStart();
        BLUETOOTH();
    }

    private void BLUETOOTH(){
        String ERR;
        ERR = getResources().getString(R.string.err);
        if (BLUETOOTHADAPTER == null){
            //MAGIC("BLUETOOTH IS NOT ");
            String NOT_SUPPPORTED;
            NOT_SUPPPORTED = getResources().getString(R.string.bluetooth_not_supported_in_this_device);
            new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText(NOT_SUPPPORTED)
                    .show();
            Log.w(THIS_APPLICATION, NOT_SUPPPORTED);
        } else {
            if (!(BLUETOOTHADAPTER.isEnabled())){
                /*
                Intent REQUEST_BLUETOOTH;
                REQUEST_BLUETOOTH = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(REQUEST_BLUETOOTH, REQUEST_ENABLE_BLUETOOTH);
                 */

                Log.w(THIS_APPLICATION, "REQUESTING USER BLUETOOTH");
                new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Enable Bluetooth?")
                        .setContentText(ERR)
                        .setConfirmText("Absolutely Mate")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                BLUETOOTHADAPTER.enable();
                                sDialog.dismissWithAnimation();
                            }
                        })
                        .setCancelButton("Hell No", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                String ERR;
                                ERR = getResources().getString(R.string.err);
                                new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("Oops...")
                                        .setContentText(ERR)
                                        .show();
                                sDialog.dismissWithAnimation();
                            }
                        })
                        .show();
            } else {
                Log.w(THIS_APPLICATION, "REQUEST USER BLUETOOTH FAILED");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        INIT();

        APPLICATION_SETTING.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent TOSETTING;
                TOSETTING = new Intent(MainActivity.this, Application_Setting.class);
                startActivity(TOSETTING);
            }
        });

        DISCOVERED_NEARBY_BLUETOOTH_DEVICE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View ROWLIST = getLayoutInflater().inflate(R.layout.nearby, null);
                NEARBY_DEVICE_LIST = ROWLIST.findViewById(R.id.NEARBY);
                //ADAPTER = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item, //something);

            }
        });
    }

    public void Magic(String Message){
        Context context;
        context = MainActivity.this;
        Toast.makeText(context, Message, Toast.LENGTH_SHORT)
                .show();
    }
}