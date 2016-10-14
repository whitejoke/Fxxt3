package aizulove.com.fxxt.modle.entity;

import java.util.Date;

/**
 *退货
 */
public class Reshipping {
	

	private Integer id;//编号
	

	private Float refundAmount;//退款金额

	private String alipay;//支付宝账号

	private String alipayName;//检查支付宝账号名字(防止账号填错)

	private String note;//退款原因

	private String remark;//退款备注

	private String expressName;//快递名称

	private String expressSingle;//快递单号

	private Date creatTime;//申请退款时间	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAlipay() {
		return alipay;
	}

	public void setAlipay(String alipay) {
		this.alipay = alipay;
	}

	public String getAlipayName() {
		return alipayName;
	}

	public void setAlipayName(String alipayName) {
		this.alipayName = alipayName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getExpressSingle() {
		return expressSingle;
	}

	public void setExpressSingle(String expressSingle) {
		this.expressSingle = expressSingle;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Float getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(Float refundAmount) {
		this.refundAmount = refundAmount;
	}
	
	
}
