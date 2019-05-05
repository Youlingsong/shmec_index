package com.datahome.service.impl;

import com.datahome.bean.IndexBean;
import com.datahome.entity.IndexEntity;
import com.datahome.repository.AccountRepository;
import com.datahome.repository.IndexRepository;
import com.datahome.service.IndexService;
import com.datahome.util.CommonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author xl
 * @Description: 查询 用户指标
 * @Date: Create in 2018/5/18 9:22
 */

@Service
public class IndexServiceImpl implements IndexService {


    @Resource
    private IndexRepository indexDao;

    @Resource
    private AccountRepository accountDao;

    @Resource
    private HttpSession session;

    @Override
    public String finds(IndexBean indexBean) {

//        Map<String, Object> accountMap = (Map<String, Object>) session.getAttribute("account");
//        if (accountMap == null) {
//            return CommonUtil.format(4200, "用户未登录！");
//        }
//        Optional<AccountEntity> accountEntityOptional = accountDao.findById(Integer.parseInt(accountMap.get("id").toString()));
//        if (!accountEntityOptional.isPresent()) {
//            return CommonUtil.format(4200, "用户不存在！");
//        }
//
//        //用户状态
//        AccountEntity accountEntity = accountEntityOptional.get();
//        if (!"1".equals(accountEntity.getAccountStatus())) {
//            return CommonUtil.format(4200, "账号被禁止使用！");
//        }
//
//        String allowReadIndex = accountEntity.getAllowReadIndexs();
//        List<Integer> allowReadList = JSONArray.parseArray(allowReadIndex, Integer.class);

        //全部指标 ，用于加载指标树
        List<IndexEntity> indexEntitys = indexDao.find_by_idList_indexName_indexStatus_parentId(null, null, "1", null, null, null, 99999, 1);
        List<Map<String, Object>> allList = new ArrayList<>();
        for (IndexEntity indexEntity : indexEntitys) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", indexEntity.getName());
            map.put("nodeIds", indexEntity.getNodeIds());
            map.put("parentId", indexEntity.getParentId());
            allList.add(map);
        }

        //当前用户可查看指标
        List<Map<String, Object>> resultList = new ArrayList<>();
//        if (allowReadList != null) {
//            List<IndexEntity> indexEntityList = indexDao.find_by_idList_indexName_indexStatus_parentId(allowReadList, null, "1", null, null, null, 99999, 1);
//            for (IndexEntity indexEntity : indexEntityList) {
//                resultList.add(IndexUtil.getIndexEntityMap(indexEntity));
//            }
//        }

        //返回结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("allowReadList", resultList);
        resultMap.put("allList", allList);
        return CommonUtil.format(2000, resultMap);
    }

    @Override
    public String findsRootIndexs() {
        //全部指标 ，用于加载指标树
        List<IndexEntity> indexEntitys = indexDao.find_by_idList_indexName_indexStatus_parentId(null, null, "1", 0, null, null, 99999, 1);
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (IndexEntity indexEntity : indexEntitys) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", indexEntity.getName());
            map.put("nodeIds", indexEntity.getNodeIds());
            map.put("parentId", indexEntity.getParentId());
            resultList.add(map);
        }
        return CommonUtil.format(2000, resultList);
    }
}
