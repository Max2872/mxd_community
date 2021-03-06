package com.project.mxd.mxd_community;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by mxd on 2018/2/27.
 */

public class GiftFragment extends Fragment {
    private TextView recommandTextView;
    private TextView customTextView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gift_content,container,false);
        recommandTextView = (TextView) view.findViewById(R.id.recommand_box);
        customTextView = (TextView) view.findViewById(R.id.custom_box);
        recommandTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePreference(false);
                Intent intent = new Intent();
                intent.setClass(getActivity(), GiftBoxSettingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                startActivity(intent);
            }
        });
        customTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePreference(true);
                Intent intent = new Intent();
                intent.setClass(getActivity(), GiftBoxSettingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                startActivity(intent);
            }
        });
        return view;
    }
    private void savePreference(boolean shouldTurnCustom) {
        SharedPreferences preference = getActivity().getSharedPreferences("userPreference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean("shouldTurnCustom",shouldTurnCustom);
        editor.commit();
    }
}

