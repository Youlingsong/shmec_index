package com.datahome.bean;

import com.datahome.group.IndexUnitsMgmtGroup;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/5/4 19:03
 */
public class IndexUnitsMgmtBean {

    @NotNull(groups = {
            IndexUnitsMgmtGroup.update.class, IndexUnitsMgmtGroup.delete.class, IndexUnitsMgmtGroup.find.class
    })
    private Integer id;

    @NotNull(groups = {
            IndexUnitsMgmtGroup.save.class,
            IndexUnitsMgmtGroup.update.class
    })
    @Size(max = 200)
    private String unitsName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnitsName() {
        return unitsName;
    }

    public void setUnitsName(String unitsName) {
        this.unitsName = unitsName;
    }
}
