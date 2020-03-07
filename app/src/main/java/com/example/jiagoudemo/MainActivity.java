package com.example.jiagoudemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private LinearLayout mTabWeixin;
    private LinearLayout mTabFrd;
    private LinearLayout mTabAddress;
    private LinearLayout mTabSetting;
    //�ײ�4�������ؼ��е�ͼƬ��ť
    private ImageButton mImgWeixin;
    private ImageButton mImgFrd;
    private ImageButton mImgAddress;
    private ImageButton mImgSetting;
    //��ʼ��4��Fragment
    private Fragment tab01;
    private Fragment tab02;
    private Fragment tab03;
    private Fragment tab04;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();//��ʼ�����е�view
        initEvents();
        setSelect(0);//Ĭ����ʾ΢���������
    }

    private void initEvents() {
        mTabWeixin.setOnClickListener(this);
        mTabFrd.setOnClickListener(this);
        mTabAddress.setOnClickListener(this);
        mTabSetting.setOnClickListener(this);

    }

    private void initView() {
        mTabWeixin = (LinearLayout)findViewById(R.id.id_tab_weixin);
        mTabFrd = (LinearLayout)findViewById(R.id.id_tab_frd);
        mTabAddress = (LinearLayout)findViewById(R.id.id_tab_address);
        mTabSetting = (LinearLayout)findViewById(R.id.id_tab_setting);
        mImgWeixin = (ImageButton)findViewById(R.id.id_tab_weixin_img);
        mImgFrd = (ImageButton)findViewById(R.id.id_tab_frd_img);
        mImgAddress = (ImageButton)findViewById(R.id.id_tab_address_img);
        mImgSetting = (ImageButton)findViewById(R.id.id_tab_setting_img);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        resetImg();
        switch (v.getId()) {
            case R.id.id_tab_weixin://�����΢�Ű�ťʱ���л�ͼƬΪ��ɫ���л�fragmentΪ΢���������
                setSelect(0);
                break;
            case R.id.id_tab_frd:
                setSelect(1);
                break;
            case R.id.id_tab_address:
                setSelect(2);
                break;
            case R.id.id_tab_setting:
                setSelect(3);
                break;

            default:
                break;
        }
    }
    /*
     * ��ͼƬ����Ϊ��ɫ�ģ��л���ʾ���ݵ�fragment
     * */
    private void setSelect(int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();//����һ������
        hideFragment(transaction);//�����Ȱ����е�Fragment�����ˣ�Ȼ�������ٿ�ʼ�������Ҫ��ʾ��Fragment
        switch (i) {
            case 0:
                if (tab01 == null) {
                    tab01 = new WeixinFragment();
                    /*
                     * ��Fragment��ӵ���У�public abstract FragmentTransaction add (int containerViewId, Fragment fragment)
                     *containerViewId��ΪOptional identifier of the container this fragment is to be placed in. If 0, it will not be placed in a container.
                     * */
                    transaction.add(R.id.id_content, tab01);//��΢����������Fragment��ӵ�Activity��
                }else {
                    transaction.show(tab01);
                }
                mImgWeixin.setImageResource(R.drawable.tab_weixin_pressed);
                break;
            case 1:
                if (tab02 == null) {
                    tab02 = new FrdFragment();
                    transaction.add(R.id.id_content, tab02);
                }else {
                    transaction.show(tab02);
                }
                mImgFrd.setImageResource(R.drawable.tab_find_frd_pressed);
                break;
            case 2:
                if (tab03 == null) {
                    tab03 = new AddressFragment();
                    transaction.add(R.id.id_content, tab03);
                }else {
                    transaction.show(tab03);
                }
                mImgAddress.setImageResource(R.drawable.tab_address_pressed);
                break;
            case 3:
                if (tab04 == null) {
                    tab04 = new SettingFragment();
                    transaction.add(R.id.id_content, tab04);
                }else {
                    transaction.show(tab04);
                }
                mImgSetting.setImageResource(R.drawable.tab_settings_pressed);
                break;

            default:
                break;
        }
        transaction.commit();//�ύ����
    }

    /*
     * �������е�Fragment
     * */
    private void hideFragment(FragmentTransaction transaction) {
        if (tab01 != null) {
            transaction.hide(tab01);
        }
        if (tab02 != null) {
            transaction.hide(tab02);
        }
        if (tab03 != null) {
            transaction.hide(tab03);
        }
        if (tab04 != null) {
            transaction.hide(tab04);
        }

    }

    private void resetImg() {
        mImgWeixin.setImageResource(R.drawable.tab_weixin_normal);
        mImgFrd.setImageResource(R.drawable.tab_find_frd_normal);
        mImgAddress.setImageResource(R.drawable.tab_address_normal);
        mImgSetting.setImageResource(R.drawable.tab_settings_normal);
    }
}
