package aizulove.com.fxxt.modle.entity;

import java.io.Serializable;
import java.util.Date;


/**
 *评论
 */

public class Comment implements Serializable {

	private Integer id;//编号
	private String title;//标题
	private String content;//内容

	private Member  member;//会员id
	private Post post;//主贴id
	private Integer postId;
	private Date creatTime;//发表时间

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postIdl) {
		this.postId = postIdl;
	}

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

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}



}
