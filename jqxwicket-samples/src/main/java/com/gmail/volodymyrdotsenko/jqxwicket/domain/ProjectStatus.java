package com.gmail.volodymyrdotsenko.jqxwicket.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project_statuses")
public class ProjectStatus implements Serializable {

	private static final long serialVersionUID = 1L;

	public static List<String> listExample() {
		List<String> list = new ArrayList<String>();

		list.add("status1");
		list.add("status2");
		list.add("status3");
		list.add("status4");
		list.add("status5");

		return list;
	}

	public ProjectStatus() {
	}

	public ProjectStatus(String name) {
		this.name = name;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")	
	private Long id;

	@Column(name = "name", length = 255)
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}