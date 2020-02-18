package com.phonefangdajing.word;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tiny.TinySdk;
import com.android.tiny.bean.User;
import com.android.tiny.bean.base.TaskType;
import com.android.tiny.tinyinterface.FunParamsNoResult;
import com.android.tiny.tinyinterface.OkHttpException;
import com.android.tiny.tinyinterface.OnConfigListener;
import com.android.tiny.tinyinterface.OnUpdateListener;

public class SdkTestAct  extends Activity {

    public static final String TAG="SdkTestAct";
    private TextView tvShow;//回调信息展示

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);

        tvShow= findViewById(R.id.tvshow);

        //手机或微信登陆
        findViewById(R.id.login1bt).setOnClickListener(v -> {
            TinySdk.getInstance().login(this);
        });
        //游客登陆需要权限申请
        findViewById(R.id.login2bt).setOnClickListener(v -> {
            TinySdk.getInstance().visitorLogin(this);
        });
        //游客登陆绑定手机号
        findViewById(R.id.conphonebt).setOnClickListener(v -> {
            TinySdk.getInstance().visitorBindMobile(this);
        });

        //绑定手机号后（游客或手机号登陆）回调
        TinySdk.getInstance().setLoginSuccessListener(new FunParamsNoResult<User.UserEntity>(TaskType.LOGIN) {
            @Override
            public void function(User.UserEntity userEntity) {
                tvShow.setText(userEntity.toString());
                Log.d(TAG, "function: "+userEntity.toString());
            }
        });

        //获取用户信息
        findViewById(R.id.userinfobt).setOnClickListener(v -> {
            User.UserEntity entity= TinySdk.getInstance().getUser();
            tvShow.setText("获取用户信息 "+entity.toString());
        });

        //获取任务信息
        findViewById(R.id.task_bt).setOnClickListener(v -> {
            //type：1、特殊任务、2、新手任务、3、每日任务
            TinySdk.getInstance().getTaskConfigInfo("3", new OnUpdateListener() {
                @Override
                public void onComplete(String config) {
                    tvShow.setText("获取任务信息 "+config);
                }

                @Override
                public void onFail(String error) {
                    tvShow.setText(error);
                }
            });
        });

        //获取任务状态信息
        findViewById(R.id.task_statusbt).setOnClickListener(v -> {
            TinySdk.getInstance().getTaskStatus(new OnConfigListener() {
                @Override
                public void onSuccess(String config) {
                    Log.d(TAG, "onSuccess: "+config);
                    tvShow.setText("获取任务状态信息 "+config);
                }

                @Override
                public void onError(OkHttpException e) {
                    Log.e(TAG, "onError: "+e.toString());
                }
            });
        });
        //获取任务金币列表信息
        findViewById(R.id.goldbt).setOnClickListener(v -> {
            TinySdk.getInstance().getCoinListConfig(new OnConfigListener() {
                @Override
                public void onSuccess(String config) {
                    Log.d(TAG,"data: " + config);
                    tvShow.setText("获取任务金币列表信息 "+config);
                }

                @Override
                public void onError(OkHttpException e) {
                    Log.e(TAG, "onError: "+e.toString());
                }
            });
        });

        //签到
        findViewById(R.id.qiandaobt).setOnClickListener(v -> {
              startActivity(new Intent(this,TaskActivity.class));
        });
        //增加金币
        findViewById(R.id.addmoneybt).setOnClickListener(v -> {
            TinySdk.getInstance().updateCoinCount("710002", "100");//任务标识
        });
        //查询金币
        findViewById(R.id.moneynumbt).setOnClickListener(v -> {
            tvShow.setText("参考获取用户信息");
        });
        //提现 跳转提现界面
        findViewById(R.id.tixianbt).setOnClickListener(v -> {
            TinySdk.getInstance().cash(this);
        });

        //提现相关回调
        // 为提现页面设置播放激励视频广告的逻辑，app应在播放完成处理发放金币，次数由客户端控制
        TinySdk.getInstance().setOnClickVideoLoadAd(new FunParamsNoResult<Context>(TaskType.CLICK_VIDEO_PLAY) {
        @Override
            public void function(Context context) {
            Toast.makeText(context, "video pic is clicked", Toast.LENGTH_SHORT).show();
        }
        });

        // 用户提现成功，由app设置可跳转到可赚取金币的页面的点击事件
        TinySdk.getInstance().setOnGainMoreCoins(new FunParamsNoResult<Context>(TaskType.GAIN_MORE_COINS) {
        @Override
        public void function(Context context) {

            Toast.makeText(context, "赚取更多金币", Toast.LENGTH_SHORT).show();
        }
        });
    }


}
