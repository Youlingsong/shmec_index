package com.datahome.bean;

import com.datahome.group.IndexDataMgmtGroup;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/5/21 15:04
 */

@Getter
@Setter
public class IndexDataMgmtBean {

    @NotNull(groups = {IndexDataMgmtGroup.update.class,IndexDataMgmtGroup.delete.class})
    private Integer id;

    private Integer indexId;

    @Range(min = 0, max = 100)
    private Double value;

    @NotNull(groups = {IndexDataMgmtGroup.update.class})
    @Pattern(regexp = "^(1|2|3|4)$")
    private String indexDataStatus;

    private Integer cityId;

    private Integer departmentId;

    @NotNull(groups = {IndexDataMgmtGroup.saveIndexData.class})
    private String year;

    private String order;

    private String sort;

    private Integer pageSize;

    private Integer pageNumber;

}
