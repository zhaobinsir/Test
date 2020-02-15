package demo.shizhi.com.calendardemo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.tiny.TinySdk;
import com.android.tiny.bean.TaskViewConfig;
import com.android.tiny.bean.TinyConfig;
import com.android.tiny.bean.User;
import com.android.tiny.bean.base.TaskType;
import com.android.tiny.tinyinterface.DataReportProvider;
import com.android.tiny.tinyinterface.FunParamsNoResult;
import com.android.tiny.tinyinterface.OkHttpException;
import com.android.tiny.tinyinterface.OnConfigListener;

public class SdkTestAct  extends Activity {

    public static final String TAG="SdkTestAct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);

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
                Log.d(TAG, "function: "+userEntity.toString());
            }
        });

        //获取任务列表
        findViewById(R.id.taskbt).setOnClickListener(v -> {
            TinySdk.getInstance().getTaskStatus(new OnConfigListener() {
                @Override
                public void onSuccess(String config) {
                    Log.d(TAG, "onSuccess: "+config);
                }

                @Override
                public void onError(OkHttpException e) {
                }
            });
        });

        //签到
        findViewById(R.id.qiandaobt).setOnClickListener(v -> {

        });
        //增加金币
        findViewById(R.id.addmoneybt).setOnClickListener(v -> {

        });
        //查询金币
        findViewById(R.id.moneynumbt).setOnClickListener(v -> {

        });
        //体现
        findViewById(R.id.tixianbt).setOnClickListener(v -> {

        });
    }


}
