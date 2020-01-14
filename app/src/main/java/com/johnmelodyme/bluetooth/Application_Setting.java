package com.johnmelodyme.bluetooth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * @AUTHOR : JOHN MELODY ME
 * @COPYRIGHT : JOHN MELODY ME
 * @PROJECT: BLUETOOTH
 * @MESSAGE: """I STARTED THIS PROJECT BECAUSE I AM LEARNING ON BLUETOOTH """
 */

public class Application_Setting extends AppCompatActivity {
    private String OTHER_SETTING[] = {"About", "Source"};
    ListView Other_Setting, Preference;
    ArrayAdapter O_setting, C_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application__setting);

        Other_Setting = (ListView) findViewById(R.id.other);

        Context context = Application_Setting.this;
        O_setting = new ArrayAdapter<String>(context, R.layout.support_simple_spinner_dropdown_item, OTHER_SETTING);
        Other_Setting.setAdapter(O_setting);

        Other_Setting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String ItemClicked;
                ItemClicked = (String) parent.getItemAtPosition(position);

                if (ItemClicked.equals("About")){
                    Log.w("Tag", "ABOUT");
                } else if (ItemClicked.equals("Source")){
                    SweetAlertDialog pDialog = new SweetAlertDialog(Application_Setting.this, SweetAlertDialog.PROGRESS_TYPE);
                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    pDialog.setTitleText("Loading to the source........");
                    pDialog.setCancelable(false);
                    pDialog.show();
                    Intent SOURCE;
                    SOURCE = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://github.com/johnmelodyme/bluetooth-android"));
                    startActivity(SOURCE);
                    pDialog.dismissWithAnimation();
                }
            }
        });
    }
}
