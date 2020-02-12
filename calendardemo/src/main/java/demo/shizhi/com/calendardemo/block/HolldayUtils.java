package demo.shizhi.com.calendardemo.block;

import android.content.Context;
import android.content.res.Resources;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import demo.shizhi.com.calendardemo.R;
import demo.shizhi.com.calendardemo.block.entity.HolidayJson;

public class HolldayUtils {

    public static HolidayJson getHoliDay(Context context){
        try {
            InputStream inputStream= context.getResources().openRawResource(R.raw.holiday);
            return new Gson().fromJson(new InputStreamReader(inputStream),HolidayJson.class);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (JsonIOException e) {
            e.printStackTrace();
        }
        return null;
    }

   /* public synchronized static String request(String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        String httpUrl = "http://api.tianapi.com/txapi/lunar/index?key=39cbafad8a20ad34dbbab1396a7ee827&date="+httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) throws ParseException, IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int year=2020;
        int month=12;
        //存放所有日期的List列表
        List<Long> dates=new ArrayList<>();
        for (int y = year; y <=2030; y++) {//年
            System.out.println(y+"年\n");
            //月
            for (int m= 1; m<= month; m++) {
                System.out.println(m+"月\n");
                //日
                Calendar calendar=Calendar.getInstance();
                calendar.set(y,m,0);
                //本月多少天总共
                int day= calendar.get(Calendar.DAY_OF_MONTH);
                StringBuilder builder=new StringBuilder();
                for (int d = 1; d<=day; d++) {
                    builder.append(d+"号 ");
                    String time=y+"-"+m+"-"+d;
                    dates.add(sdf.parse(time).getTime()/1000);
                    if (d==day) {//最后一条换行
                        builder.append("\n\n");
                    }
                }
                System.out.println(builder.toString());
            }
        }

        System.out.println("列表大小 "+dates.size());
        long time=System.currentTimeMillis();//开始
        System.out.println("开始时间："+time);
        for (int i = 0; i < dates.size(); i++) {
            try {
                Thread.sleep(200);
                getNet(String.valueOf(dates.get(i)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("恭喜完成 耗时(秒)："+(System.currentTimeMillis()-time)/1000+"S");

    }

    public synchronized static void getNet(String time) throws IOException {
        String jsonResult = request(time);
        RBean rBean=new Gson().fromJson(jsonResult,RBean.class);
        //转换json
        RBean.NewslistBean cbean=rBean.getNewslist().get(0);
        //已时间戳 设置的json key
        String jkey=cbean.getGregoriandate();
        String json=new Gson().toJson(cbean);
        //自定义凭借
        String finaljson="\""+jkey+"\""+":"+json+",\n";

        String path="D:\\"+"rili.json";
        FileUtils.writeStringToFile(new File(path),finaljson,"utf-8",true);
        System.out.println("本地已存入");
    }


    static class RBean{

        *//**
         * code : 200
         * msg : success
         * newslist : [{"gregoriandate":"2020-01-01 00:00:00","lunardate":"2019-12-7","lunar_festival":"","festival":"元旦","fitness":"祭祀.修填.涂泥.馀事勿取","taboo":"移徙.入宅.嫁娶.开市.安葬","shenwei":"喜神：东南 福神：东北 财神：正南阳贵：东南 阴贵：正东 ","taishen":"房床若移整,大门修当避胎神在房内南停留6天","chongsha":"兔日冲(丁酉)鸡","suisha":"岁煞西","wuxingjiazi":"土","wuxingnayear":"平地木","wuxingnamonth":"洞下水","xingsu":"北方壁水貐-吉","pengzu":"癸不词讼 卯不穿井","jianshen":"平","tiangandizhiyear":"己亥","tiangandizhimonth":"丙子","tiangandizhiday":"癸卯","lmonthname":"季冬","shengxiao":"猪","lubarmonth":"腊月","lunarday":"初七","jieqi":""}]
         *//*

        private int code;
        private String msg;
        private List<NewslistBean> newslist;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public List<NewslistBean> getNewslist() {
            return newslist;
        }

        public void setNewslist(List<NewslistBean> newslist) {
            this.newslist = newslist;
        }

        public static class NewslistBean {
            *//**
             * gregoriandate : 2020-01-01 00:00:00
             * lunardate : 2019-12-7
             * lunar_festival :
             * festival : 元旦
             * fitness : 祭祀.修填.涂泥.馀事勿取
             * taboo : 移徙.入宅.嫁娶.开市.安葬
             * shenwei : 喜神：东南 福神：东北 财神：正南阳贵：东南 阴贵：正东
             * taishen : 房床若移整,大门修当避胎神在房内南停留6天
             * chongsha : 兔日冲(丁酉)鸡
             * suisha : 岁煞西
             * wuxingjiazi : 土
             * wuxingnayear : 平地木
             * wuxingnamonth : 洞下水
             * xingsu : 北方壁水貐-吉
             * pengzu : 癸不词讼 卯不穿井
             * jianshen : 平
             * tiangandizhiyear : 己亥
             * tiangandizhimonth : 丙子
             * tiangandizhiday : 癸卯
             * lmonthname : 季冬
             * shengxiao : 猪
             * lubarmonth : 腊月
             * lunarday : 初七
             * jieqi :
             *//*

            private String gregoriandate;
            private String lunardate;
            private String lunar_festival;
            private String festival;
            private String fitness;
            private String taboo;
            private String shenwei;
            private String taishen;
            private String chongsha;
            private String suisha;
            private String wuxingjiazi;
            private String wuxingnayear;
            private String wuxingnamonth;
            private String xingsu;
            private String pengzu;
            private String jianshen;
            private String tiangandizhiyear;
            private String tiangandizhimonth;
            private String tiangandizhiday;
            private String lmonthname;
            private String shengxiao;
            private String lubarmonth;
            private String lunarday;
            private String jieqi;

            public String getGregoriandate() {
                return gregoriandate;
            }

            public void setGregoriandate(String gregoriandate) {
                this.gregoriandate = gregoriandate;
            }

            public String getLunardate() {
                return lunardate;
            }

            public void setLunardate(String lunardate) {
                this.lunardate = lunardate;
            }

            public String getLunar_festival() {
                return lunar_festival;
            }

            public void setLunar_festival(String lunar_festival) {
                this.lunar_festival = lunar_festival;
            }

            public String getFestival() {
                return festival;
            }

            public void setFestival(String festival) {
                this.festival = festival;
            }

            public String getFitness() {
                return fitness;
            }

            public void setFitness(String fitness) {
                this.fitness = fitness;
            }

            public String getTaboo() {
                return taboo;
            }

            public void setTaboo(String taboo) {
                this.taboo = taboo;
            }

            public String getShenwei() {
                return shenwei;
            }

            public void setShenwei(String shenwei) {
                this.shenwei = shenwei;
            }

            public String getTaishen() {
                return taishen;
            }

            public void setTaishen(String taishen) {
                this.taishen = taishen;
            }

            public String getChongsha() {
                return chongsha;
            }

            public void setChongsha(String chongsha) {
                this.chongsha = chongsha;
            }

            public String getSuisha() {
                return suisha;
            }

            public void setSuisha(String suisha) {
                this.suisha = suisha;
            }

            public String getWuxingjiazi() {
                return wuxingjiazi;
            }

            public void setWuxingjiazi(String wuxingjiazi) {
                this.wuxingjiazi = wuxingjiazi;
            }

            public String getWuxingnayear() {
                return wuxingnayear;
            }

            public void setWuxingnayear(String wuxingnayear) {
                this.wuxingnayear = wuxingnayear;
            }

            public String getWuxingnamonth() {
                return wuxingnamonth;
            }

            public void setWuxingnamonth(String wuxingnamonth) {
                this.wuxingnamonth = wuxingnamonth;
            }

            public String getXingsu() {
                return xingsu;
            }

            public void setXingsu(String xingsu) {
                this.xingsu = xingsu;
            }

            public String getPengzu() {
                return pengzu;
            }

            public void setPengzu(String pengzu) {
                this.pengzu = pengzu;
            }

            public String getJianshen() {
                return jianshen;
            }

            public void setJianshen(String jianshen) {
                this.jianshen = jianshen;
            }

            public String getTiangandizhiyear() {
                return tiangandizhiyear;
            }

            public void setTiangandizhiyear(String tiangandizhiyear) {
                this.tiangandizhiyear = tiangandizhiyear;
            }

            public String getTiangandizhimonth() {
                return tiangandizhimonth;
            }

            public void setTiangandizhimonth(String tiangandizhimonth) {
                this.tiangandizhimonth = tiangandizhimonth;
            }

            public String getTiangandizhiday() {
                return tiangandizhiday;
            }

            public void setTiangandizhiday(String tiangandizhiday) {
                this.tiangandizhiday = tiangandizhiday;
            }

            public String getLmonthname() {
                return lmonthname;
            }

            public void setLmonthname(String lmonthname) {
                this.lmonthname = lmonthname;
            }

            public String getShengxiao() {
                return shengxiao;
            }

            public void setShengxiao(String shengxiao) {
                this.shengxiao = shengxiao;
            }

            public String getLubarmonth() {
                return lubarmonth;
            }

            public void setLubarmonth(String lubarmonth) {
                this.lubarmonth = lubarmonth;
            }

            public String getLunarday() {
                return lunarday;
            }

            public void setLunarday(String lunarday) {
                this.lunarday = lunarday;
            }

            public String getJieqi() {
                return jieqi;
            }

            public void setJieqi(String jieqi) {
                this.jieqi = jieqi;
            }
        }
    }*/


}
