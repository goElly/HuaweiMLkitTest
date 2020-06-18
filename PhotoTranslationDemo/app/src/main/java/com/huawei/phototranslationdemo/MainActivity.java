package com.huawei.phototranslationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
    }

    public void asr(View view) {
        this.startActivity(new Intent(MainActivity.this, AsrAnalyseActivity.class));
    }

    public void asrDemo(View view) {
        this.startActivity(new Intent(MainActivity.this, ASRDemo.class));
    }

    public void myTrans(View view) {
        this.startActivity(new Intent(MainActivity.this, SimpleTranslator.class));
    }

    public void transAct(View view) {
        this.startActivity(new Intent(MainActivity.this, TranslatorActivity.class));
    }
}