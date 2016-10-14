package aizulove.com.fxxt.modle.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 *主贴
 */

public class Post implements Serializable {

	private Integer id;//编号

	private String title;//标题

	private String img;//主图

	private String content;//内容

	private Integer zan;//赞数

	private Integer visit;//访问量

	private String source;//来源

	private Integer type;//1.发现，2.俱乐部

	private Integer logoId;//车标Id

	private  Member member;//会员id

	private List<Comment> commentList;//包含的评论

	private int size;
	
	private Date creatTime;//发表时间
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Integer getZan() {
		return zan;
	}

	public void setZan(Integer zan) {
		this.zan = zan;
	}

	public Integer getLogoId() {
		return logoId;
	}

	public void setLogoId(Integer logoId) {
		this.logoId = logoId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getVisit() {
		return visit;
	}

	public void setVisit(Integer visit) {
		this.visit = visit;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public  void setSize(int size){
		this.size=size;
	}

	public  int getSize(){
		return this.size;
	}
	
}
