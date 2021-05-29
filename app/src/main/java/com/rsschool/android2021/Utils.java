package com.rsschool.android2021;

import android.widget.EditText;
import android.widget.TextView;

public class Utils {

    public static int getIntUnsigned(EditText editText) {
        if (editText != null) {
            String value = editText.getText().toString();
            return strToIntUnsigned(value);
        }
        return 0;
    }


    public static int getIntUnsigned(TextView textView) {
        if (textView != null) {
            String value = textView.getText().toString();
            return strToIntUnsigned(value);
        }
        return 0;
    }

    private static int strToIntUnsigned(String value) {
        // return 0 iа value=""
        // return -1 if value > MAX_INT
        if (!value.isEmpty()) {
            long valueLong = Long.parseLong(value);
            if (valueLong <= Integer.MAX_VALUE) {
                return (int) valueLong;
            } else {
                return -1;
            }

        }
        return 0;
    }

}
