package com.datahome.bean;

import com.datahome.group.AccountMgmtGroup;
import com.datahome.util.RegexUtil;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/5/10 10:29
 */
@Setter
@Getter
public class AccountMgmtBean {


    @NotNull(groups = {AccountMgmtGroup.find.class, AccountMgmtGroup.delete.class, AccountMgmtGroup.updateInfo.class, AccountMgmtGroup.initPassword.class})
    private Integer id;

    @NotNull(groups = {AccountMgmtGroup.save.class, AccountMgmtGroup.updateInfo.class})
    private String name;

    @NotNull(groups = {AccountMgmtGroup.save.class})
    private String password;

    @NotNull(groups = {AccountMgmtGroup.save.class, AccountMgmtGroup.updateInfo.class})
    private String account;

    @Pattern(regexp = RegexUtil.PHONE)
    private String phone;

    @Email
    private String email;

    @Size(max = 200)
    private String institution;

    @Size(max = 200)
    private String job;

    @NotNull(groups = {AccountMgmtGroup.save.class, AccountMgmtGroup.updateInfo.class})
    @Pattern(regexp = RegexUtil.COMMON_STATUS_CODE)
    private String accountStatus;

    @Size(min = 1, max = 1000)
    private List<Integer> cityIds;

    @Size(min = 1, max = 1000)
    private List<Integer> allowReadIndexs;

    private String order;

    private String sort;

    private Integer pageSize;

    private Integer pageNumber;

}
