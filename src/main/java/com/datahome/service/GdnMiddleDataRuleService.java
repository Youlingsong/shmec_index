package com.datahome.service;

import com.datahome.bean.GdnMiddleDataRuleBean;

public interface GdnMiddleDataRuleService {
    String save(GdnMiddleDataRuleBean gdnMiddleDataRuleBean) throws Exception;

    String update(GdnMiddleDataRuleBean gdnMiddleDataRuleBean);

    String find(GdnMiddleDataRuleBean gdnMiddleDataRuleBean);

    String finds(GdnMiddleDataRuleBean gdnMiddleDataRuleBean);

    String delete(GdnMiddleDataRuleBean gdnMiddleDataRuleBean);
}
