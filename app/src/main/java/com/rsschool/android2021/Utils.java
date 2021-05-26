package com.rsschool.android2021;

import android.widget.EditText;
import android.widget.TextView;

public class Utils {

    public static int getInt(EditText editText) {
        int result = 0;
        if (editText != null) {
            String value = editText.getText().toString();
            result = strToInt(value);
        }
        return result;
    }

    public static int getInt(TextView textView) {
        int result = 0;
        if (textView != null) {
            String value = textView.getText().toString();
            result = strToInt(value);
        }
        return result;
    }

    private static int strToInt(String value){
        return value.length() > 0 ? Integer.parseInt(value) : 0;
    }

}
