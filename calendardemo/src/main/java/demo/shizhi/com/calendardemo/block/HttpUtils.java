package demo.shizhi.com.calendardemo.block;

import org.jetbrains.annotations.NotNull;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtils {

    private static HttpUtils instance;

    private  HttpUtils(){}

    private static OkHttpClient okHttpClient;

    public static HttpUtils getInstance() {
        if (instance==null) {
            okHttpClient=new OkHttpClient();
            instance=new  HttpUtils();
        }
        return instance;
    }
   //请求日历天数所对应的详细信息
    public void getDataAsync(@NotNull String date, @NotNull Callback callback){
        synchronized (instance){
            Request request = new Request.Builder()
                    .url("https://api.jisuapi.com/calendar/query?appkey=c81e0b49546ebe65&date="+date)
                    .build();
            okHttpClient.newCall(request).enqueue(callback);
        }
    }
}
