/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organizacao.sca.repository;



import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.organizacao.sca.model.Aluno;
import com.organizacao.sca.repository.filter.AlunoFilter;
import com.organizacao.sca.service.NegocioException;
import com.organizacao.sca.util.jpa.Transactional;





/**
 *
 * @author Dilson
 */
public class Alunos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Aluno guardar(Aluno aluno) {
		return manager.merge(aluno);
	}
	
	@Transactional
	public void remover(Aluno aluno) throws NegocioException {
		try {
			aluno = porId(aluno.getId());
			manager.remove(aluno);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Aluno não pode ser excluído.");
		}
	}

        
        public Aluno porEmail(String email) {
		Aluno aluno = null;
		
		try {
			aluno = this.manager.createQuery("from Aluno where lower(email) = :email", Aluno.class)
				.setParameter("email", email.toLowerCase()).getSingleResult();
		} catch (NoResultException e) {
			// nenhum usuário encontrado com o e-mail informado
		}
		
		return aluno;
	}
        
        @SuppressWarnings("unchecked")
	public List<Aluno> filtrados(AlunoFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Aluno.class);
		
		if (StringUtils.isNotBlank(filtro.getRg())) {
			criteria.add(Restrictions.eq("rg", filtro.getRg()));
		}
		
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}

	public Aluno porId(Long id) {
		return manager.find(Aluno.class, id);
	}

	public List<Aluno> porNome(String nome) {
		return this.manager.createQuery("from Aluno where upper(nome) like :nome", Aluno.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}
	
}
