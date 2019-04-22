package com.datahome.service.impl;

import com.datahome.bean.StaffBean;
import com.datahome.entity.StaffEntity;
import com.datahome.entity.StaffRoleEntity;
import com.datahome.repository.CityRepository;
import com.datahome.repository.StaffRepository;
import com.datahome.service.StaffService;
import com.datahome.util.CommonUtil;
import com.datahome.util.StaffUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @Author xl
 * @Description: 管理员
 * @Date: Create in 2018/5/8 17:50
 */

@Service
public class StaffServiceImpl implements StaffService {

    @Resource
    private StaffRepository staffDao;

    @Resource
    private CityRepository cityDao;

    @Resource
    private HttpSession session;

    @Resource
    private StaffUtil staffUtil;

    //管理员 登录
    @Override
    public String login(StaffBean staffBean) {

        StaffEntity staffEntity = staffDao.findByNameAndPassword(staffBean.getName(), CommonUtil.encodeBase64(staffBean.getPassword()));
        if (staffEntity == null) {
            return CommonUtil.format(4200, "账号或密码错误！");
        }

        if ("2".equals(staffEntity.getStatus())) {
            return CommonUtil.format(4200, "账号被禁用！");
        }

        Map<String, Object> staff = getStaffEntityMap(staffEntity);
        session.setAttribute("staff", staff);

        return CommonUtil.format(2000, staff);
    }

    //管理员 登出
    @Override
    public String logout(StaffBean staffBean) {
        session.invalidate();
        return CommonUtil.format(2000, "注销成功！");
    }

    //新增管理员
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String save(StaffBean staffBean) {
        String name = staffBean.getName();
        Integer cityId = staffBean.getCityId();
        Integer roleId = staffBean.getRoleId();

        //校验用户角色
        List<StaffRoleEntity> staffRoleEntities = staffDao.findStaffRoleBy_Id(roleId);
        if (staffRoleEntities == null || staffRoleEntities.size() != 1) {
            return CommonUtil.format(4200, " 用户角色错误！");
        }
        StaffRoleEntity staffRoleEntity = staffRoleEntities.get(0);

        //权限校验
        if (!checkAdmin(cityId, staffRoleEntity)) {
            return CommonUtil.format(4200, " 无权新增用户！");
        }

        //校验用户名和账号是否存在
        if (staffDao.findByName(name) != null) {
            return CommonUtil.format(4200, " 该用户名已被占用！");
        }
        StaffEntity staffEntity = new StaffEntity();
        staffEntity.setName(name);
        staffEntity.setPassword(CommonUtil.encodeBase64(staffBean.getPassword()));
        staffEntity.setPhone(staffBean.getPhone());
        staffEntity.setEmail(staffBean.getEmail());
        staffEntity.setStatus(staffBean.getStatus());
        staffEntity.setAdminFlag("2");
        staffEntity.setCityEntity(cityDao.findById(cityId).get());
        staffEntity.setStaffRoleEntity(staffRoleEntity);
        staffEntity.setSaveTime(new Date());
        staffDao.save(staffEntity);
        return CommonUtil.format(2000, "新增成功！");
    }

    @Override
    public String find(StaffBean staffBean) {
        Optional<StaffEntity> optionalStaffEntity = staffDao.findById(staffBean.getId());
        if (!optionalStaffEntity.isPresent()) {
            return CommonUtil.format(4200, "查无数据！");
        }
        return CommonUtil.format(2000, getStaffEntityMap(optionalStaffEntity.get()));
    }

