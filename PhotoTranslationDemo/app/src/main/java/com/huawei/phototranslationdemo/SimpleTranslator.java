package com.huawei.phototranslationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.mlsdk.translate.MLTranslatorFactory;
import com.huawei.hms.mlsdk.translate.cloud.MLRemoteTranslateSetting;
import com.huawei.hms.mlsdk.translate.cloud.MLRemoteTranslator;

public class SimpleTranslator extends AppCompatActivity {
    TextView tv;
    MLRemoteTranslateSetting settings;
    String sourceLangCode;
    String targetLangCode;
    String sourceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textview1);
        sourceLangCode = "en";
        targetLangCode = "zh";
        settings = new MLRemoteTranslateSetting.Factory()
                .setSourceLangCode(sourceLangCode)
                .setTargetLangCode(targetLangCode)
                .create();
    }

//    public void click(View view) {
//        // Create a text translator using custom parameter settings.
//        MLRemoteTranslateSetting setting = new MLRemoteTranslateSetting
//                .Factory()
//                // Set the source language code. The ISO 639-1 standard is used. This parameter is optional. If this parameter is not set, the system automatically detects the language.
//                .setSourceLangCode("en")
//                // Set the target language code. The ISO 639-1 standard is used.
//                .setTargetLangCode("zh")
//                .create();
//        MLRemoteTranslator mlRemoteTranslator = MLTranslatorFactory.getInstance().getRemoteTranslator(setting);
//
//// sourceText indicates the text to be translated, which cannot exceed 5000 characters.
//        String sourceText = "hello";
//        final Task<String> task = mlRemoteTranslator.asyncTranslate(sourceText);
//        tv = findViewById(R.id.textview1);
//        task.addOnSuccessListener(new OnSuccessListener<String>() {
//            @Override
//            public void onSuccess(String text) {
//                // Processing logic for recognition success;
//                tv.setText(text);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(Exception e) {
//                // Processing logic for recognition failure.
//                tv.setText(e.toString());
//            }
//        });
//
//
//        if (mlRemoteTranslator!= null) {
//            mlRemoteTranslator.stop();
//        }
//    }

    public void click(View view) {
        // Create a text translator.
        // setSourceLangCode: Set the code of the source language. ISO 639-1 standard code.
        // setTargetLangCode: Set the code of the target language. ISO 639-1 standard code.
//        String sourceLangCode = "en";
//        String targetLangCode = "zh";
//        String sourceText = "hello";
//        MLRemoteTranslateSetting settings = new MLRemoteTranslateSetting.Factory()
//                .setSourceLangCode(sourceLangCode)
//                .setTargetLangCode(targetLangCode)
//                .create();
        sourceText = "hello";
        MLRemoteTranslator mlRemoteTranslator = MLTranslatorFactory.getInstance().getRemoteTranslator(settings);
        // Translate text.
        // sourceText: text to be translated.
        final Task<String> task = mlRemoteTranslator.asyncTranslate(sourceText);
        task.addOnSuccessListener(new OnSuccessListener<String>() {
            public void onSuccess(String text) {
                // Processing logic for success.
                tv.setText(text);
            }
        }).addOnFailureListener(new OnFailureListener() {
            public void onFailure(Exception e) {
                // Processing logic for failure.
                tv.setText(e.toString());
            }
        });
        // Release resources.
        mlRemoteTranslator.stop();
    }
}