package com.project.mxd.mxd_community;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by maohs on 2018/2/27.
 */

public class MineFragment extends Fragment {
    private boolean shouldLogin;
    private TextView accountDesc;
    private TextView accountContent;
    private TextView accountSignature;
    private TextView loginBtn;
    private RelativeLayout orderItem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_content,container,false);
        accountDesc = (TextView)view.findViewById(R.id.accountDesc);
        accountContent = (TextView)view.findViewById(R.id.accountContent);
        accountSignature = (TextView)view.findViewById(R.id.accountSignature);
        loginBtn = (TextView)view.findViewById(R.id.loginBtn);
        orderItem = (RelativeLayout) view.findViewById(R.id.orderItem);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                startActivity(intent);
            }
        });
        orderItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shouldLogin) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), OrderManagerActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                    startActivity(intent);
                }else  {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                    startActivity(intent);
                }
            }
        });
        initData();
        return view;
    }
    private void initData() {
        SharedPreferences preferences = getActivity().getSharedPreferences("userPreference", Context.MODE_PRIVATE);
        shouldLogin = preferences.getBoolean("shouldLogin",false);
        if (!shouldLogin) {
            accountDesc.setVisibility(View.GONE);
            accountContent.setVisibility(View.GONE);
            accountSignature.setVisibility(View.GONE);
            loginBtn.setVisibility(View.VISIBLE);
            return;
        }
        CommunityOpenHelper communityOpenHelper = new CommunityOpenHelper(getActivity(),"community.db",null,1);
        SQLiteDatabase db = communityOpenHelper.getReadableDatabase();
        Cursor cursor = db.query("userInfo",null,null,null,null,null,null);
        try {
            if (cursor != null && cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    accountContent.setText(cursor.getString(cursor.getColumnIndex("phoneNum")));
                }
            }else  {

            }
        }catch (Exception e) {

        }finally {
            cursor.close();
        }
        db.close();
    }
}
