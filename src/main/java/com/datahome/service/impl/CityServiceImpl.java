package com.datahome.service.impl;

import com.alibaba.fastjson.JSON;
import com.datahome.bean.CityBean;
import com.datahome.entity.AccountEntity;
import com.datahome.entity.CityEntity;
import com.datahome.repository.AccountRepository;
import com.datahome.repository.CityRepository;
import com.datahome.service.CityService;
import com.datahome.util.CommonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/5/4 15:08
 */


@Service
public class CityServiceImpl implements CityService {

    @Resource
    private CityRepository cityDao;

    @Resource
    private AccountRepository accountDao;

    @Resource
    private HttpSession session;

    @Override
    public String combox(CityBean cityBean) {
        Integer parentId = cityBean.getParentId();
        if (parentId == null) {
            parentId = 0;
        }

        List<CityEntity> cityList = cityDao.findByParentIdAndCityStatusIn(parentId, Arrays.asList("1"));
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> resutlMap = null;
        if (cityList != null) {
            for (CityEntity cityEntity : cityList) {
                resutlMap = new HashMap<>();
                resutlMap.put("id", cityEntity.getId());
                resutlMap.put("cityName", cityEntity.getCityName());
                resutlMap.put("parentId", cityEntity.getParentId());
                resultList.add(resutlMap);
            }
        }
        return CommonUtil.format(2000, resultList);
    }

    @Override
    public String findAll() {
        Map<String, Object> accountMap = (Map<String, Object>) session.getAttribute("account");
        if (accountMap == null) {
            return CommonUtil.format(4401, "用户未登录");
        }

        //查询该用户所属城市
        List<Map<String, Object>> resultList = new ArrayList<>();
        Optional<AccountEntity> accountEntityOptional = accountDao.findById(Integer.parseInt(accountMap.get("id").toString()));
        AccountEntity accountEntity = accountEntityOptional.get();
        String cityId = accountEntity.getCityIds();
        if (cityId == null) {
            return CommonUtil.format(2000, resultList);
        }
        List<Integer> cityIds = JSON.parseArray(cityId, Integer.class);
        if (cityIds == null) {
            cityIds = new ArrayList<>();
        }

        List<CityEntity> cityEntitys = cityDao.findAllByCityStatusInOrderBySaveTime(Arrays.asList("1"));
        for (CityEntity cityEntity : cityEntitys) {
            Map<String, Object> map = new HashMap<>();
            Integer cityId2 = cityEntity.getId();
            if (cityIds.contains(cityId2)) {
                map.put("flag", "1");
            } else {
                map.put("flag", "2");
            }
            map.put("id", cityId2);
            map.put("cityName", cityEntity.getCityName());
            map.put("cityStatus", cityEntity.getCityStatus());
            map.put("levelCode", cityEntity.getLevelCode());
            map.put("parentId", cityEntity.getParentId());
            resultList.add(map);
        }

        return CommonUtil.format(2000, resultList);
    }
}
