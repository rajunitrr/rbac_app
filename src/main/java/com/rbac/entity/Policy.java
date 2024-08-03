package com.rbac.entity;

//import javax.persistence.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Policies", schema = "eis_auth")
@Data
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sub", nullable = false)
    private String sub;

    @Column(name = "dom", nullable = false)
    private String dom;

    @Column(name = "obj", nullable = false)
    private String obj;

    @Column(name = "act", nullable = false)
    private String act;

    @Column(name = "eft", nullable = false)
    private String eft;

	public void setId(Long id2) {
		this.id = id2;		
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String getDom() {
		return dom;
	}

	public void setDom(String dom) {
		this.dom = dom;
	}

	public String getObj() {
		return obj;
	}

	public void setObj(String obj) {
		this.obj = obj;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}

	public String getEft() {
		return eft;
	}

	public void setEft(String eft) {
		this.eft = eft;
	}

	public Long getId() {
		return id;
	}
	
	
	
}
