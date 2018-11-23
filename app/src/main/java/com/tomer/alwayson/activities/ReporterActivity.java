package com.tomer.alwayson.activities;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;
import android.text.InputType;
import android.widget.RadioButton;
import android.widget.TextView;

import com.tomer.alwayson.BuildConfig;
import com.tomer.alwayson.ContextConstatns;
import com.tomer.alwayson.R;
import com.tomer.alwayson.SecretConstants;
import com.tomer.alwayson.helpers.Prefs;

public class ReporterActivity extends Activity implements ContextConstatns {

    private String messageExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
