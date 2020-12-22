package com.example.yuansheng;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author: guoxuxiong
 * 时间:2020/12/21
 * 邮箱:553605867@qq.com
 * 描述:
 */
public class TestActivity extends Activity implements View.OnClickListener {
    private int currentCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_h5);
        Button btn=findViewById(R.id.tvIntentTxt);
        btn.setText("传递给flutter");
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
//
        FlutterPluginAtoF.getInstance().sendEventData(""+ currentCount++); //通过这个发送消息到Flutter
        Toast.makeText(this, currentCount+"", Toast.LENGTH_SHORT).show();
    }
}
