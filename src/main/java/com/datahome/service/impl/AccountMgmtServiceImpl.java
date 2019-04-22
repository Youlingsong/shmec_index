package com.datahome.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.datahome.bean.AccountMgmtBean;
import com.datahome.entity.AccountEntity;
import com.datahome.entity.CityEntity;
import com.datahome.entity.StaffEntity;
import com.datahome.entity.StaffRoleEntity;
import com.datahome.repository.AccountRepository;
import com.datahome.repository.CityRepository;
import com.datahome.repository.IndexRepository;
import com.datahome.service.AccountMgmtService;
import com.datahome.util.AccountUtil;
import com.datahome.util.CommonUtil;
import com.datahome.util.StaffUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.annotation.WebServlet;
import java.util.*;

/**
 * @Author xl
 * @Description: 普通用户管理
 * @Date: Create in 2018/5/8 17:50
 */
@WebServlet
@Service
public class AccountMgmtServiceImpl implements AccountMgmtService {

    @Resource
    private AccountRepository accountDao;

    @Resource
    private CityRepository cityDao;

    @Resource
    private IndexRepository indexDao;

    @Resource
    private AccountUtil accountUtil;

    @Resource
    private StaffUtil staffUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String save(AccountMgmtBean accountMgmtBean) {
        //获取新增的用户等级
        List<Integer> cityIds = accountMgmtBean.getCityIds();
        List<Integer> allowReadIndexs = accountMgmtBean.getAllowReadIndexs();
        String name = accountMgmtBean.getName();
        String account = accountMgmtBean.getAccount();

        //权限检验
        if (!checkAdmin(cityIds)) {
            return CommonUtil.format(4200, " 无权操作！");
        }

        //校验账号是否存在,name 作为昵称，不做校验
        if (accountDao.findByAccount(account) != null) {
            return CommonUtil.format(4200, " 该账号已被占用！");
        }

        //实例化
        AccountEntity accountEntity = new AccountEntity();
        accountEntity = checkAndSetCityIdsAndAllowReadIndexs(accountEntity, cityIds, allowReadIndexs);
        accountEntity.setName(name);
        accountEntity.setAccount(account);
        accountEntity.setEmail(accountMgmtBean.getEmail());
        accountEntity.setName(accountMgmtBean.getName());
        accountEntity.setPassword(CommonUtil.encodeBase64(accountMgmtBean.getPassword()));
        accountEntity.setPhone(accountMgmtBean.getPhone());
        accountEntity.setAccountStatus(accountMgmtBean.getAccountStatus());
        accountEntity.setInstitution(accountMgmtBean.getInstitution());
        accountEntity.setJob(accountMgmtBean.getJob());
        accountEntity.setSaveTime(new Date());
        accountDao.save(accountEntity);
        return CommonUtil.format(2000, "新增成功！");
    }

    @Override
    public String find(AccountMgmtBean accountBean) {

        StaffEntity staffEntity = staffUtil.getCurrentStaff();
        if (staffEntity == null || !"admin".equals(staffEntity.getStaffRoleEntity().getRoleKey())) {
            return CommonUtil.format(4200, "无权操作！");
        }

        Optional<AccountEntity> optionalAccountEntity = accountDao.findById(accountBean.getId());
        if (optionalAccountEntity == null) {
            return CommonUtil.format(4200, "查无数据！");
        }

        //用户基本信息
        AccountEntity accountEntity = optionalAccountEntity.get();
        Map<String, Object> resultMap = accountUtil.getAccountEntityMap(accountEntity);

        return CommonUtil.format(2000, accountUtil.getCityAndAllowReadIndexs(accountEntity, resultMap));
    }

