package cn.edu.scut.wweiran.fragmentpractice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class KeyEventActivity extends AppCompatActivity {

    private EditText mEditText;
    private CheckBox mCheckBox;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_event);

        mEditText = (EditText) findViewById(R.id.editText);
        mCheckBox = (CheckBox) findViewById(R.id.checkBox);
        mTextView = (TextView) findViewById(R.id.textView);

        mEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                int metaState = event.getMetaState();
                int unicodeChar = event.getUnicodeChar();

                String msg = "";
                msg += "按键动作：" + String.valueOf(event.getAction()) + "\n";
                msg += "按键代码：" + String.valueOf(event.getKeyCode()) + "\n";
                msg += "按键字符：" + (char) unicodeChar + "\n";
                msg += "UNICODE：" + String.valueOf(unicodeChar) + "\n";
                msg += "重复次数：" + String.valueOf(event.getRepeatCount()) + "\n";
                msg += "功能键状态：" + String.valueOf(metaState) + "\n";
                msg += "硬件编码：" + String.valueOf(event.getScanCode()) + "\n";
                msg += "按键标志：" + String.valueOf(event.getFlags()) + "\n";

                mTextView.setText(msg);
                if (mCheckBox.isChecked()) {
                    return true;
                } else {
                    return false;
                }


            }
        });
    }
}
