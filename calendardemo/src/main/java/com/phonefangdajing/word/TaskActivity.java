package com.phonefangdajing.word;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.tiny.TinySdk;
import com.android.tiny.bean.ActivityInfo;
import com.android.tiny.bean.CardAdData;
import com.android.tiny.bean.SignInfoAction;
import com.android.tiny.bean.TargetTaskStatus;
import com.android.tiny.bean.TaskStatus;
import com.android.tiny.bean.base.BaseTaskEntity;
import com.android.tiny.bean.base.TaskType;
import com.android.tiny.tinyinterface.FunParamsNoResult;
import com.android.tiny.tinyinterface.OkHttpException;
import com.android.tiny.tinyinterface.OnSignGoldCoinListener;
import com.android.tiny.tinyinterface.OnSignRedPackageListener;
import com.android.tiny.tinyinterface.OnTaskListener;
import com.android.tiny.tinyinterface.OnUpdateListener;
import com.tiny.a.b.c.bo;

//每日任务 包含签到等其他任务
public class TaskActivity extends AppCompatActivity {

    public static final String TAG = "TaskActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_layout);


        Fragment fragment = TinySdk.getInstance().createTaskFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl, fragment).commit();

        //签到
        signIn();
        //banner区
//        banner();
    }

    private void signIn() {
        Log.d(TAG, "onCreate: 当前sdk内签到是否显示" + TinySdk.getInstance().isTaskSignViewShow());
        //签到成功回调金币数以便app进行一些活动
        TinySdk.getInstance().setSignGoldCoinListener(new FunParamsNoResult<OnSignGoldCoinListener>(TaskType.GOLD_COIN_SIGN) {
            @Override
            public void function(final OnSignGoldCoinListener listener) {
                new Handler().postDelayed(() -> {
                    //签到成功调用
                    TargetTaskStatus taskStatus = new TargetTaskStatus();
                    taskStatus.setTinyResult(TaskStatus.ACRUIRE_COIN_STATUS);
                    listener.onComplete(taskStatus);
                    Log.d(TAG, "function: 签到成功回调");
                }, 1500);
            }
        });
        //点击红包回馈 2
        TinySdk.getInstance().setSignRedPackageListener(new FunParamsNoResult<OnSignRedPackageListener>(TaskType.RED_PACKAGE_SIGN) {
            //app加载激励视频广告
            @Override
            public void function(final OnSignRedPackageListener listener) {
                Log.d(TAG, "function: 点击红包回馈");
                listener.onComplete();
            }
        });

        //点击签到金币回调  1
        TinySdk.getInstance().setSignGoldCoinListener(new FunParamsNoResult<OnSignGoldCoinListener>(TaskType.GOLD_COIN_SIGN) {
            @Override
            public void function(final OnSignGoldCoinListener listener) {
                new Handler().postDelayed(() -> {
                    //签到成功调用
                    TargetTaskStatus taskStatus = new TargetTaskStatus();
                    taskStatus.setTinyResult(TaskStatus.ACRUIRE_COIN_STATUS);
                    listener.onComplete(taskStatus);
                    Log.d(TAG, "function: 点击签到金币回调");
                }, 1000);
            }
        });

      /*  //签到完成领金币
        TinySdk.getInstance().setSignSuccessListener(new FunParamsNoResult<String>(TaskType.SIGN) {

        @Override
        public void function(String coin) {

        }
        });*/

        //签到弹窗
        TinySdk.getInstance().setOnShowViewListener(new FunParamsNoResult<Activity>(TaskType.SHOW_VIEW) {
            @Override
            public void function(Activity activity) {
                Log.d(TAG, "function: 签到弹窗");
            }
        });

        //显示今日和明日签到金币
        TinySdk.getInstance().setOnSignInfoAction(new FunParamsNoResult<SignInfoAction>(TaskType.SIGN_INFO_ACTION) {
            @Override
            public void function(SignInfoAction action) {
                //status：0今日未签到，1今日签到了

                Log.d(TAG, "显示今日和明日签到金币 today is " + action.getTodayCoins() + ",tomorrow is " + action.getTomorrowCoins() + "status is " + action.getStatus());

            }
        });

        //点击任务列表回调
        TinySdk.getInstance().setOnTaskListener(new FunParamsNoResult<OnTaskListener>(TaskType.EXECUTE_TASK) {

        @Override
        public void function(OnTaskListener listener) {  //listener  回传给sdk的接口
            BaseTaskEntity data = listener.getData();  //里面有任务id，url、coin等一些字段
            if (data == null) {
                return;
            }

            int taskId = data.taskId;

            String url = data.url;

            boolean login = listener.isLogin();

            Log.d(TAG, "function: uri "+url+" userlogin "+login+" taskId "+taskId);
            Log.d(TAG, "function: more info "+data.taskCoin);
            //=================================================================

            //更改任务状态，将状态改为去领取状态  任务做完后再去设置
            TinySdk.getInstance().updateTaskStatus(String.valueOf(data.taskKey), new OnUpdateListener() {
                @Override
                public void onComplete(String config) {
                    Log.d(TAG, "onComplete: "+config);
                }

                @Override
                public void onFail(String error) {
                    Log.e(TAG, "onFail: "+error);
                }
            });
        }
        });

    }

}
