package com.datahome.bean;

import com.datahome.group.NewsGroup;
import com.datahome.util.RegexUtil;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @Author xl
 * @Description: 新闻后台
 * @Date: Create in 2018/1/15 18:51
 */
@Setter
@Getter
public class NewsBean {

    @NotNull(groups = {NewsGroup.find.class})
    private Integer id;

    private String title;

    private String publishFlag;

    private Date publishTime;

    private Integer classesId;

    private String placeCode;

    @NotNull(groups = {NewsGroup.finds.class
    })
    @Range(min = 1, max = 100)
    private Integer pageSize;

    @NotNull(groups = {NewsGroup.finds.class
    })
    @Range(min = 1, max = 100)
    private Integer pageNumber;

    @Pattern(regexp = RegexUtil.ORDER)
    private String order;

    private String sort;
}
