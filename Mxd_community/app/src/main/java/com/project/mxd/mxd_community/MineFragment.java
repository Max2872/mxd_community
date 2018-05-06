package com.project.mxd.mxd_community;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by maohs on 2018/2/27.
 */

public class MineFragment extends Fragment {
    private CommunityOpenHelper communityOpenHelper;
    private View view;
    private boolean shouldLogin;
    private TextView accountDesc;
    private TextView accountContent;
    private TextView accountSignature;
    private TextView loginBtn;
    private RelativeLayout orderItem;
    private RelativeLayout walletItem;
    private RelativeLayout addressItem;
    private RelativeLayout remindItem;

    private TextView walletContent;
    private TextView logoutBtn;

    private String walletString = "0";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mine_content,container,false);
        communityOpenHelper = new CommunityOpenHelper(getActivity(),"community.db",null,1);

        accountDesc = (TextView)view.findViewById(R.id.accountDesc);
        accountContent = (TextView)view.findViewById(R.id.accountContent);
        accountSignature = (TextView)view.findViewById(R.id.accountSignature);
        loginBtn = (TextView)view.findViewById(R.id.loginBtn);
        orderItem = (RelativeLayout) view.findViewById(R.id.orderItem);
        walletItem = (RelativeLayout) view.findViewById(R.id.walletItem);
        addressItem = (RelativeLayout) view.findViewById(R.id.addressItem);
        remindItem = (RelativeLayout) view.findViewById(R.id.remindItem);
        walletContent = (TextView) view.findViewById(R.id.walletContent);
        logoutBtn = (TextView)view.findViewById(R.id.logoutBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLoginActivity();
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
                    toLoginActivity();
                }
            }
        });
        walletItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shouldLogin) {
                    Integer tempNum = Integer.parseInt(walletString);
                    tempNum += 500;
                    customToast("成功充值500元，零钱余额" + tempNum + "元",2);
                    walletString = tempNum + "";
                    walletContent.setText(tempNum.toString() + "元");
                }else  {
                    walletString = "0";
                    walletContent.setText(walletString + "元");
                    toLoginActivity();
                }
            }
        });
        addressItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shouldLogin) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), AddressManagerActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                    startActivity(intent);
                }else  {
                    toLoginActivity();
                }
            }
        });
        remindItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shouldLogin) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), RemindActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
                    startActivity(intent);
                }else  {
                    toLoginActivity();
                }
            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearPreference();
                initData();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void onPause() {
        if (shouldLogin) {
            SQLiteDatabase db = communityOpenHelper.getWritableDatabase();
            ContentValues value = new ContentValues();
            value.put("wallet",walletString);
            db.update("userInfo",value,null,null);
            db.close();
        }
        super.onPause();
    }

    private void initData() {
        SharedPreferences preferences = getActivity().getSharedPreferences("userPreference", Context.MODE_PRIVATE);
        shouldLogin = preferences.getBoolean("shouldLogin",false);
        if (!shouldLogin) {
            accountDesc.setVisibility(View.GONE);
            accountContent.setVisibility(View.GONE);
            accountSignature.setVisibility(View.GONE);
            logoutBtn.setVisibility(View.GONE);
            loginBtn.setVisibility(View.VISIBLE);
            return;
        }else {
            accountDesc.setVisibility(View.VISIBLE);
            accountContent.setVisibility(View.VISIBLE);
            accountSignature.setVisibility(View.VISIBLE);
            logoutBtn.setVisibility(View.VISIBLE);
            loginBtn.setVisibility(View.GONE);
        }
        SQLiteDatabase db = communityOpenHelper.getReadableDatabase();
        Cursor cursor = db.query("userInfo",null,null,null,null,null,null);
        try {
            if (cursor != null && cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    accountContent.setText(cursor.getString(cursor.getColumnIndex("phoneNum")));
                    walletString = cursor.getString(cursor.getColumnIndex("wallet"));
                    if (walletString == null) {
                        walletString = "0";
                    }
                    walletContent.setText(walletString + "元");
                }
            }else  {

            }
        }catch (Exception e) {

        }finally {
            cursor.close();
        }
        db.close();
    }
    private void toLoginActivity() {
        Intent intent = new Intent();
        intent.setClass(getActivity(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_USER_ACTION);
        intent.putExtra("hasLogin",true);
        startActivity(intent);
    }
    private void clearPreference() {
        SQLiteDatabase db = communityOpenHelper.getWritableDatabase();
        db.delete("userInfo",null,null);
        db.close();
        SharedPreferences preference = getActivity().getSharedPreferences("userPreference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.clear();
        editor.commit();
    }
    private void customToast(String string,int showTime) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view1 = inflater.inflate(R.layout.mine_toast_custom,(ViewGroup) view.findViewById(R.id.mineToat));
        TextView toastMsg = (TextView)view1.findViewById(R.id.toastMsg);
        toastMsg.setText(string);
        Toast toast = new Toast(getActivity());
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,300);
        toast.setDuration(showTime);
        toast.setView(view1);
        toast.show();
    }
}
