package com.datahome.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author xl
 * @Description: 分组信息中间表
 * @Date: Create in 2018/5/26 14:45
 */
@Entity
@Table(name = "age06_indexgroup_index")
public class IndexGroupIndexEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "indexGroupId", nullable = false)
    private IndexGroupEntity indexGroupEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "indexId", nullable = false)
    private IndexEntity indexEntity;

    @Temporal(TemporalType.TIMESTAMP)
    private Date saveTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public IndexGroupEntity getIndexGroupEntity() {
        return indexGroupEntity;
    }

    public void setIndexGroupEntity(IndexGroupEntity indexGroupEntity) {
        this.indexGroupEntity = indexGroupEntity;
    }

    public IndexEntity getIndexEntity() {
        return indexEntity;
    }

    public void setIndexEntity(IndexEntity indexEntity) {
        this.indexEntity = indexEntity;
    }

    public Date getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(Date saveTime) {
        this.saveTime = saveTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
