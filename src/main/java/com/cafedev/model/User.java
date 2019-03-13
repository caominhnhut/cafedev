package com.cafedev.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cafedev.enums.EStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Nhut Nguyen on 01-07-2018.
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "USERS", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"username", "email" }) })
public class User implements UserDetails {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "first_name", nullable = true)
	private String firstName;

	@Column(name = "last_name", nullable = true)
	private String lastName;

	@Column(name = "email", length = 50)
	private String email;

	@Column(name = "phone_number", nullable = true)
	private String phoneNumber;
	
	@Column(name = "avatar")
	private String avatar;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private EStatus status = EStatus.ACTIVE;

	@Column(name = "create_date")
	private Date createDate;
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", 
	joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private List<Role> roles = new ArrayList<Role>();
	public List<Role> getRoles() {
		return roles;
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Feed> feeds = new ArrayList<Feed>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comments = new ArrayList<Comment>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Article> articles = new ArrayList<Article>();
	
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExaminationUser> examinations = new ArrayList<>();

	public List<Article> getArticles() {
		return articles;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public List<Feed> getFeeds() {
		return feeds;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<ExaminationUser> getExaminations() {
		return examinations;
	}

	public void setExaminations(List<ExaminationUser> examinations) {
		this.examinations = examinations;
	}
	@Override
	public String toString() {
		StringBuffer strUser = new StringBuffer();
		strUser
		.append("User Info:\nUsername: ").append(this.username)
		.append("\nPassword: ").append(this.password)
		.append("\nfirstName: ").append(this.firstName)
		.append("\nlastName: ").append(this.lastName)
		.append("\nemail: ").append(this.email)
		.append("\nphoneNumber: ").append(this.phoneNumber)
		.append("\navatar: ").append(this.avatar)
		.append("\nroles:\n");
		for(Role r: roles)
		{
			strUser.append(r.getId()+" - "+r.getName().name()+"\n");
		}
		
		return strUser.toString();
	}

	public User(Long id, String username, String password, String firstName,
			String lastName, String email, String phoneNumber, String avatar,
			EStatus status, Date createDate, List<Role> roles,
			List<Feed> feeds, List<Comment> comments, List<Article> articles,
			List<ExaminationUser> examinations) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.avatar = avatar;
		this.status = status;
		this.createDate = createDate;
		this.roles = roles;
		this.feeds = feeds;
		this.comments = comments;
		this.articles = articles;
		this.examinations = examinations;
	}

	public User() {
		super();
	}
	
}
