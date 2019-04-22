package com.datahome.dao.impl;

import com.datahome.dao.BaseDao;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * @Author xl
 * @Description: spring-data-jpa自身的查询语句  + 封装语句
 * @Date: Create in 2018/5/18 19:48
 */
public class BaseDaoImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
        implements BaseDao<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;

    protected Class<T> entityClass;

    public BaseDaoImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.entityManager = em;

    }

    @Bean
    protected Class getEntityClass() {
        if (entityClass == null) {
            entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }
        return entityClass;
    }

}
