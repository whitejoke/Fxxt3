package aizulove.com.fxxt.modle.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *报名人
 */

public class Candidate implements Serializable {

	private Integer id;//编号

	private String name;//名字

	private String phone;//

	private Date createTime;//
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
