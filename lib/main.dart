import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);
  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          // Here we take the value from the MyHomePage object that was created by
          // the App.build method, and use it to set our appbar title.
          title: Text(widget.title),
        ),
        body: TestWidget());
  }
}

class TestWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return TestState();
  }
}

class TestState extends State<TestWidget> {
  //获取插件与原生Native的交互通道
  static const sendToNative =
      const MethodChannel('com.gxx.receive/plugin');

  //具体要做的功能
  Future<Null> _statrtToNativeActivity() async {
    String result = await sendToNative.invokeMethod('startToEditActivity');
    print(result);
  }

  // 具体要做的功能
  Future<Null> _sendDataToNative() async {
    Map<String, String> map = {"flutter": "我是flutter 传递过来的"};
    String result = await sendToNative.invokeMethod('mapData', map);
    print(result);
  }

  //接收来自本地的消息
  static const receiveFromNative =
      const EventChannel('com.gxx.send/plugin');
  //监听接收消息
  StreamSubscription _streamSubscription;
  var _currentCount;

  @override
  void initState() {
    super.initState();
    //开启监听
    if (_streamSubscription == null) {
      _streamSubscription =
          receiveFromNative.receiveBroadcastStream().listen((event) {
        setState(() {
          _currentCount = event;
          print("ChannelPage: $event");
        });
      }, onError: (Object error) {
        setState(() {
          _currentCount = "计时器异常";
          print(error);
        });
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return  Container(
      child: Column(
        children: <Widget>[
          RaisedButton(
            child: Text('跳转到原生界面'),
            onPressed: (){
              _statrtToNativeActivity();
            },
          ),
          RaisedButton(
            child: Text('让原生接收到数据'),
            onPressed: (){
              _sendDataToNative();
            },
          ),
          RaisedButton(child: Text("原生传递过来的数据:$_currentCount"),
          onPressed: (){

          },)
        ],
      ),
    );
  }

  @override
  void dispose() {
    // TODO: implement dispose
    super.dispose();
    //取消监听
    if (_streamSubscription != null) {
      _streamSubscription.cancel();
    }
  }
}
