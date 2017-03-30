package cn.edu.scut.wweiran.fragmentpractice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SaveDataActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    //数据的保存，可以通过写一个记住密码，来实现数据的读写
    //可以有SharedPreference、文件、数据库等，这里分别对这三种进行练习

    private Spinner mSpinner;
    private ArrayAdapter<CharSequence> mArrayAdapter;
    private TextView mTextView;
    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_data);

        mSpinner = (Spinner) findViewById(R.id.spinner_the_way_remember_password);
        mArrayAdapter = ArrayAdapter.createFromResource(this, R.array.save_date, android.R.layout
                .simple_spinner_item);
        mArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(mArrayAdapter);
        mSpinner.setOnItemSelectedListener(this);

        mTextView = (TextView) findViewById(R.id.tv_login_way);
        mCheckBox = (CheckBox) findViewById(R.id.remember_password);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        mTextView.setText(parent.getItemAtPosition(position).toString());
        mTextView.setText("以" + parent.getSelectedItem().toString() + "方式登录");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "未选择", Toast.LENGTH_SHORT).show();
    }
}
