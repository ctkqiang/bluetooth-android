package com.johnmelodyme.bluetooth;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * @AUTHOR : JOHN MELODY ME
 * @COPYRIGHT : JOHN MELODY ME
 * @PROJECT: BLUETOOTH

 */


public class MainActivity extends AppCompatActivity {
    private BluetoothAdapter BLUETOOTHADAPTER;

    private void INIT() {
        BLUETOOTHADAPTER = BluetoothAdapter.getDefaultAdapter();
    }

    @Override
    public void onStart(){
        super.onStart();
        if (BLUETOOTHADAPTER == null){
            //MAGIC("BLUETOOTH IS NOT ");
            String NOT_SUPPPORTED;
            NOT_SUPPPORTED = getResources().getString(R.string.bluetooth_not_supported_in_this_device);
            new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText(NOT_SUPPPORTED)
                    .show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        INIT();
    }

    public void MAGIC(String MESSAGE){
        Toast.makeText(MainActivity.this, MESSAGE,
                Toast.LENGTH_SHORT)
                .show();
    }
}
