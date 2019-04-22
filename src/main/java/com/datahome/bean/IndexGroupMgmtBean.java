package com.datahome.bean;

import com.datahome.group.IndexGroupMgmtGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/5/4 19:03
 */

@Setter
@Getter
public class IndexGroupMgmtBean {

    @NotNull(groups = {IndexGroupMgmtGroup.update.class, IndexGroupMgmtGroup.delete.class, IndexGroupMgmtGroup.find.class})
    private Integer id;

    @NotNull(groups = {IndexGroupMgmtGroup.save.class, IndexGroupMgmtGroup.update.class})
    @Size(max = 200)
    private String groupName;
}
