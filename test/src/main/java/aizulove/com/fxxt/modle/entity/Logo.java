package aizulove.com.fxxt.modle.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 *车标（俱乐部）
 */

public class Logo implements Serializable {

	private Integer id;//编号
	
	private String title;//标题
	
	private String logoImg;//图
	
	private String sort;// 排序

	private Integer zan;//赞
	
	private Integer quality=0;//数量

	private Integer isAttention;

	private String content;

	private Date createTime;//发表时间
	
	private List<Post> postList;//
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLogoImg() {
		return logoImg;
	}

	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}

	public void setZan(Integer zan){
		this.zan=zan;
	}

	public Integer getZan(){
		return  this.zan;
	}

	public void setQuality(Integer quality){
		this.quality=quality;
	}

	public Integer getQuality(){
		return   quality;
	}

	public void setPostList(List<Post> postList){
		this.postList=postList;
	}

	public  List<Post> getPostList(){
		return this.postList;
	}

	public  void setContent(String content){
		this.content=content;
	}

	public String getContent(){
		return  this.content;
	}

	public  Integer getIsAttention(){return this.isAttention;}

	public void setIsAttention(Integer isAttention){this.isAttention=isAttention;}
}
