package com.cool.todayheadline.bean;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.cool.todayheadline.R;

public class MonitorEditText implements TextWatcher {


    private Button button;
    private EditText[] text;

    public void SetMonitorEditText(final Button button, final EditText... text) {

        this.button = button;
        this.text = text;


        for (int i = 0; i < text.length; i++) {

            if (text[i] != null) {

                text[i].addTextChangedListener(MonitorEditText.this);
            }

        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        // TODO Auto-generated method stub
        for (int i = 0; i < text.length; i++) {

            if (text[i].length() == 0) {
                button.setBackgroundResource(R.drawable.login_btn);
                button.setEnabled(false);
                return;//这句代码值两千万
            } else {

                button.setBackgroundResource(R.drawable.login_btn2);
                button.setEnabled(true);

            }
        }
    }

}
