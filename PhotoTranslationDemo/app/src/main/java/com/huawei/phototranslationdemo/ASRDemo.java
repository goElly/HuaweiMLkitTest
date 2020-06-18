package com.huawei.phototranslationdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.huawei.hms.mlplugin.asr.MLAsrCaptureActivity;
import com.huawei.hms.mlplugin.asr.MLAsrCaptureConstants;

public class ASRDemo extends AppCompatActivity {
    TextView tv;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asrdemo);
    }

    public void asrOnClick(View view) {
        // Use Intent for recognition settings.
        Intent intent = new Intent(this, MLAsrCaptureActivity.class)
// Set the language that can be recognized to English. If this parameter is not set, English is recognized by default. Example: "zh": Chinese or "en-US": English
                .putExtra(MLAsrCaptureConstants.LANGUAGE, "en-US")
// Set whether to display text on the speech pickup UI. MLAsrCaptureConstants.FEATURE_ALLINONE: no; MLAsrCaptureConstants.FEATURE_WORDFLUX: yes.
                .putExtra(MLAsrCaptureConstants.FEATURE, MLAsrCaptureConstants.FEATURE_WORDFLUX);

        // 100: request code between the current activity and speech pickup UI activity. You can use this code to obtain the processing result of the speech pickup UI.
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String text = "";
        tv = findViewById(R.id.textView2);
// 100: request code between the current activity and speech pickup UI activity defined in step 2.
        if (requestCode == 100) {
            switch (resultCode) {
// MLAsrCaptureConstants.ASR_SUCCESS: Recognition is successful.
                case MLAsrCaptureConstants.ASR_SUCCESS:
                    if (data != null) {
                        Bundle bundle = data.getExtras();
// Obtain the text information recognized from speech.
                        if (bundle != null && bundle.containsKey(MLAsrCaptureConstants.ASR_RESULT)) {
                            text = bundle.getString(MLAsrCaptureConstants.ASR_RESULT);
                            tv.setText(text);
// Process the recognized text information.
                        }
                    }
                    break;
// MLAsrCaptureConstants.ASR_FAILURE: Recognition fails.
                case MLAsrCaptureConstants.ASR_FAILURE:
// Processing logic for recognition failure.
                    if (data != null) {
                        Bundle bundle = data.getExtras();
// Check whether a result code is contained.
                        if (bundle != null && bundle.containsKey(MLAsrCaptureConstants.ASR_ERROR_CODE)) {
                            int errorCode = bundle.getInt(MLAsrCaptureConstants.ASR_ERROR_CODE);
// Perform troubleshooting based on the result code.
                        }
// Check whether error information is contained.
                        if (bundle != null && bundle.containsKey(MLAsrCaptureConstants.ASR_ERROR_MESSAGE)){
                            String errorMsg = bundle.getString(MLAsrCaptureConstants.ASR_ERROR_MESSAGE);
// Perform troubleshooting based on the error information.
                        }
                    }
                default:
                    break;
            }
        }
    }
}
