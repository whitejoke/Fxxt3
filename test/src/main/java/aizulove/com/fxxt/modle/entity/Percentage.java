package aizulove.com.fxxt.modle.entity;

import java.util.Date;

/**
 *percentage#提成
 */
public class Percentage{
	

	private Integer id;//id

	private String orderId;//

	private String level;//

	private String name;//

	private String truename;//

	private String title;//

	private String proportion;//

	private Integer userId;//id

	private Float amont;//

	private Date createDate;// 创建时间
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Float getAmont() {
		return amont;
	}
	public void setAmont(Float amont) {
		this.amont = amont;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId(){
		return this.userId;
	}

	public void setUserId(Integer userId){
		this.userId=userId;
	}

	public String getTruename(){
		return this.truename;
	}

	public void setTruename(String truename){
		this.truename=truename;
	}

	public void setTitle(String title){
		this.title=title;
	}

	public String getTitle(){
		return title;
	}

	public void setProportion(String proportion){
		this.proportion=proportion;
	}

	public String getProportion(){
		return this.proportion;
	}
	
}
