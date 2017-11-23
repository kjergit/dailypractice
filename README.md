# PowerEye Mobile SDK1.0使用开发文档

##1产品介绍：
    PV SDK是一款软件开发组件，旨在让开发人员获得PowerVision飞机（PowerEye，以及其余产品，后续陆续开放）的扩展开发工具包（PV SDK支持iOS平台和Android平台）。 
    本阶段PV SDK只开放通用挂载的接口转发控制功能（后续会开放飞机本身的控制、姿态、云台控制、相机设置控制，遥控器、链路等功能）。开发人员可以通过SDK中提供的API函数，通过手机对通用挂载上的飞机进行数据采集、发送、控制等，可以将用户的产品整合到powervision飞机上。
  
   **下图为PowerEye**
![](http://avatar.csdn.net/D/6/7/1_qq_21376985.jpg)
    也可以到我们的[官网](http://www.powervision.me/en/)去了解更多产品

***

##2 sdk——V1.0包含内容 
###1）API中可以访问PowerEye通用挂载的应用API函数（iOS&Android）
###2)	示例代码和教程（用户可以通过demo中的事例代码进行初步了解）
###3)	开发人员指南和API文档；
***
#3 Environment Required（Android）
  ##JDK7+
  ##AndroidStudio 3.0+
  ***
 #4调用步骤
 ###1初始化sdk 
 ` mPowerSDK = PowerSDK.getInstance();`
 ###2连接设备
  `mPowerSDK.startConnectSDK(ConnectIpAndPortFactory.getEggConnectIpAndPortFactory());`
 ###3设置连接监听
 ` mPowerSDK.addConnectListener(simpleConnectListener);`
 ###4对应的连接回调
  `ConnectListener.SimpleConnectListener simpleConnectListener = new ConnectListener.SimpleConnectListener() {
        @Override
        public void onChainConnected() {
            super.onChainConnected();
            Log.e(TAG, "onChainConnected");
        }

        @Override
        public void onDroneConnected() {
            super.onDroneConnected();
            Log.e(TAG, "onDroneConnected");
        }

        @Override
        public void onDeviceDisconnected() {
            super.onDeviceDisconnected();
            Log.e(TAG, "onDeviceDisconnected");
        }

        @Override
        public void onChainDisconnected() {
            super.onChainDisconnected();
            Log.e(TAG, "onChainDisconnected");
        }
    };`
  ##更多api使用，请详见demo中以及sdk文档说明。


