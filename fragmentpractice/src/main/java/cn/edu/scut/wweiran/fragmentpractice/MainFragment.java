package cn.edu.scut.wweiran.fragmentpractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by WANGWEIRAN on 2017/3/29.
 */

public class MainFragment extends Fragment {

    private String mContent;
    private TextView mTextView;


    public MainFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mTextView = (TextView) view.findViewById(R.id.fragment_text_view);
        if (getArguments() != null) {
            mTextView.setText(getArguments().getString("MAIN"));
        }
        return view;
    }
}
