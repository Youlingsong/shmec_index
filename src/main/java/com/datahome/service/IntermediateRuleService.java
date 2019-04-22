package com.datahome.service;

import com.datahome.bean.IntermediateRuleBean;

public interface IntermediateRuleService {
    String save(IntermediateRuleBean intermediateRuleBean);

    String delete(IntermediateRuleBean intermediateRuleBean);

    String find(IntermediateRuleBean intermediateRuleBean);

    String deletes(IntermediateRuleBean intermediateRuleBean);

    String finds(IntermediateRuleBean intermediateRuleBean);
}
