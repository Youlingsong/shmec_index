package com.datahome.dao;

import com.datahome.entity.GdnMiddleDataEntity;
import com.datahome.middledata.GdnMiddleDataModel;

import java.util.List;

public interface GdnMiddleDataDao {


    List<GdnMiddleDataEntity> findBykey_value_remake(GdnMiddleDataModel gdnMiddleDataModel);

    void deleteby_ruleid(String ruleid);
}
