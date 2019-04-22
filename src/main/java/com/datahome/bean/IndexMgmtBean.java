package com.datahome.bean;

import com.datahome.group.IndexMgmtGroup;
import com.datahome.util.RegexUtil;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/5/10 16:34
 */

@Getter
@Setter
public class IndexMgmtBean {

    @NotNull(groups = {IndexMgmtGroup.delete.class, IndexMgmtGroup.find.class, IndexMgmtGroup.update.class})
    private Integer id;

    @NotNull(groups = {IndexMgmtGroup.save.class, IndexMgmtGroup.update.class, IndexMgmtGroup.combox.class})
    private Integer parentId;

    @NotNull(groups = {IndexMgmtGroup.save.class, IndexMgmtGroup.update.class})
    private String name;

    @NotNull(groups = {IndexMgmtGroup.save.class, IndexMgmtGroup.update.class})
    @Pattern(regexp = RegexUtil.COMMON_FLAG_CODE)
    private String indexStatus;

    private String description;

    private String evaluationStandards;

    @NotNull(groups = {IndexMgmtGroup.save.class, IndexMgmtGroup.update.class})
    private Double grossScore;

    private List<Integer> cityIdList;

    private List<Integer> departmentIdList;

    private String cityName;

    private String nodeIds;

    @NotNull(groups = {IndexMgmtGroup.save.class, IndexMgmtGroup.update.class})
    private Integer unitsId;

    private List<Integer> groupId;

    private String order;

    private String sort;

    private Integer pageSize;

    private Integer pageNumber;
}
