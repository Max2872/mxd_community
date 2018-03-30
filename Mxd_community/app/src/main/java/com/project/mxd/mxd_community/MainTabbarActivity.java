package com.project.mxd.mxd_community;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;

/**
 * Created by maohs on 2018/2/26.
 */

public class MainTabbarActivity extends AppCompatActivity implements View.OnClickListener {
    //UI Object
    private TextView textTopBar;
    private TextView giftTabBar;
    private TextView cardTabBar;
    private TextView cartTabBar;
    private TextView mineTabBar;

    //Fragment Object
    private GiftFragment giftFragment;
    private CardFragment cardFragment;
    private CartFragment cartFragment;
    private MineFragment mineFragment;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_tabbar);
        StatusBarCompat.setStatusBarColor(this,getResources().getColor(R.color.commonRed),true);
        fragmentManager = getFragmentManager();
        bindViews();
        giftTabBar.performClick();
        LoginActivity.loginInstance.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent originIntent = getIntent();
        boolean toCart = originIntent.getBooleanExtra("toCart",false);
        if (toCart) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            hideAllFragment(fragmentTransaction);
            setSelected();
            cartTabBar.setSelected(true);
            textTopBar.setText("购物车");
            if (cartFragment == null) {
                cartFragment = new CartFragment();
                fragmentTransaction.add(R.id.content_zone,cartFragment);
            }else  {
                fragmentTransaction.show(cartFragment);
            }
            fragmentTransaction.commit();
            originIntent.putExtra("toCart",false);
        }
    }

    private void bindViews() {
        textTopBar = (TextView) findViewById(R.id.top_bar_text);
        giftTabBar = (TextView) findViewById(R.id.tab_bar_gift);
        cardTabBar = (TextView) findViewById(R.id.tab_bar_card);
        cartTabBar = (TextView) findViewById(R.id.tab_bar_cart);
        mineTabBar = (TextView) findViewById(R.id.tab_bar_mine);

        giftTabBar.setOnClickListener(this);
        cardTabBar.setOnClickListener(this);
        cartTabBar.setOnClickListener(this);
        mineTabBar.setOnClickListener(this);

    }

    private void setSelected() {
        giftTabBar.setSelected(false);
        cardTabBar.setSelected(false);
        cartTabBar.setSelected(false);
        mineTabBar.setSelected(false);
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (giftFragment != null) {
            fragmentTransaction.hide(giftFragment);
        }
        if (cardFragment != null) {
            fragmentTransaction.hide(cardFragment);
        }
        if (cartFragment != null) {
            fragmentTransaction.hide(cartFragment);
        }
        if (mineFragment != null) {
            fragmentTransaction.hide(mineFragment);
        }
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideAllFragment(fragmentTransaction);
        switch (v.getId()) {
            case R.id.tab_bar_gift:
                setSelected();
                giftTabBar.setSelected(true);
                textTopBar.setText("首页");
                if (giftFragment == null) {
                    giftFragment = new GiftFragment();
                    fragmentTransaction.add(R.id.content_zone,giftFragment);
                }else  {
                    fragmentTransaction.show(giftFragment);
                }
                break;
            case R.id.tab_bar_card:
                setSelected();
                cardTabBar.setSelected(true);
                textTopBar.setText("贺卡");
                if (cardFragment == null) {
                    cardFragment = new CardFragment();
                    fragmentTransaction.add(R.id.content_zone,cardFragment);
                }else  {
                    fragmentTransaction.show(cardFragment);
                }
                break;
            case R.id.tab_bar_cart:
                setSelected();
                cartTabBar.setSelected(true);
                textTopBar.setText("购物车");
                if (cartFragment == null) {
                    cartFragment = new CartFragment();
                    fragmentTransaction.add(R.id.content_zone,cartFragment);
                }else  {
                    fragmentTransaction.show(cartFragment);
                }
                break;
            case R.id.tab_bar_mine:
                setSelected();
                mineTabBar.setSelected(true);
                textTopBar.setText("个人中心");
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    fragmentTransaction.add(R.id.content_zone,mineFragment);
                }else  {
                    fragmentTransaction.show(mineFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }

}
