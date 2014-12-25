package com.gmail.volodymyrdotsenko.jqxwicket.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.gmail.volodymyrdotsenko.jqxwicket.domain.ProjectStatus;

@Repository
public class ProjectDAO {

	@PersistenceContext
	EntityManager entityManager;

	public List<ProjectStatus> findAllProjectStatuses() {
		return entityManager.createQuery("SELECT o FROM ProjectStatus o",
				ProjectStatus.class).getResultList();
	}

	public void install() {
		List<String> statusesExample = ProjectStatus.listExample();

		List<ProjectStatus> statuses = findAllProjectStatuses();

		l: for (String e : statusesExample) {
			for (ProjectStatus s : statuses) {
				if (e.equals(s.getName())) {
					continue l;
				}
			}
			
			entityManager.persist(new ProjectStatus(e));
		}
	}
}