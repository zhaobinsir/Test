package com.phonefangdajing.word.db;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class HuangLi {
        /*"gregoriandate": "2020-01-01 00:00:00",
        "lunardate": "2019-12-7",
        "lunar_festival": "",
        "festival": "元旦",
        "fitness": "祭祀.修填.涂泥.馀事勿取",
        "taboo": "移徙.入宅.嫁娶.开市.安葬",
        "shenwei": "喜神：东南 福神：东北 财神：正南阳贵：东南 阴贵：正东 ",
        "taishen": "房床若移整,大门修当避胎神在房内南停留6天",
        "chongsha": "兔日冲(丁酉)鸡",
        "suisha": "岁煞西",
        "wuxingjiazi": "土",
        "wuxingnayear": "平地木",
        "wuxingnamonth": "洞下水",
        "xingsu": "北方壁水貐-吉",
        "pengzu": "癸不词讼 卯不穿井",
        "jianshen": "平",
        "tiangandizhiyear": "己亥",
        "tiangandizhimonth": "丙子",
        "tiangandizhiday": "癸卯",
        "lmonthname": "季冬",
        "shengxiao": "猪",
        "lubarmonth": "腊月",
        "lunarday": "初七",
        "jieqi": ""
*/
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String gregoriandate;
    public String lunardate;
    public String lunar_festival;
    public String festival;
    public String fitness;
    public String taboo;
    public String shenwei;
    public String taishen;
    public String chongsha;
    public String suisha;
    public String wuxingjiazi;
    public String wuxingnayear;
    public String wuxingnamonth;
    public String xingsu;
    public String pengzu;
    public String jianshen;
    public String tiangandizhiyear;
    public String tiangandizhimonth;
    public String tiangandizhiday;
    public String lmonthname;
    public String shengxiao;
    public String lubarmonth;
    public String lunarday;
    public String jieqi;

    @Override
    public String toString() {
        return "HuangLi{" +
                "id=" + id +
                ", gregoriandate='" + gregoriandate + '\'' +
                ", lunardate='" + lunardate + '\'' +
                ", lunar_festival='" + lunar_festival + '\'' +
                ", festival='" + festival + '\'' +
                ", fitness='" + fitness + '\'' +
                ", taboo='" + taboo + '\'' +
                ", shenwei='" + shenwei + '\'' +
                ", taishen='" + taishen + '\'' +
                ", chongsha='" + chongsha + '\'' +
                ", suisha='" + suisha + '\'' +
                ", wuxingjiazi='" + wuxingjiazi + '\'' +
                ", wuxingnayear='" + wuxingnayear + '\'' +
                ", wuxingnamonth='" + wuxingnamonth + '\'' +
                ", xingsu='" + xingsu + '\'' +
                ", pengzu='" + pengzu + '\'' +
                ", jianshen='" + jianshen + '\'' +
                ", tiangandizhiyear='" + tiangandizhiyear + '\'' +
                ", tiangandizhimonth='" + tiangandizhimonth + '\'' +
                ", tiangandizhiday='" + tiangandizhiday + '\'' +
                ", lmonthname='" + lmonthname + '\'' +
                ", shengxiao='" + shengxiao + '\'' +
                ", lubarmonth='" + lubarmonth + '\'' +
                ", lunarday='" + lunarday + '\'' +
                ", jieqi='" + jieqi + '\'' +
                '}';
    }
}
