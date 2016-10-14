package aizulove.com.fxxt.modle.entity;

import java.io.Serializable;
import java.util.Date;


/**
 *优惠卷
 */

public class Coupon implements Serializable {
	

	private Integer id;//编号

	private String title;//

	private String promoCode;//

	private Double promoPrice;//优惠价格

	private String promoImage;//图片

	private Integer expiryStatus;//失效状态

	private String brief;//简介

	private Date startTime;//创建时间

	private Date overTime;//创建时间

	private Date createTime;//创建时间

	private Integer status;//状态 0:不使用，1:使用

	private Integer isDel;//状态 0:不删除，1:删除

	private Integer term;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public Integer getTerm() {
		return term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public Double getPromoPrice() {
		return promoPrice;
	}

	public void setPromoPrice(Double promoPrice) {
		this.promoPrice = promoPrice;
	}

	public String getPromoImage() {
		return promoImage;
	}

	public void setPromoImage(String promoImage) {
		this.promoImage = promoImage;
	}


	public Integer getExpiryStatus() {
		return expiryStatus;
	}

	public void setExpiryStatus(Integer expiryStatus) {
		this.expiryStatus = expiryStatus;
	}


	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getOverTime() {
		return overTime;
	}

	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}


	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	
	
	
}
