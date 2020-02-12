package demo.shizhi.com.calendardemo;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnDismissListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.necer.calendar.Miui10Calendar;
import com.necer.entity.CalendarDate;
import com.necer.entity.Lunar;
import com.necer.painter.InnerPainter;
import com.necer.utils.CalendarUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import demo.shizhi.com.calendardemo.block.HttpUtils;
import demo.shizhi.com.calendardemo.block.entity.HolidayJson;
import demo.shizhi.com.calendardemo.block.HolldayUtils;
import demo.shizhi.com.calendardemo.block.WeekUtils;
import demo.shizhi.com.calendardemo.block.entity.LunarEntity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

///未使用注解框架，网络问题，死活加不上 干
public class CalendarActivity extends AppCompatActivity implements Callback {

    public static final String TAG = "CalendarActivity";


    private Miui10Calendar miui10Calendar;
    private LinearLayout calendarTitle_ln;

    //时间选择器
    TimePickerView pvCustomLunar;
    TimePickerBuilder timePickerBuilder;

    //标题 时间等信息
    private TextView titleTv, timeTv, lunarTv, spaceTime;
    //详细年月老农历
    private TextView oldlunarTv;
    //五行彭祖吉凶等信息 五行，冲煞 彭祖 宜忌，吉神，凶神
    private TextView wxTv, csTv, pzTv, yiTv, jiTv, jsTv, xsTv;
    //老农历信息
    private String oldlunarCon;

    //返回按钮 及标题按钮
    private ImageView backImag, statuimg;

