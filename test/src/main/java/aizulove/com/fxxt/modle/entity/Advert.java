package aizulove.com.fxxt.modle.entity;

/**
 * Created by Administrator on 2016/5/26.
 */
public class Advert {
    private Integer aid;
    private String title;
    private String url;
    private  String imageSrc;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAid(Integer aid){
        this.aid=aid;
    }

    public  Integer getAid(){
        return this.aid;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public String getUrl(){
        return this.url;
    }

    public void setImageSrc(String imageSrc){
       this.imageSrc=imageSrc;
    }

    public String getImageSrc(){
        return  imageSrc;
    }
}
