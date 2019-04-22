package com.datahome.util;

import com.alibaba.fastjson.JSONArray;
import com.datahome.entity.AccountEntity;
import com.datahome.entity.CityEntity;
import com.datahome.entity.IndexEntity;
import com.datahome.repository.CityRepository;
import com.datahome.repository.IndexRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/5/15 10:08
 */

@Component
public class AccountUtil {


    @Resource
    private CityRepository cityDao;

    @Resource
    private IndexRepository indexDao;

    public String findHql(String hql, String name, String accountStatus, Integer cityId, LinkedHashMap<String, Object> params) {
        if (name != null) {
            hql += " and  name like :name ";
            params.put("name", "%" + name + "%");
        }
        if (accountStatus != null) {
            hql += " and accountStatus = :accountStatus ";
            params.put("accountStatus", accountStatus);
        }
        if (cityId != null) {
            hql += " and cityEntity.id = :cityId ";
            params.put("cityId", cityId);
        }

        return hql;
    }

    public Map<String, Object> getAccountEntityMap(AccountEntity accessEntity) {
        Map<String, Object> map = null;
        if (accessEntity != null) {
            map = new HashMap<>();
            map.put("id", accessEntity.getId());
            map.put("name", accessEntity.getName());
            map.put("account", accessEntity.getAccount());
            map.put("accountStatus", accessEntity.getAccountStatus());
            map.put("email", accessEntity.getEmail());
            map.put("phone", accessEntity.getPhone());
            map.put("institution", accessEntity.getInstitution());
            map.put("job", accessEntity.getJob());
        }
        return map;
    }

    //获取用户所有的可查看指标、所属城市
    public Map<String, Object> getCityAndAllowReadIndexs(AccountEntity accountEntity, Map<String, Object> resultMap) {

        //查询用户 可查看的指标
        String allowRead = accountEntity.getAllowReadIndexs();
        if (allowRead != null && !"".equals(allowRead)) {
            List<Integer> allowReadIdList = JSONArray.parseArray(allowRead, Integer.class);

            List<Map<String, Object>> allowReadList = new ArrayList<>();
            List<IndexEntity> allowReadEntityList = indexDao.find_by_idList_indexName_indexStatus_parentId(allowReadIdList, null, "1", null, null, null, 9999, 1);
            for (IndexEntity indexEntity : allowReadEntityList) {
                allowReadList.add(IndexUtil.getIndexEntityMap(indexEntity));
            }
            resultMap.put("allowReadIndexs", allowReadList);
        }

        //当前用户所属城市
        List<Map<String, Object>> resultCitys = new ArrayList<>();
        String citys = accountEntity.getCityIds();
        if (citys != null && !"".equals(citys)) {
            List<Integer> cityIds = JSONArray.parseArray(citys, Integer.class);
            List<CityEntity> cityEntitys = cityDao.findALLByCityStatusAndIdIn("1", cityIds);
            for (CityEntity cityEntity : cityEntitys) {
                Map<String, Object> map = new HashMap<>();
                map.put("cityId", cityEntity.getId());
                map.put("cityName", cityEntity.getCityName());
                map.put("cityStatus", cityEntity.getCityStatus());
                map.put("parentId", cityEntity.getParentId());
                resultCitys.add(map);
            }
        }
        resultMap.put("citys", resultCitys);
        return resultMap;
    }
}
