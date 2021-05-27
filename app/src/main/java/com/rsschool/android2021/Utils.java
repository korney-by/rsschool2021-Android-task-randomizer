package com.rsschool.android2021;

import android.widget.EditText;
import android.widget.TextView;

public class Utils {

    public static int getInt(EditText editText) {
        if (editText != null) {
            String value = editText.getText().toString();
            return strToInt(value);
        }
        return 0;
    }

    public static int getInt(TextView textView) {
        if (textView != null) {
            String value = textView.getText().toString();
            return strToInt(value);
        }
        return 0;
    }

    private static int strToInt(String value){
        return value.length() > 0 ? Integer.parseInt(value) : 0;
    }

}
