package com.datahome.middledata;

import com.datahome.group.IndexMgmtGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class IndexModel {
    @NotNull(groups = {IndexMgmtGroup.save.class,IndexMgmtGroup.delete.class, IndexMgmtGroup.find.class, IndexMgmtGroup.update.class})
    private Integer id;

    @NotNull(groups = {IndexMgmtGroup.save.class})
    private Integer score;
}
