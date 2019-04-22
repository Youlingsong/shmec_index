package com.datahome.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "age06_answer")
public class AnswerEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "askId")
    private AskEntity askEntity;

    @Column(columnDefinition = "varchar(100000)")
    private String value;

    @Column(columnDefinition = "varchar(1000)")
    private String info;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    private String ip;

}
