package com.johnmelodyme.bluetooth;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

/**
 * @AUTHOR : JOHN MELODY ME
 * @COPYRIGHT : JOHN MELODY ME
 * @PROJECT: BLUETOOTH
 * @MESSAGE: """I STARTED THIS PROJECT BECAUSE I AM LEARNING ON BLUETOOTH """
 */

public class Application_Setting extends AppCompatActivity {
    private String CLASSIC_SETTING[] = {""};
    private String OTHER_SETTING[] = {"About", "Source"};
    ListView Other_Setting, Preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application__setting);

        Other_Setting = (ListView) findViewById(R.id.other);
        Preference = (ListView) findViewById(R.id.classic);
    }
}
