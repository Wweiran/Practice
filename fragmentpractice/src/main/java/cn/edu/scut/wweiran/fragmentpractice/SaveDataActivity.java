package cn.edu.scut.wweiran.fragmentpractice;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveDataActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "SaveDataActivity";

    private static final String ACCOUNT = "cn.edu.scut.wweiran.fragmentpractice.account";
    private static final String PASSWORD = "cn.edu.scut.wweiran.fragmentpractice.password";
    private static final String ISCHECK = "cn.edu.scut.wweiran.fragmentpractice.check";


    //数据的保存，可以通过写一个记住密码，来实现数据的读写
    //可以有SharedPreference、文件、数据库等，这里分别对这三种进行练习

    private Spinner mSpinner;
    private ArrayAdapter<CharSequence> mArrayAdapter;
    private TextView mTextView;
    private CheckBox mCheckBox;
    private Button mButton, mRestoreButton;

    private EditText mAccountEditText, mPasswordEditText;

    //数据存储相关
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;

    /**
     * 用来记录下拉选项是哪个
     */
    private int way2Remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);

        mSharedPreferences = getSharedPreferences("account", MODE_PRIVATE);
        editor = mSharedPreferences.edit();

        //下拉选项框的设置，设置为最基本样式
        mSpinner = (Spinner) findViewById(R.id.spinner_the_way_remember_password);
        mArrayAdapter = ArrayAdapter.createFromResource(this, R.array.save_date, android.R.layout
                .simple_spinner_item);
        mArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mArrayAdapter);
        mSpinner.setOnItemSelectedListener(this);


        //EditText初始化
        mAccountEditText = (EditText) findViewById(R.id.account);
        mPasswordEditText = (EditText) findViewById(R.id.password);

        mTextView = (TextView) findViewById(R.id.tv_login_way);
        mCheckBox = (CheckBox) findViewById(R.id.remember_password);
        mButton = (Button) findViewById(R.id.btn_login);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String accountText = mAccountEditText.getText().toString();
                String passwordText = mPasswordEditText.getText().toString();
                if (mCheckBox.isChecked()) {

                    switch (way2Remember) {
                        case 0:
                            if (!TextUtils.isEmpty(accountText) && !TextUtils.isEmpty(passwordText)) {
                                if (mCheckBox.isChecked()) {
                                    editor.putBoolean(ISCHECK, true);
                                    editor.putString(ACCOUNT, accountText);
                                    editor.putString(PASSWORD, passwordText);
                                    editor.commit();
                                }
                            }
                            break;
                        case 1:
                            if (!TextUtils.isEmpty(accountText) && !TextUtils.isEmpty(passwordText)) {
                                if (mCheckBox.isChecked()) {
                                    try {
                                        //将账号与密码写入到文件中
                                        FileOutputStream fileOutputStream = openFileOutput("account",
                                                MODE_PRIVATE);
                                        fileOutputStream.write((accountText+"\n").getBytes());
                                        fileOutputStream.write(passwordText.getBytes());
                                        fileOutputStream.flush();
                                        fileOutputStream.close();
                                    } catch (Exception e) {
                                        Log.e(TAG, "onClick: e", e);
                                    }


                                }
                            }
                            break;
                        case 2:
                            Toast.makeText(SaveDataActivity.this, "2", Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
                            if (!TextUtils.isEmpty(accountText) && !TextUtils.isEmpty(passwordText)) {
                                if (mCheckBox.isChecked()) {
                                    try {
                                        User user = new User(accountText, passwordText);
                                        //将账号与密码序列化之后，写入到文件中
                                        //要实现序列化，需要实现Serializable
                                        FileOutputStream fileOutputStream = openFileOutput("account_serializable",
                                                MODE_PRIVATE);
                                        ObjectOutputStream objectOutputStream = new ObjectOutputStream
                                                (fileOutputStream);
                                        objectOutputStream.writeObject(user);
                                        fileOutputStream.flush();
                                        fileOutputStream.close();
                                    } catch (Exception e) {
                                        Log.e(TAG, "onClick: e", e);
                                    }


                                }
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        });


        mRestoreButton = (Button) findViewById(R.id.btn_restore);
        mRestoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (way2Remember) {
                    case 0:
                        if (mSharedPreferences.getBoolean(ISCHECK, false)) {
                            mCheckBox.setChecked(mSharedPreferences.getBoolean(ISCHECK, false));
                            mAccountEditText.setText(mSharedPreferences.getString(ACCOUNT, ""));
                            mPasswordEditText.setText(mSharedPreferences.getString(PASSWORD, ""));
                        }
                        break;
                    case 1:
                        try {
                            FileInputStream fileInputStream = openFileInput("account");
                            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                            BufferedReader reader = new BufferedReader(inputStreamReader);
                            mCheckBox.setChecked(true);
                            mAccountEditText.setText(reader.readLine());
                            mPasswordEditText.setText(reader.readLine());
                            reader.close();
                            inputStreamReader.close();
                            fileInputStream.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        break;
                    case 3:
                        try {
                            FileInputStream fileInputStream = openFileInput("account_serializable");
                            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                            User user = (User) objectInputStream.readObject();
                            mCheckBox.setChecked(true);
                            mAccountEditText.setText(user.getName());
                            mPasswordEditText.setText(user.getPassword());
                            objectInputStream.close();
                            fileInputStream.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }
            }
        });

    }

    @Override
    public void onItemSelected(@NonNull AdapterView<?> parent, View view, int position, long id) {
//        mTextView.setText(parent.getItemAtPosition(position).toString());
        way2Remember = position;
        mTextView.setText("以" + parent.getSelectedItem().toString() + "方式登录");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "未选择", Toast.LENGTH_SHORT).show();
    }
}
