package com.example.yuansheng;

import io.flutter.plugin.common.EventChannel;
import io.flutter.plugin.common.PluginRegistry;

/**
 * @author: guoxuxiong
 * 时间:2020/12/21
 * 邮箱:553605867@qq.com
 * 描述: android 本地代码传数据到Flutter
 */
public class FlutterPluginAtoF implements EventChannel.StreamHandler {
    public static final String F_TO_A_CHANNEL = "com.gxx.send/plugin";
    private static FlutterPluginAtoF instance;
    private EventChannel.EventSink events;

    public static FlutterPluginAtoF getInstance(){
        return  instance;
    }
    @Override
    public void onListen(Object o, EventChannel.EventSink eventSink) {
        this.events=eventSink;

    }
    public  static  void registerWith(PluginRegistry.Registrar registrar){
        EventChannel  eventChannel=new EventChannel(registrar.messenger(),F_TO_A_CHANNEL);
        instance=new FlutterPluginAtoF();
        eventChannel.setStreamHandler(instance);
    }
    @Override
    public void onCancel(Object o) {

    }
    public  void sendEventData(Object data){
        if(events!=null){
            events.success(data);
        }
    }
}