    //查询用户列表
    @Override
    public String finds(StaffBean staffBean) {
        //获取当前登录用户
        StaffEntity staffEntity = staffUtil.getCurrentStaff();
        if (staffEntity == null || (!"1".equals(staffEntity.getAdminFlag()) && !"admin".equals(staffEntity.getStaffRoleEntity().getRoleKey()))) {
            return CommonUtil.format(4200, " 无权查看！");
        }
        List<Integer> cityIds = cityDao.findAllChildrenIdBy_nodeIds_cityStatus(staffEntity.getCityEntity().getNodeIds(), Arrays.asList("1"));
        List<StaffEntity> staffEntities = staffDao.findsBy_cityIds_status(cityIds, staffBean.getStatus());
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (StaffEntity staffEntity1 : staffEntities) {
            if ("admin".equals(staffEntity1.getStaffRoleEntity().getRoleKey()) && "2".equals(staffEntity.getAdminFlag()) && staffEntity.getId() != staffEntity1.getId()) {
                continue;
            }
            resultList.add(getStaffEntityMap(staffEntity1));
        }

        return CommonUtil.format(2000, resultList);
    }

    //管理员修改用户基本信息
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public String updateInfo(StaffBean staffBean) {
        String staffName = staffBean.getName();
        Integer cityId = staffBean.getCityId();
        Integer roleId = staffBean.getRoleId();

        //校验用户角色
        List<StaffRoleEntity> staffRoleEntities = staffDao.findStaffRoleBy_Id(roleId);
        if (staffRoleEntities == null || staffRoleEntities.size() != 1) {
            return CommonUtil.format(4200, " 用户角色错误！");
        }
        StaffRoleEntity staffRoleEntity = staffRoleEntities.get(0);

        //权限校验
        if (!checkAdmin(cityId, staffRoleEntity)) {
            return CommonUtil.format(4200, " 无权新增用户！");
        }

        Optional<StaffEntity> optionalStaffEntity = staffDao.findById(staffBean.getId());
        if (optionalStaffEntity == null) {
            return CommonUtil.format(4200, "查无数据！");
        }
        StaffEntity staffEntity = optionalStaffEntity.get();

        //校验用户名和账号是否存在
        StaffEntity temp = staffDao.findByName(staffName);
        if (temp != null && temp.getId() != staffEntity.getId()) {
            return CommonUtil.format(4200, " 该用户名已被占用！");
        }

        staffEntity.setName(staffName);
        staffEntity.setStatus(staffBean.getStatus());
        staffEntity.setPhone(staffBean.getPhone());
        staffEntity.setEmail(staffBean.getEmail());
        staffEntity.setAdminFlag("2");
        staffEntity.setStaffRoleEntity(staffRoleEntity);
        staffEntity.setCityEntity(cityDao.findById(cityId).get());
        staffEntity.setUpdateTime(new Date());
        //修改
        staffDao.save(staffEntity);
        return CommonUtil.format(2000, "修改成功！");
    }

    //初始化密码（初始密码是123456）
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public String initPassword(StaffBean staffBean) {

        //查询数据
        Optional<StaffEntity> optionalStaffEntity = staffDao.findById(staffBean.getId());
        if (optionalStaffEntity == null) {
            return CommonUtil.format(4200, "查无数据！");
        }
        StaffEntity staffEntity = optionalStaffEntity.get();

        //权限校验
        if (!checkAdmin(staffEntity.getCityEntity().getId(), staffEntity.getStaffRoleEntity())) {
            return CommonUtil.format(4200, " 无权操作！");
        }

        staffEntity.setPassword(CommonUtil.encodeBase64("123456"));
        staffEntity.setUpdateTime(new Date());
        //修改
        staffDao.save(staffEntity);
        return CommonUtil.format(2000, "修改成功！");
    }


    //个人中心修改密码
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public String updateMyselfPassword(StaffBean staffBean) {
        StaffEntity staffEntity = staffDao.findByNameAndPassword(staffBean.getName(), CommonUtil.encodeBase64(staffBean.getPassword()));
        if (staffEntity == null) {
            return CommonUtil.format(4200, "用户名或密码错误！");
        }
        staffEntity.setPassword(CommonUtil.encodeBase64(staffBean.getPassword()));

        //修改
        staffDao.save(staffEntity);
        return CommonUtil.format(2000, "修改成功！");
    }

