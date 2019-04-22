package com.datahome.dao;

import com.datahome.middledata.GdnMiddleDataModel;

import java.util.List;

public interface GdnMiddleDataRuleDao {
    List<GdnMiddleDataModel> dealsql(String sqlsentence);
}
