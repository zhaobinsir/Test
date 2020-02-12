package demo.shizhi.com.calendardemo.block.entity;

import java.util.List;

public class HolidayJson {


    /**
     * 2020 节假日信息
     * holiday : [{"holiday":true,"name":"元旦","wage":3,"date":"2020-01-01"},{"holiday":false,"name":"春节前调休","after":false,"wage":1,"target":"春节","date":"2020-01-19"},{"holiday":true,"name":"除夕","wage":2,"date":"2020-01-24"},{"holiday":true,"name":"初一","wage":3,"date":"2020-01-25"},{"holiday":true,"name":"初二","wage":3,"date":"2020-01-26"},{"holiday":true,"name":"初三","wage":3,"date":"2020-01-27"},{"holiday":true,"name":"初四","wage":2,"date":"2020-01-28","rest":1},{"holiday":true,"name":"初五","wage":2,"date":"2020-01-29","rest":1},{"holiday":true,"name":"初六","wage":2,"date":"2020-01-30","rest":1},{"holiday":true,"name":"初七","wage":2,"date":"2020-01-31","rest":1},{"holiday":true,"name":"初八","wage":2,"date":"2020-02-01","rest":1},{"holiday":true,"name":"初九","wage":2,"date":"2020-02-02","rest":1},{"holiday":true,"name":"清明节","wage":3,"date":"2020-04-04","rest":56},{"holiday":true,"name":"清明节","wage":2,"date":"2020-04-05","rest":1},{"holiday":true,"name":"清明节","wage":2,"date":"2020-04-06","rest":1},{"holiday":false,"name":"劳动节前调休","after":false,"wage":1,"target":"劳动节","date":"2020-04-26","rest":20},{"holiday":true,"name":"劳动节","wage":3,"date":"2020-05-01","rest":25},{"holiday":true,"name":"劳动节","wage":2,"date":"2020-05-02","rest":1},{"holiday":true,"name":"劳动节","wage":2,"date":"2020-05-03","rest":1},{"holiday":true,"name":"劳动节","wage":2,"date":"2020-05-04","rest":1},{"holiday":true,"name":"劳动节","wage":2,"date":"2020-05-05"},{"holiday":false,"name":"劳动节后调休","after":true,"wage":1,"target":"劳动节","date":"2020-05-09"},{"holiday":true,"name":"端午节","wage":3,"date":"2020-06-25"},{"holiday":true,"name":"端午节","wage":2,"date":"2020-06-26"},{"holiday":true,"name":"端午节","wage":2,"date":"2020-06-27"},{"holiday":false,"after":true,"name":"端午节后调休","wage":1,"target":"端午节","date":"2020-06-28"},{"holiday":false,"after":false,"name":"国庆节前调休","wage":1,"target":"国庆节","date":"2020-09-27"},{"holiday":true,"name":"中秋节","wage":3,"date":"2020-10-01"},{"holiday":true,"name":"国庆节","wage":3,"date":"2020-10-02"},{"holiday":true,"name":"国庆节","wage":3,"date":"2020-10-03"},{"holiday":true,"name":"国庆节","wage":2,"date":"2020-10-04"},{"holiday":true,"name":"国庆节","wage":2,"date":"2020-10-05"},{"holiday":true,"name":"国庆节","wage":2,"date":"2020-10-06"},{"holiday":true,"name":"国庆节","wage":2,"date":"2020-10-07"},{"holiday":true,"name":"国庆节","wage":2,"date":"2020-10-08"},{"holiday":false,"name":"国庆节后调休","after":true,"wage":1,"target":"国庆节","date":"2020-10-10"}]
     */

    private List<HolidayBean> holiday;

    public List<HolidayBean> getHoliday() {
        return holiday;
    }

    public void setHoliday(List<HolidayBean> holiday) {
        this.holiday = holiday;
    }

    public static class HolidayBean {
        /**
         * holiday : true
         * name : 元旦
         * wage : 3
         * date : 2020-01-01
         * after : false
         * target : 春节
         * rest : 1
         */

        private boolean holiday;
        private String name;
        private int wage;
        private String date;
        private boolean after;
        private String target;
        private int rest;

        public boolean isHoliday() {
            return holiday;
        }

        public void setHoliday(boolean holiday) {
            this.holiday = holiday;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getWage() {
            return wage;
        }

        public void setWage(int wage) {
            this.wage = wage;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public boolean isAfter() {
            return after;
        }

        public void setAfter(boolean after) {
            this.after = after;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public int getRest() {
            return rest;
        }

        public void setRest(int rest) {
            this.rest = rest;
        }
    }
}