    //个人中心修改用户信息
    @Override
    public String updateMyselfInfo(StaffBean staffBean) {
        Optional<StaffEntity> optionalStaffEntity = staffDao.findById(staffBean.getId());
        if (optionalStaffEntity == null) {
            return CommonUtil.format(4200, "查无数据！");
        }
        StaffEntity staffEntity = optionalStaffEntity.get();

        //校验用户名和账号是否存在
        String staffName = staffBean.getName();
        StaffEntity temp = staffDao.findByName(staffName);
        if (temp != null && temp.getId() != staffEntity.getId()) {
            return CommonUtil.format(4200, " 该用户名已被占用！");
        }

        staffEntity.setName(staffBean.getName());
        staffEntity.setPhone(staffBean.getPhone());
        staffEntity.setEmail(staffBean.getEmail());
        staffEntity.setUpdateTime(new Date());
        //修改
        staffDao.save(staffEntity);
        return CommonUtil.format(2000, "修改成功！");
    }

    @Override
    public String findAllStaffRole() {
        List<StaffRoleEntity> staffRoleEntities = staffDao.findStaffRoles();
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (StaffRoleEntity staffRoleEntity : staffRoleEntities) {
            Map<String, Object> map = new HashMap<>();
            map.put("roleId", staffRoleEntity.getId());
            map.put("roleKey", staffRoleEntity.getRoleKey());
            map.put("roleName", staffRoleEntity.getRoleName());
            resultList.add(map);
        }
        return CommonUtil.format(2000, resultList);
    }


    //删除用户
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public String delete(StaffBean staffBean) {

        //查询数据
        Optional<StaffEntity> optionalStaffEntity = staffDao.findById(staffBean.getId());
        if (optionalStaffEntity == null) {
            return CommonUtil.format(4200, "查无数据！");
        }
        if ("1".equals(optionalStaffEntity.get().getAdminFlag())) {
            return CommonUtil.format(4200, "无法删除超级管理员账号！");
        }
        StaffEntity staffEntity = optionalStaffEntity.get();

        //权限校验
        if (!checkAdmin(staffEntity.getCityEntity().getId(), staffEntity.getStaffRoleEntity())) {
            return CommonUtil.format(4200, " 无权新增用户！");
        }

        staffDao.delete(staffEntity);
        return CommonUtil.format(2000, "删除成功！");
    }


    private Map<String, Object> getStaffEntityMap(StaffEntity staffEntity) {
        Map<String, Object> map = null;
        if (staffEntity != null) {
            map = new HashMap<>();
            map.put("id", staffEntity.getId());
            map.put("name", staffEntity.getName());
            map.put("status", staffEntity.getStatus());
            map.put("adminFlag", staffEntity.getAdminFlag());
            map.put("phone", staffEntity.getPhone());
            map.put("email", staffEntity.getEmail());
            map.put("roleKey", staffEntity.getStaffRoleEntity().getRoleKey());
            map.put("roleId", staffEntity.getStaffRoleEntity().getId());
            map.put("cityName", staffEntity.getCityEntity().getCityName());
            map.put("cityId", staffEntity.getCityEntity().getId());
            map.put("savetime", staffEntity.getSaveTime());
        }
        return map;
    }

    private Boolean checkAdmin(Integer cityId, StaffRoleEntity staffRoleEntity) {
        StaffEntity staffEntity = staffUtil.getCurrentStaff();
        if (staffEntity == null) return false;

        //当前用户角色
        StaffRoleEntity staffRoleEntity2 = staffEntity.getStaffRoleEntity();
        String roleKey2 = staffRoleEntity2.getRoleKey();

        //如果是超级管理员
        if ("1".equals(staffEntity.getAdminFlag())) {
            return true;
        }
        //管理员不能新增同等级城市的管理员
        if ("admin".equals(staffRoleEntity.getRoleKey()) && cityId == staffEntity.getCityEntity().getId()) {
            return false;
        }

        //当前用户所属的城市Id
        List<Integer> cityIds2 = cityDao.findAllChildrenIdBy_nodeIds_cityStatus(staffEntity.getCityEntity().getNodeIds(), Arrays.asList("1"));

        //各个层级的管理员
        if ("admin".equals(roleKey2) && cityIds2.contains(cityId)) {
            return true;
        }
        return false;
    }
}
