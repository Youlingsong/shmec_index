package com.datahome.bean;

import com.datahome.group.NewsGroup;
import com.datahome.group.NewsMgmtGroup;
import com.datahome.util.RegexUtil;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/5/7 17:51
 */
@Setter
@Getter
public class NewsMgmtBean {

    @NotNull(groups = {
            NewsMgmtGroup.find.class, NewsMgmtGroup.updates.class, NewsMgmtGroup.delete.class, NewsGroup.find.class
    })
    private Integer id;

    @NotNull(groups = {
            NewsMgmtGroup.save.class,
            NewsMgmtGroup.updates.class
    })
    @Size(min = 1, max = 200)
    private String title;

    @Size(max = 50)
    private String author;

    @Size(min = 1, max = 200)
    private String titleImageUrl;

    @Size(max = 255)
    private String summary;

    @NotNull(groups = {
            NewsMgmtGroup.save.class,
            NewsMgmtGroup.updates.class
    })
    @Size(min = 1, max = 5000000)
    private String content;

    @NotNull(groups = {
            NewsMgmtGroup.save.class,
            NewsMgmtGroup.updates.class
    })
    @Pattern(regexp = RegexUtil.COMMON_FLAG_CODE)
    private String publishFlag;

    @NotNull(groups = {
            NewsMgmtGroup.save.class,
            NewsMgmtGroup.updates.class
    })
    @Pattern(regexp = RegexUtil.COMMON_FLAG_CODE)
    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(groups = {
            NewsMgmtGroup.save.class,
            NewsMgmtGroup.updates.class
    })
    private Date publishTime;

    @NotNull(groups = {
            NewsMgmtGroup.save.class,
            NewsMgmtGroup.updateNewsClasses.class,
            NewsMgmtGroup.updates.class,
            NewsMgmtGroup.deleteNewsClasses.class,
            NewsMgmtGroup.findNewsClasses.class
    })
    private Integer classesId;

    @NotNull(groups = {
            NewsMgmtGroup.saveNewsClasses.class, NewsMgmtGroup.updateNewsClasses.class
    })
    private Integer parentId;

    @NotNull(groups = {
            NewsMgmtGroup.saveNewsClasses.class,
            NewsMgmtGroup.updateNewsClasses.class
    })
    @Size(max = 50, groups = {NewsMgmtGroup.class})
    private String classes;

    @Size(max = 100)
    private String source;

    @NotNull(groups = {
            NewsMgmtGroup.save.class,
            NewsMgmtGroup.updates.class
    })
    @Range(min = 1, max = 20)
    private Integer priority;

    @NotNull(groups = {
            NewsMgmtGroup.finds.class, NewsGroup.finds.class
    })
    private Integer pageSize;

    @NotNull(groups = {
            NewsMgmtGroup.finds.class, NewsGroup.finds.class
    })
    private Integer pageNumber;

    @Pattern(regexp = RegexUtil.ORDER)
    private String order;

    private String sort;
}
