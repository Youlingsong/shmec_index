package com.datahome.bean;

import com.datahome.group.IndexDataGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/5/21 15:03
 */
@Setter
@Getter
public class IndexDataBean {

    @NotNull(groups = {IndexDataGroup.finds.class})
    private List<String> years;

    private List<Integer> cityIds;

    @NotNull(groups = {IndexDataGroup.finds.class})
    private List<Integer> indexIds;
}
