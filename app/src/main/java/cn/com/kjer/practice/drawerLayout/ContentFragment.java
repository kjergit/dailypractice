package cn.com.kjer.practice.drawerLayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.com.kjer.practice.R;

/**
 * Created by simon on 2016/11/8.
 */

public class ContentFragment extends Fragment {
    private TextView tv_content;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content_layout, container, false);
        tv_content = (TextView) view.findViewById(R.id.tv_content);
        String text = getArguments().getString("content");
        tv_content.setText(text);
        return view;
    }
}