    //查询用户列表
    @Override
    public String finds(AccountMgmtBean accountBean) {

        String name = accountBean.getName();
        String accountStatus = accountBean.getAccountStatus();

        StaffEntity staffEntity = staffUtil.getCurrentStaff();
        if (staffEntity == null || !"admin".equals(staffEntity.getStaffRoleEntity().getRoleKey())) {
            return CommonUtil.format(4200, "无权操作！");
        }

        Integer total = accountDao.findTotal_by_accountStatus_city_name(name, accountStatus, null);
        if (total == 0) return "{\"status\":2000,\"message\":[]}";

        List<AccountEntity> accessEntityList = accountDao.find_by_accountStatus_city_name(name, accountStatus, null, accountBean.getOrder(), accountBean.getSort(), accountBean.getPageSize(), accountBean.getPageNumber());
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (AccountEntity accountEntity : accessEntityList) {
            resultList.add(accountUtil.getCityAndAllowReadIndexs(accountEntity, accountUtil.getAccountEntityMap(accountEntity)));
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", total);
        resultMap.put("rows", resultList);

        return CommonUtil.format(2000, resultMap);
    }

    //初始化密码（初始密码是用户的登录账号）
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public String initPassword(AccountMgmtBean accountBean) {

        StaffEntity staffEntity = staffUtil.getCurrentStaff();
        if (staffEntity == null || !"admin".equals(staffEntity.getStaffRoleEntity().getRoleKey())) {
            return CommonUtil.format(4200, "无权操作！");
        }

        Optional<AccountEntity> optionalAccountEntity = accountDao.findById(accountBean.getId());
        if (optionalAccountEntity == null) {
            return CommonUtil.format(4200, "查无数据！");
        }

        //用户基本信息
        AccountEntity accountEntity = optionalAccountEntity.get();
        accountEntity.setPassword(CommonUtil.encodeBase64("123456"));
        accountEntity.setUpdateTime(new Date());
        accountDao.save(accountEntity);

        return CommonUtil.format(2000, "修改成功！");
    }

    //管理员修改用户基本信息
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public String updateInfo(AccountMgmtBean accountMgmtBean) {

        Integer id = accountMgmtBean.getId();
        String name = accountMgmtBean.getName();
        String account = accountMgmtBean.getAccount();
        List<Integer> cityIds = accountMgmtBean.getCityIds();
        List<Integer> allowReadIndexs = accountMgmtBean.getAllowReadIndexs();

        //权限检验
        if (!checkAdmin(cityIds)) {
            return CommonUtil.format(4200, " 无权操作！");
        }

        //用戶信息
        Optional<AccountEntity> optionalAccountEntity = accountDao.findById(id);
        if (!optionalAccountEntity.isPresent()) {
            return CommonUtil.format(4200, "查无数据！");
        }
        AccountEntity accountEntity = optionalAccountEntity.get();

        //账号检验
        if (!accountEntity.getAccount().equals(account) && accountDao.findByAccount(account) != null) {
            return CommonUtil.format(4200, " 该账号已被占用！");
        }

        //修改值
        accountEntity = checkAndSetCityIdsAndAllowReadIndexs(accountEntity, cityIds, allowReadIndexs);
        accountEntity.setName(name);
        accountEntity.setAccount(account);
        accountEntity.setPhone(accountMgmtBean.getPhone());
        accountEntity.setEmail(accountMgmtBean.getEmail());
        accountEntity.setAccountStatus(accountMgmtBean.getAccountStatus());
        accountEntity.setInstitution(accountMgmtBean.getInstitution());
        accountEntity.setJob(accountMgmtBean.getJob());

        accountDao.save(accountEntity);
        return CommonUtil.format(2000, "修改成功！");
    }

    //删除用户
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public String delete(AccountMgmtBean accessBean) {

        StaffEntity staffEntity = staffUtil.getCurrentStaff();
        if (staffEntity == null || !"admin".equals(staffEntity.getStaffRoleEntity().getRoleKey())) {
            return CommonUtil.format(4200, "无权操作！");
        }

        Optional<AccountEntity> optionalAccountEntity = accountDao.findById(accessBean.getId());
        if (!optionalAccountEntity.isPresent()) {
            return CommonUtil.format(4200, "查无数据！");
        }
        accountDao.delete(optionalAccountEntity.get());
        return CommonUtil.format(2000, "删除成功！");
    }


    //检查并设置用户的可查看指标、所属城市
    private AccountEntity checkAndSetCityIdsAndAllowReadIndexs(AccountEntity accountEntity, List<Integer> cityIds, List<Integer> allowReadIndexs) {

        //指标数据校验
        if (allowReadIndexs != null && allowReadIndexs.size() > 0) {
            if (allowReadIndexs.size() != indexDao.findCountBy_indexStatus_ids("1", allowReadIndexs)) {
                throw new RuntimeException(CommonUtil.format(4200, "指标数据异常！"));
            }
            accountEntity.setAllowReadIndexs(JSONObject.toJSONString(allowReadIndexs));
        }

        //城市数据校验
        if (cityIds != null && cityIds.size() != 0) {
            List<CityEntity> cityEntities = cityDao.findALLByCityStatusAndIdIn("1", cityIds);
            if (cityEntities == null || cityEntities.size() != cityIds.size()) {
                throw new RuntimeException(CommonUtil.format(4200, "城市数据异常！"));
            }
            accountEntity.setCityIds(JSON.toJSONString(cityIds));
        }
        return accountEntity;
    }

    private Boolean checkAdmin(List<Integer> cityIds) {
        StaffEntity staffEntity = staffUtil.getCurrentStaff();
        if (staffEntity == null) return false;
        //如果是超级管理员
        if ("1".equals(staffEntity.getAdminFlag())) {
            return true;
        }

        //当前用户所属的城市Id
        List<Integer> cityIds2 = cityDao.findAllChildrenIdBy_nodeIds_cityStatus(staffEntity.getCityEntity().getNodeIds(), Arrays.asList("1"));
        StaffRoleEntity staffRoleEntity2 = staffEntity.getStaffRoleEntity();

        //当前用户角色
        String roleKey2 = staffRoleEntity2.getRoleKey();

        //各个层级的管理员
        if ("admin".equals(roleKey2) && cityIds2.containsAll(cityIds)) {
            return true;
        }
        return false;
    }
}
