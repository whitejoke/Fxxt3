package aizulove.com.fxxt.modle.entity;

import java.io.Serializable;

/**
 
 */

public class Member implements Serializable {


	private int userid;
	
	private String username;//
	
	//通行证
	private String passport;

	private String company;

	private String password;//密码

	private String payword;//支付密码

	private String email;//

	private int message;//新信件数量

	private int chat;//新对话数量

	private int sound;//站内信声音

	private int online;//1在线 0隐身

	private int avatar;//是否有头像

	private int gender;//性别

	private String truename;//真名

	private String mobile;//联系电话

	private String msn;

	private String qq;

	private String ali;

	private String skype;

	private String department;//部门

	private String career;//职位

	private int admin;//

	private String role;//

	private int aid;//

	private int groupid;//
	

	private int regid;//

	private int areaid;
	

	private int sms;//短信余额
	

	private int credit;//积分余额
	
	private float money;//资金余额
	
	//@Column(name = "locking")
	//private float locking=0;//资金锁定
	
	private String bank;//收款银行
	
	
	private int banktype;//银行类型
	
	private String branch;//
	
	private String account;//收款账号
	
	private long edittime;//修改时间

	private String regip;//注册ip

	private long regtime;//注册时间

	private String loginip;//登录ip

	private long logintime;//登录时间

	private int logintimes;//登录次数

	private String black;//站内信黑名单

	private int send;//是否允许转发站内信至邮箱

	private String auth;//验证密钥

	private String authvalue;//验证内容

	private int authtime;//验证时间

	private int vemail;//邮件认证

	private int vmobile;//手机认证

	private int vtruename;//实名认证	

	private int vbank;//银行认证
	

	private int vcompany;//公司认证	
	

	private int vtrade;//支付宝帐号认证
	

	private String trade;//支付宝帐号
	

	private String support;//客服专员
	

	private String inviter;//推荐注册人
	
	
	
	

	private String province;//省
	

	private String city;//城市
	
	/*@Column(name = "address")
	private String address;//收货地址
	
	@Column(name = "randomuuid")
	private String randomuuid;//唯一标示
	
	@Column(name = "createDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;//创建时间
	
	@Column(name = "loginDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date loginDate;//登录时间
	
	@Column(name = "loginNum")
	private int loginNum;//登录次数
	
	@Column(name = "status")
	private int status;//账户状态 0：禁用   1：启用   2：申请中

	*/
	private String level;
	
	private String firstLevelName;//
	
	private int firstLevelId=0;//
	
	private String secondLevelName;//

	private String token;//

	private  String avatarUrl;
	
	private int secondLevelId=0;//
	
	private int thirdLevelId=0;//

	private String referralCode;

	private String code;

	private String attentionids;

	public String getAttentionids() {
		return attentionids;
	}

	public void setAttentionids(String attentionids) {
		this.attentionids = attentionids;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getReferralCode() {
		return referralCode;
	}

	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	
	public String getTruename() {
		return truename;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getAli() {
		return ali;
	}

	public void setAli(String ali) {
		this.ali = ali;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPayword() {
		return payword;
	}

	public void setPayword(String payword) {
		this.payword = payword;
	}

	public int getMessage() {
		return message;
	}

	public void setMessage(int message) {
		this.message = message;
	}

	public int getChat() {
		return chat;
	}

	public void setChat(int chat) {
		this.chat = chat;
	}

	public int getSound() {
		return sound;
	}

	public void setSound(int sound) {
		this.sound = sound;
	}

	public int getOnline() {
		return online;
	}

	public void setOnline(int online) {
		this.online = online;
	}

	public int getAvatar() {
		return avatar;
	}

	public void setAvatar(int avatar) {
		this.avatar = avatar;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public int getRegid() {
		return regid;
	}

	public void setRegid(int regid) {
		this.regid = regid;
	}

	public int getAreaid() {
		return areaid;
	}

	public void setAreaid(int areaid) {
		this.areaid = areaid;
	}

	public int getSms() {
		return sms;
	}

	public void setSms(int sms) {
		this.sms = sms;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public int getBanktype() {
		return banktype;
	}

	public void setBanktype(int banktype) {
		this.banktype = banktype;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	

	public long getEdittime() {
		return edittime;
	}

	public void setEdittime(long edittime) {
		this.edittime = edittime;
	}

	public long getRegtime() {
		return regtime;
	}

	public void setRegtime(long regtime) {
		this.regtime = regtime;
	}

	public long getLogintime() {
		return logintime;
	}

	public void setLogintime(long logintime) {
		this.logintime = logintime;
	}

	public String getRegip() {
		return regip;
	}

	public void setRegip(String regip) {
		this.regip = regip;
	}

	

	public String getLoginip() {
		return loginip;
	}

	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}

	

	public int getLogintimes() {
		return logintimes;
	}

	public void setLogintimes(int logintimes) {
		this.logintimes = logintimes;
	}

	public String getBlack() {
		return black;
	}

	public void setBlack(String black) {
		this.black = black;
	}

	public int getSend() {
		return send;
	}

	public void setSend(int send) {
		this.send = send;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getAuthvalue() {
		return authvalue;
	}

	public void setAuthvalue(String authvalue) {
		this.authvalue = authvalue;
	}

	public int getAuthtime() {
		return authtime;
	}

	public void setAuthtime(int authtime) {
		this.authtime = authtime;
	}

	public int getVemail() {
		return vemail;
	}

	public void setVemail(int vemail) {
		this.vemail = vemail;
	}

	public int getVmobile() {
		return vmobile;
	}

	public void setVmobile(int vmobile) {
		this.vmobile = vmobile;
	}

	public int getVtruename() {
		return vtruename;
	}

	public void setVtruename(int vtruename) {
		this.vtruename = vtruename;
	}

	public int getVbank() {
		return vbank;
	}

	public void setVbank(int vbank) {
		this.vbank = vbank;
	}

	public int getVcompany() {
		return vcompany;
	}

	public void setVcompany(int vcompany) {
		this.vcompany = vcompany;
	}

	public int getVtrade() {
		return vtrade;
	}

	public void setVtrade(int vtrade) {
		this.vtrade = vtrade;
	}

	public String getTrade() {
		return trade;
	}

	public void setTrade(String trade) {
		this.trade = trade;
	}

	public String getSupport() {
		return support;
	}

	public void setSupport(String support) {
		this.support = support;
	}

	public String getInviter() {
		return inviter;
	}

	public void setInviter(String inviter) {
		this.inviter = inviter;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getFirstLevelName() {
		return firstLevelName;
	}

	public void setFirstLevelName(String firstLevelName) {
		this.firstLevelName = firstLevelName;
	}

	public int getFirstLevelId() {
		return firstLevelId;
	}

	public void setFirstLevelId(int firstLevelId) {
		this.firstLevelId = firstLevelId;
	}

	public String getSecondLevelName() {
		return secondLevelName;
	}

	public void setSecondLevelName(String secondLevelName) {
		this.secondLevelName = secondLevelName;
	}

	public int getSecondLevelId() {
		return secondLevelId;
	}

	public void setSecondLevelId(int secondLevelId) {
		this.secondLevelId = secondLevelId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getThirdLevelId() {
		return thirdLevelId;
	}

	public void setThirdLevelId(int thirdLevelId) {
		this.thirdLevelId = thirdLevelId;
	}

	public  String getAvatarUrl(){
		return  this.avatarUrl;
	}
	
	public void setAvatarUrl(String avatarUrl){
		this.avatarUrl=avatarUrl;
	}

	public void setToken(String token){this.token=token;}

	public String getToken(){return this.token;}
}
