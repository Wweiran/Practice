package cn.edu.scut.wweiran.materialdesign;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WANGWEIRAN on 2017/3/28.
 */

public class PracticeListFragment extends Fragment {

    private List<String> names = new ArrayList<String>() {
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_practice_list,
                container, false);
        setupRecyclerView(recyclerView);
        for (int i = 0; i < 200; i++) {
            names.add(String.valueOf(i));
        }
        return recyclerView;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new PracticeRecyclerViewAdapter(getActivity(), names));
    }


    public static class PracticeRecyclerViewAdapter extends RecyclerView
            .Adapter<PracticeRecyclerViewAdapter.PracticeViewHolder> {

        TypedValue mTypedValue = new TypedValue();
        int mBackground;
        List<String> mValues;

        public static class PracticeViewHolder extends RecyclerView.ViewHolder {

            private View mView;
            TextView mTextView;

            public PracticeViewHolder(View itemView) {
                super(itemView);
                mView = itemView;
//                mImageView = (ImageView) itemView.findViewById(R.id.image_view_circle);
                mTextView = (TextView) itemView.findViewById(R.id.list_text_view);
            }

            @Override
            public String toString() {
                return "PracticeViewHolder{" +
                        "mView=" + mView +
                        ", mTextView=" + mTextView +
                        '}';
            }
        }

        public PracticeRecyclerViewAdapter() {
        }

        public PracticeRecyclerViewAdapter(Context context, List<String> items) {
            context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
            mBackground = mTypedValue.resourceId;
            mValues = items;
        }

        @Override
        public PracticeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            view.setBackgroundResource(mBackground);
            return new PracticeViewHolder(view);
        }

        @Override
        public void onBindViewHolder(PracticeViewHolder holder, int position) {
            holder.mTextView.setText(mValues.get(position));
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "parctice", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }


    }
}
