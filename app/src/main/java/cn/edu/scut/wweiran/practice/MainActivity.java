package cn.edu.scut.wweiran.practice;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static cn.edu.scut.wweiran.practice.R.id.editText3;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout mTextInputLayout;
    private EditText mEditText;
    private Button mButton;
    private Button mStartActivityButton;

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextInputLayout = (TextInputLayout) findViewById(R.id.input);
        mEditText = (EditText) findViewById(editText3);
        mButton = (Button) findViewById(R.id.button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if (TextUtils.isEmpty(mEditText.getText().toString())) {
                    Toast.makeText(MainActivity.this, "电话号码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mEditText.getText()
                            .toString()));
                    startActivity(intent);
                }*/
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
                builder.setSmallIcon(R.mipmap.ic_launcher)
                        .setTicker("test")
                        .setAutoCancel(true);
                Notification notification = builder.build();
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                manager.notify(0, notification);

                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);

                Notification notification1 = new Notification();

            }
        });

        Calendar calendar = Calendar.getInstance();
        GregorianCalendar gregorianCalendar = new GregorianCalendar(2010, 2, 1);
        mTextView = (TextView) findViewById(R.id.calendar);
        mTextView.setText(String.valueOf(gregorianCalendar.isLeapYear(1300)));

        mTextInputLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() < 5) {
                    mTextInputLayout.setError("length < 5");
                    mTextInputLayout.setErrorEnabled(true);
                } else {
                    mTextInputLayout.setErrorEnabled(false);
                }
            }
        });

        mStartActivityButton = (Button) findViewById(R.id.start_activity);
        mStartActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
