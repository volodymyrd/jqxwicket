package com.gmail.volodymyrdotsenko.jqxwicket.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmail.volodymyrdotsenko.jqxwicket.dao.ProjectDAO;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDAO dao;

	@Transactional
	@Override
	public void install() {
		dao.install();
	}

}