    public static final int SUCCESS = 0;
    public static final int ERROR = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newmiulayout);
        initId();
        //日历初始化
        miu10Init();
        //隐藏aciotnbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
            setActionBar(findViewById(R.id.toolbar));
        }
        //监听初始化
        initListener();
        //日期选择初始化
        initTimePicker();
    }

    private void initId() {
        miui10Calendar = findViewById(R.id.miui10Calendar);
        calendarTitle_ln = findViewById(R.id.calendarTitle_ln);
        titleTv = findViewById(R.id.title_tv);
        backImag = findViewById(R.id.back_img);
        statuimg = findViewById(R.id.status_img);
        timeTv = findViewById(R.id.year_tv);
        lunarTv = findViewById(R.id.lunar_tv);
        spaceTime = findViewById(R.id.dimdd_tv);
        oldlunarTv = findViewById(R.id.oldtext_tv);

        wxTv = findViewById(R.id.wxinfo_tv);
        csTv = findViewById(R.id.csinfo_tv);
        pzTv = findViewById(R.id.pzinfo_tv);
        yiTv = findViewById(R.id.yiinfo_tv);
        jiTv = findViewById(R.id.jiinfo_tv);
        jsTv = findViewById(R.id.jsinfo_tv);
        xsTv = findViewById(R.id.xsinfo_tv);
    }

    private void miu10Init() {
        //设置日历可拉伸
        miui10Calendar.setMonthStretchEnable(true);
        //用于节假日操作
        InnerPainter innerPainter = (InnerPainter) miui10Calendar.getCalendarPainter();
        HolidayJson beanjson = HolldayUtils.getHoliDay(this);
        if (beanjson == null) return;
        //节假日
        List<String> holidayList = new ArrayList<>();
        //加班调休
        List<String> workdayList = new ArrayList<>();
        //遍历读取节假日/加班调休
        for (HolidayJson.HolidayBean holidayBean : beanjson.getHoliday()) {
            if (holidayBean.isHoliday())
                holidayList.add(holidayBean.getDate());
            else workdayList.add(holidayBean.getDate());
        }
        //向日历中插入描绘数据
        innerPainter.setLegalHolidayList(holidayList, workdayList);
    }

    private void initListener() {
        //日历监听事件
        miui10Calendar.setOnCalendarChangedListener((baseCalendar, year, month, localDate) -> {
            Log.d(TAG, "   当前页面选中 " + localDate);
            if (localDate != null) {
                CalendarDate calendarDate = CalendarUtil.getCalendarDate(localDate);
                Lunar lunar = calendarDate.lunar;//农历
                String time = localDate.toString("yyyy-MM-dd");
                //actionbar标题时间设置
                titleTv.setText(localDate.toString("yyyy年MM月"));
                //子标题设置
                timeTv.setText(localDate.toString("yyyy年MM月dd日"));
                //农历
                lunarTv.setText(lunar.lunarMonthStr + lunar.lunarDayStr);
                //相隔天数
                spaceTime.setText(getSpaceOfDays(time));
                //老农历时间
                oldlunarCon = WeekUtils.getWeekNum(time) + " " + WeekUtils.getWeek(localDate.toDate());
                oldlunarTv.setText(oldlunarCon);
                //发起网络请求
                HttpUtils.getInstance().getDataAsync(time,CalendarActivity.this);
                Log.d(TAG, "onCalendarChange: " + localDate.toString("yyyy年MM月dd日"));
                Log.d(TAG, "onCalendarChange: " + lunar.chineseEra + lunar.animals + "年" + lunar.lunarMonthStr + lunar.lunarDayStr);
            }
        });
        //时间选择监听
        calendarTitle_ln.setOnClickListener(v -> {
            if (!pvCustomLunar.isShowing()) {
                statuimg.setImageResource(R.drawable.up);
                pvCustomLunar.show();
            }
        });
        //返回结束
        backImag.setOnClickListener(v -> {
            finish();
        });
    }

    //是否为农历
    private static boolean isiunar;

    private void initTimePicker() {
        //时间选择器 ，自定义布局
        timePickerBuilder = new TimePickerBuilder(this, (date, v) -> {
            miui10Calendar.jumpDate(getTime(date));
        })
                .setLayoutRes(R.layout.mypickerview_custom_lunar, v -> {
                    //日期展示
                    final TextView timeshowTv = v.findViewById(R.id.timeshow_tv);
                    //周几
                    final TextView weekTv = v.findViewById(R.id.week_tv);
                    //农历日历切换
                    TextView lunarTv = v.findViewById(R.id.changelauar_tv);
                    //取消按钮
                    TextView cancelTv = v.findViewById(R.id.cancel_tv);
                    //确认按钮
                    TextView confirmTv = v.findViewById(R.id.confirm_tv);


                    //日期切换监听
                    timePickerBuilder.setTimeSelectChangeListener(date -> {
                        weekTv.setText(WeekUtils.getWeek(date));
                        timeshowTv.setText(getTime(date));
                    });

                    //监听事件
                    cancelTv.setOnClickListener(v1 -> {
                                pvCustomLunar.dismiss();
                            }
                    );
                    confirmTv.setOnClickListener(v12 -> {
                        pvCustomLunar.returnData();
                        pvCustomLunar.dismiss();
                    });
                    // 切换监听
                    lunarTv.setOnClickListener(v13 -> {
                        //农历变阳历
                        if (isiunar) {
                            isiunar = false;
                            pvCustomLunar.setLunarCalendar(false);
                        }//阳历变农历
                        else {
                            isiunar = true;
                            pvCustomLunar.setLunarCalendar(true);
                        }
                    });

                })
                .setType(new boolean[]{true, true, true, false, false, false})
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(Color.parseColor("#DDDDDD"))//设置弹窗分割线颜色
                .isDialog(true);
        pvCustomLunar = timePickerBuilder.build();
        pvCustomLunar.setOnDismissListener(o -> {
            statuimg.setImageResource(R.drawable.down);
        });
    }

    //获得两个时间 相隔的天数 不要看逻辑 真扯淡dogshit
    private String getSpaceOfDays(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        long oned = 1000 * 24 * 60 * 60;// 一天的毫秒数
        //当天时间
        String nds = format.format(new Date(System.currentTimeMillis()));
        long nd = 0;
        //目标时间
        long posd = 0;
        try {
            nd = format.parse(nds).getTime();
            posd = format.parse(date).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //相差的天数
        int spaceday = 0;
        if (nd != posd) {
            spaceday = (int) ((Math.abs(nd - posd) / oned));
        }
        if (spaceday == 0) {
            return "当天";
        }
        //判断两个日期大小
        if (nd > posd) {
            return spaceday + "天前";
        } else return spaceday + "天后";
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCCESS://正常
                    LunarEntity entity = (LunarEntity) msg.obj;
                    //获得黄历数据
                    LunarEntity.ResultBean.HuangliBean hl = entity.getResult().getHuangli();
                    //老农历信息
                    String oldCon=getStringByList(hl.getSuici())+"\r"+oldlunarCon;
                    oldlunarTv.setText(oldCon);
                    wxTv.setText(hl.getWuxing());//五行
                    csTv.setText(hl.getChong() + hl.getSha());//冲煞
                    pzTv.setText(hl.getTaishen());//彭祖
                    //宜
                    String yi = getStringByList(hl.getYi());
                    yiTv.setText(yi);
                    //忌
                    String ji = getStringByList(hl.getJi());
                    jiTv.setText(ji);
                    //吉神
                    jsTv.setText(hl.getJishenyiqu());
                    //凶神
                    xsTv.setText(hl.getXiongshen());
                    break;
                case ERROR://异常
                    Toast.makeText(CalendarActivity.this, (String) msg.obj, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    //获取宜忌 字符串
    private String getStringByList(List<String> list) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i == (list.size() - 1)) {//若为最后一条数据 不添加空格
                builder.append(list.get(i));
            } else builder.append(list.get(i) + "\r");
        }
        return builder.length() != 0 ? builder.toString() : "无数据";
    }

    //请求失败
    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {
        Log.d(TAG, "onFailure: " + e.getMessage());
        sendMsg(ERROR, e.getMessage());
    }

    //请求成功
    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) {
        try {
            if (response.code() == 200) {
                LunarEntity entity = new Gson().fromJson(response.body().string(), LunarEntity.class);
                if (entity != null) {//第三方接口请求成功
                    if (entity.getStatus() == 0) {
                        sendMsg(SUCCESS, entity);
                    } else {
                        sendMsg(ERROR, entity.getMsg());
                    }
                }
            } else
                sendMsg(ERROR, response.message());
        } catch (Exception e) {
            sendMsg(ERROR, e.getMessage());
        }

    }

    private void sendMsg(int what, Object object) {
        Message message = handler.obtainMessage();
        message.obj = object;
        message.what = what;
        handler.sendMessage(message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
