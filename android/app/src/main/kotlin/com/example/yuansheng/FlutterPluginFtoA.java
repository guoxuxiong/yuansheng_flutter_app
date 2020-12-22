package com.example.yuansheng;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

/**
 * @author: guoxuxiong
 * 时间:2020/12/21
 * 邮箱:553605867@qq.com
 * 描述:从Flutter传递到本地的插件类
 */
public class FlutterPluginFtoA implements MethodChannel.MethodCallHandler {
    //这里要注意和Flutter使用的相同
    public static final String A_TO_F_CHANNEL = "com.gxx.receive/plugin";
    private Activity handlerActivity;

    public FlutterPluginFtoA(Activity activity) {
        this.handlerActivity = activity;
    }
    public static void registerWith(PluginRegistry.Registrar registrar) {
        //主要的方法MethodChannel
        MethodChannel methodChannel = new MethodChannel(registrar.messenger(), A_TO_F_CHANNEL);
        FlutterPluginFtoA instance = new FlutterPluginFtoA(registrar.activity());
        methodChannel.setMethodCallHandler(instance);
    }
    @Override
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        switch (methodCall.method){
            case "showToast":
                //接收Flutter 传递过来的参数
                Object arguments = methodCall.arguments;
                Toast.makeText(handlerActivity, "收到Flutter传过来的" + arguments, Toast.LENGTH_SHORT).show();
                break;
            case "startToEditActivity":
                //跳转到原生界面
                Intent editActivityIntent = new Intent(handlerActivity, TestActivity.class);
                handlerActivity.startActivity(editActivityIntent);

                result.success("startOk");
                break;
            case "mapData":
                //Flutter 传递过来的数据
                //解析参数
                String text = methodCall.argument("flutter");
                Toast.makeText(handlerActivity, "接收: " + text, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
