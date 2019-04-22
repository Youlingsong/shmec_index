package com.datahome.bean;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Setter
@Getter
public class QuestionareBean implements Serializable {

	@Range(min = 1)
	private Integer id;

	@Size(min = 1, max = 255)
	private String name;
	
	@Size(max = 1000)
	private String summary;
	
	@Size(min = 1, max = 10000)
	private String value;
	
	@Size(min = 1, max = 1000)
	private String info;
	
	@Pattern(regexp = "create|edit")
	private String saveType;
	
	@Min(value = 1)
	private Integer saveId;
	
	@Min(value = 1)
	private Integer page;

	@Range(min = 1, max = 100)
	private Integer rows;

	@Pattern(regexp = "asc|desc")
	private String order;

	@Pattern(regexp = "id|createTime|updateTime|status")
	private String sort;
	
	private Boolean status;
	
	private String urlId;
}
