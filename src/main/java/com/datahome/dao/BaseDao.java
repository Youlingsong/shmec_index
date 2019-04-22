package com.datahome.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @Author xl
 * @Description: 可以在这里封装全局方法
 * @Date: Create in 2018/5/18 19:47
 */
@NoRepositoryBean
public interface BaseDao<T, ID extends Serializable> extends JpaRepository<T, ID> {


}
