package com.phonefangdajing.word;

import android.app.Application;
import android.util.Log;

import com.android.tiny.TinySdk;
import com.android.tiny.bean.TaskViewConfig;
import com.android.tiny.bean.TinyConfig;

public class App extends Application {

    //此处测试

    @Override
    public void onCreate() {
        super.onCreate();
        initsdk();
        //此处为测试github 废代码
        for (int i = 0; i < 10; i++) {
            Log.d("TEST", ":test"+i);
        }
        //测试测试测试
    }

    private void initsdk() {

        TaskViewConfig taskViewConfig = new TaskViewConfig.Builder()
                .setCompleteDrawable(R.drawable.tinysdk_task_test) //设置任务完成状态样式 必须 （可以参考文件样式 tinysdk_task_complete.xml ）
                .setNotCompleteDrawable(R.drawable.tinysdk_task_comples)//设置任务未完成状态样式  必须
                .setAcquireDrawable(R.drawable.tinysdk_task_acquires)//设置任务领取状态样式 必须
                .setNotificationDrawable(R.drawable.tinysdk_task_noti)//设置通知的按钮的样式  必须
                .setTaskItemTitleColor(R.color.colorPrimary) //必须
                .setTaskItemContentColor(R.color.color_2f2f2f)//必须
                .setTaskItemDividerColor(R.color.color_B3FEEFD2)//必须
                .setTaskItemCoinColor(R.color.color_F5F5F5)//必须
                .setDownTime(false)//时段奖励功能
                .setProgressVisibility(false)//每日任务的进度条
                .setGuideTaskVisibility(true)//新手任务
                .setTipVisibility(true)//任务的提示icon
                .setSignVisibility(true)//签到
                .setFunVisibility(true)//轻松一刻
                .setBannerVisibility(false)//banner
                .setCardAdVisibility(false)//5个活动擦片
                .build();

        //日常任务显示步数后面 （200 / 30000）
        final TinyConfig config = new TinyConfig.Builder()
                .setHost("http://172.31.0.123:1080")//必传 测试地址http://172.31.0.123:1080
                .setAppId("27") //必传 1：输入法，2：全能计步器，3：睡觉,具体获取搜索 appid获取
                .setDomainUrl("http://169.55.74.167:15580")///必传 拉取功能配置的服务器域名地址；测试环境的地址：http://169.55.74.167:15580，供dev开发包调试测试用；正式包必须用产品运营申请的正式地址。
                .setPubId("100165")///必传 pubid
                .setPubKey("hwibHQoiwggVwx2I")///必传 pubey ---------
//                .setPrivacyClass(Test.class)//必传 隐私协议界面  正式加入----
                .setTaskViewConfig(taskViewConfig)//必传 ui配置
                .setTxAppId("1108888734")//必传---------
                .setWxAppId("wxb44f57349b2ebc15")//必传-------
                .setTid("1234")//非必传 渠道tid,需要向运营人员申请，需要分渠道时必传
                .setPubIv("")//非必传 ----------
//                .setChannel("111")//非必传 渠道名称(接入区分分渠道功能需必传)
                .setCashOpen(true)//非必传提现功能开关
                .setSignKey("1") //非必传 签到key
                .setProgressKey("100001")  //非必传 使用进度条则必传
//                .setStepTaskInfo(stepTaskInfo)//非必传，计步器专属
                .setAutoSign(true)//非必传 设置是否自动签到，默认不自动签到
                .setSignRedAnimation(true)//非必传 设置签到动效
                .setSkinName("calendar.skin")  //非必传 皮肤包位置（重要）
                //  .setVerificationTime(-1)    //非必传 登录验证码倒计时时间，方便测试，上线默认设置为-1，测试打包可自定义值
                .setSignStyle(0)//非必传 0金币样式，1宝箱样式,默认0
                .setDataReportProvider((category, action, label, value, extra, eid) -> {
//                        --------------接入上报sdk必须调用以下方法，需要调用上报sdk方法，上报tinysdk打点事件
//                        AnalyticsSdk.sendEvent(category, action, label, value, extra, eid);
                })
                .build();
        TinySdk.getInstance().init(this, config);
    }
}
