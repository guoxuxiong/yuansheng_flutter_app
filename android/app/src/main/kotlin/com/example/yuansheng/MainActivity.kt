package com.example.yuansheng

import android.os.Bundle
import io.flutter.app.FlutterActivity
import io.flutter.plugins.GeneratedPluginRegistrant.registerWith

class MainActivity : FlutterActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_edit_h5)
        //获取到自身的Registry
        val registry = flutterView.pluginRegistry;
        //注册插件
        FlutterPluginFtoA.registerWith(registry.registrarFor(FlutterPluginFtoA.A_TO_F_CHANNEL));
        FlutterPluginAtoF.registerWith(registry.registrarFor(FlutterPluginAtoF.F_TO_A_CHANNEL));
    }
}
