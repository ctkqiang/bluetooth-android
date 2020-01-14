package com.johnmelodyme.bluetooth;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * @AUTHOR : JOHN MELODY ME
 * @COPYRIGHT : JOHN MELODY ME
 * @PROJECT: BLUETOOTH
 * @MESSAGE: """I STARTED THIS PROJECT BECAUSE I AM LEARNING ON BLUETOOTH """
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private BluetoothAdapter BLUETOOTHADAPTER;
    private Button DISCOVERED_NEARBY_BLUETOOTHDEVICE, APPLICATION_SETTING;
    private int  REQUEST_ENABLE_BLUETOOTH = 0b1;

    private void INIT() {
        BLUETOOTHADAPTER = BluetoothAdapter.getDefaultAdapter();

        DISCOVERED_NEARBY_BLUETOOTHDEVICE = (Button) findViewById(R.id.BluetoothDevice);
        APPLICATION_SETTING = (Button) findViewById(R.id.setting);
    }

    @Override
    public void onStart(){
        super.onStart();
        BLUETOOTH();
    }

    private void BLUETOOTH(){
        if (BLUETOOTHADAPTER == null){
            //MAGIC("BLUETOOTH IS NOT ");
            String NOT_SUPPPORTED;
            NOT_SUPPPORTED = getResources().getString(R.string.bluetooth_not_supported_in_this_device);
            new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText(NOT_SUPPPORTED)
                    .show();
            Log.w(TAG, NOT_SUPPPORTED);
        } else {
            if (!(BLUETOOTHADAPTER.isEnabled())){
                /*
                Intent REQUEST_BLUETOOTH;
                REQUEST_BLUETOOTH = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(REQUEST_BLUETOOTH, REQUEST_ENABLE_BLUETOOTH);
                 */
                Log.w(TAG, "REQUESTING USER BLUETOOTH");
                new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Enable Bluetooth?")
                        .setContentText("Application will not work without bluetooth enabled")
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
                                BLUETOOTHADAPTER.disable();
                                sDialog.dismissWithAnimation();
                            }
                        })
                        .show();
            } else {
                Log.w(TAG, "REQUEST USER BLUETOOTH FAILED");
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
    }
}