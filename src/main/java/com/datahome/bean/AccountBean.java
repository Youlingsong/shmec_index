package com.datahome.bean;

import com.datahome.group.AccountGroup;
import com.datahome.util.RegexUtil;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/5/10 10:29
 */
@Setter
@Getter
public class AccountBean {

    @NotNull(groups = {AccountGroup.find.class, AccountGroup.updateInfo.class})
    private Integer id;

    @NotNull(groups = {AccountGroup.updateInfo.class, AccountGroup.login.class})
    private String name;

    @NotNull(groups = {AccountGroup.login.class})
    private String password;

    @NotNull(groups = {AccountGroup.updateInfo.class})
    private String account;

    @NotNull(groups = {AccountGroup.updatePassword.class})
    private String newPassword;

    @Pattern(regexp = RegexUtil.PHONE)
    private String phone;

    @Email
    private String email;

    @Size(max = 200)
    private String institution;

    @Size(max = 200)
    private String job;

}
