package aizulove.com.fxxt.modle.entity;

/**
 * Created by joker on 2016/8/17.
 */
public class CityList {
    private String cityName;
    private String cityId;
//    public CityList(String cityName, String cityId) {
//        super();
//        this.cityName = cityName;
//        this.cityId = cityId;
//    }
    public String getCityName() {
        return cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public String getCityId() {
        return cityId;
    }
    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
    @Override
    public String toString() {
        return "City [cityName=" + cityName + ", cityId=" + cityId + "]";
    }


}