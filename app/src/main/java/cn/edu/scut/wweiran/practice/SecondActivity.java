package cn.edu.scut.wweiran.practice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private MyTextView mMyTextView;
    private View mView;
    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mView = findViewById(R.id.my_view);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SecondActivity.this, "test", Toast.LENGTH_SHORT).show();
            }
        });

        mButton = (Button) findViewById(R.id.move_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((View) mView.getParent()).scrollBy(-100, 0);
                mButton.scrollBy(100, 0);
            }
        });

    }


}
