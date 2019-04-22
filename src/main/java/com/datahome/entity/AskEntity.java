package com.datahome.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "age06_ask")
public class AskEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String urlId;
	
	private String name;
	
	@Column(columnDefinition = "varchar(1000)")
	private String summary;

	@Column(columnDefinition = "varchar(100000)")
	private String value;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	
	private Boolean status;


}
