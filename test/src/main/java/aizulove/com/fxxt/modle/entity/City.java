package aizulove.com.fxxt.modle.entity;

import java.util.List;

/**
 * Created by joker on 2016/8/8.
 */
public class City {

        private int areaid;
        private String areaname;
        private int parentid;
        private String arrparentid;
        private boolean child;
        private String arrchildid;
        private int listorder;
        private List<citylist> cityList;

    public List<citylist> getCityList() {
        return cityList;
    }

    public void setCityList(List<citylist> cityList) {
        this.cityList = cityList;
    }
    public class citylist{
        private String cityname;
        private int cityid;

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }

        public int getCityid() {
            return cityid;
        }

        public void setCityid(int cityid) {
            this.cityid = cityid;
        }
    }

    public int getAreaid() {
            return areaid;
        }

        public void setAreaid(int areaid) {
            this.areaid = areaid;
        }

        public String getAreaname() {
            return areaname;
        }

        public void setAreaname(String areaname) {
            this.areaname = areaname;
        }

        public int getParentid() {
            return parentid;
        }

        public void setParentid(int parentid) {
            this.parentid = parentid;
        }

        public String getArrparentid() {
            return arrparentid;
        }

        public void setArrparentid(String arrparentid) {
            this.arrparentid = arrparentid;
        }

        public boolean isChild() {
            return child;
        }

        public void setChild(boolean child) {
            this.child = child;
        }

        public String getArrchildid() {
            return arrchildid;
        }

        public void setArrchildid(String arrchildid) {
            this.arrchildid = arrchildid;
        }

        public int getListorder() {
            return listorder;
        }

        public void setListorder(int listorder) {
            this.listorder = listorder;
        }
}

