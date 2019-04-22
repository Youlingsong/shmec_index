package com.datahome.service.impl;

import com.datahome.bean.AccountBean;
import com.datahome.entity.AccountEntity;
import com.datahome.repository.AccountRepository;
import com.datahome.service.AccountService;
import com.datahome.util.AccountUtil;
import com.datahome.util.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Author xl
 * @Description: 管理员
 * @Date: Create in 2018/5/8 17:50
 */

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountRepository accountDao;

    @Resource
    private AccountUtil accountUtil;

    @Resource
    private HttpSession session;

    @Override
    public String login(AccountBean accountBean) {
        //用户名密码校验
        AccountEntity accessEntity = accountDao.findByNameAndPassword(accountBean.getName(), CommonUtil.encodeBase64(accountBean.getPassword()));
        if (accessEntity == null) {
            return CommonUtil.format(4200, "用户名或者密码错误！");
        }

        if ("2".equals(accessEntity.getAccountStatus())) {
            return CommonUtil.format(4200, "账号被禁用！");
        }

        //将用户信息存入session
        Map<String, Object> accountMap = new HashMap<>();
        accountMap.put("id", accessEntity.getId());
        accountMap.put("name", accessEntity.getName());
        session.setAttribute("account", accountMap);

        return CommonUtil.format(2000, accountMap);
    }

    @Override
    public String logout(AccountBean accountBean) {
        session.invalidate();
        return CommonUtil.format(2000, "注销成功！");
    }

    @Override
    public String find(AccountBean accountBean) {
        Optional<AccountEntity> optionalAccountEntity = accountDao.findById(accountBean.getId());
        if (optionalAccountEntity == null) {
            return CommonUtil.format(4200, "查无数据！");
        }

        //用户基本信息
        AccountEntity accountEntity = optionalAccountEntity.get();
        Map<String, Object> resultMap = accountUtil.getAccountEntityMap(accountEntity);

        return CommonUtil.format(2000, accountUtil.getCityAndAllowReadIndexs(accountEntity, resultMap));
    }

    // 修改用户密码
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updatePassword(AccountBean accountBean) {
        Object accountMap = session.getAttribute("account");
        if (accountMap == null) {
            return CommonUtil.format(4200, "用户未登录！");
        }
        String name = (String) ((Map<String, Object>) accountMap).get("name");
        AccountEntity accountEntity = accountDao.findByNameAndPassword(name, CommonUtil.encodeBase64(accountBean.getPassword()));
        if (accountEntity == null) {
            return CommonUtil.format(4200, "原密码错误！");
        }

        accountEntity.setPassword(CommonUtil.encodeBase64(accountBean.getNewPassword()));
        accountEntity.setUpdateTime(new Date());

        accountDao.save(accountEntity);

        session.invalidate();

        return CommonUtil.format(2000, "修改成功！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updateInfo(AccountBean accountBean) {

        Optional<AccountEntity> optionalAccountEntity = accountDao.findById(accountBean.getId());
        if (!optionalAccountEntity.isPresent()) {
            return CommonUtil.format(4200, "查无数据！");
        }
        AccountEntity accountEntity = optionalAccountEntity.get();

        //账号检验
        String account = accountBean.getAccount();
        if (!accountEntity.getAccount().equals(account) && accountDao.findByAccount(account) != null) {
            return CommonUtil.format(4200, " 该账号已被占用！");
        }

        accountEntity.setName(accountBean.getName());
        accountEntity.setAccount(accountBean.getAccount());
        accountEntity.setEmail(accountBean.getEmail());
        accountEntity.setPhone(accountBean.getPhone());
        accountEntity.setInstitution(accountBean.getInstitution());
        accountEntity.setJob(accountBean.getJob());
        accountEntity.setUpdateTime(new Date());

        accountDao.save(accountEntity);

        return CommonUtil.format(2000, "修改成功！");
    }
}
