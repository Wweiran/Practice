package cn.edu.scut.wweiran.fragmentpractice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by WANGWEIRAN on 2017/3/29.
 */

public class MainFragment extends Fragment {

    private static final String HOME_MAIN = "home_text";
    private String mContent;
    private TextView mTextView;


    @NonNull
    public static MainFragment newInstance(String s) {
        Bundle bundle = new Bundle();
        bundle.putString(HOME_MAIN, s);

        MainFragment mainFragment = new MainFragment();
        mainFragment.setArguments(bundle);
        return mainFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mTextView = (TextView) view.findViewById(R.id.fragment_text_view);
        if (getArguments() != null) {
            mTextView.setText(getArguments().getString(HOME_MAIN));
        }
        return view;
    }
}
