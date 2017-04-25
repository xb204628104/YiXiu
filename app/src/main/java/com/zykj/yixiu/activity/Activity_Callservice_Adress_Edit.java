package com.zykj.yixiu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.zykj.yixiu.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zykj on 2017/4/25.
 */

public class Activity_Callservice_Adress_Edit extends Activity {
    @Bind(R.id.textView)
    TextView textView;
    @Bind(R.id.et_edit_name)
    EditText etEditName;
    @Bind(R.id.ll_edit_adress)
    LinearLayout llEditAdress;
    @Bind(R.id.sw_edie_moren)
    Switch swEdieMoren;
    @Bind(R.id.et_edit_adress)
    EditText etEditAdress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_address_edit);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent!=null){
            String adress = intent.getStringExtra("adress");
            etEditAdress.setText(adress);
        }
    }

    @OnClick({R.id.ll_edit_adress,R.id.et_edit_adress, R.id.sw_edie_moren})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_edit_adress:
                Intent intent=new Intent(Activity_Callservice_Adress_Edit.this,Activity_Baidu_Map.class);
                startActivity(intent);
                break;
            case R.id.sw_edie_moren:
                break;
        }
    }
}
