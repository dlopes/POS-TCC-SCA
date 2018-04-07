/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.organizacao.sca.repository;

import com.organizacao.sca.model.Funcionario;
import com.organizacao.sca.repository.filter.FuncionarioFilter;
import com.organizacao.sca.service.NegocioException;
import com.organizacao.sca.util.jpa.Transactional;
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

/**
 *
 * @author Anjo_Grabiel
 */
public class Funcionarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    private EntityManager manager;

    public Funcionario porId(Long id) {
        try {
            return manager.createQuery("from Funcionario where id = :id", Funcionario.class).setParameter("id", id).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Funcionario guardar(Funcionario funcionario) {
        return manager.merge(funcionario);
    }

    public Funcionario porNomeOuEmail(String nome, String email) {
        try {
            return manager.createQuery("from Funcionario where upper(nome) = :nome and email = :email", Funcionario.class)
                    .setParameter("nome", nome.toUpperCase())
                    .setParameter("email", email.toUpperCase())
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Funcionario> porNome(String nome) {
        return this.manager.createQuery("from Funcionario "
                + "where upper(nome) like :nome", Funcionario.class)
                .setParameter("nome", nome.toUpperCase() + "%")
                .getResultList();
    }

    public Funcionario porEmail(String email) {
        try {
            return manager.createQuery("from Funcionario where upper(email) = :email", Funcionario.class)
                    .setParameter("email", email.toUpperCase())
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public void remover(Funcionario funcionario) throws NegocioException {
        try {
            funcionario = porId(funcionario.getId());
            manager.remove(funcionario);
            manager.flush();
        } catch (PersistenceException e) {
            throw new NegocioException("Funcionario não pode ser excluído.");
        }
    }

    @SuppressWarnings("unchecked")
    public List<Funcionario> filtrados(FuncionarioFilter filtro) {
        Session session = manager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Funcionario.class);

        if (StringUtils.isNotBlank(filtro.getCpf())) {
            criteria.add(Restrictions.eq("cpf", filtro.getCpf()));
        }

        if (StringUtils.isNotBlank(filtro.getNome())) {
            criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
        }

        return criteria.addOrder(Order.asc("nome")).list();
    }
}
