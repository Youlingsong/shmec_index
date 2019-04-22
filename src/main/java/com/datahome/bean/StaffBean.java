package com.datahome.bean;

import com.datahome.group.StaffGroup;
import com.datahome.util.RegexUtil;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/10/11 17:57
 */
@Getter
@Setter
public class StaffBean {

    @NotNull(groups = {StaffGroup.find.class, StaffGroup.initPassword.class, StaffGroup.updateInfo.class, StaffGroup.delete.class, StaffGroup.updateMyselfInfo.class})
    private Integer id;

    @NotNull(groups = {StaffGroup.save.class, StaffGroup.updateInfo.class, StaffGroup.updatePassword.class, StaffGroup.login.class, StaffGroup.updateMyselfInfo.class})
    private String name;

    @NotNull(groups = {StaffGroup.save.class, StaffGroup.login.class, StaffGroup.updatePassword.class})
    private String password;

    @NotNull(groups = {StaffGroup.updatePassword.class})
    private String newPassword;

    @NotNull(groups = {StaffGroup.save.class, StaffGroup.updateInfo.class})
    @Pattern(regexp = RegexUtil.COMMON_STATUS_CODE)
    private String status;

    @Pattern(regexp = RegexUtil.PHONE)
    private String phone;

    @Email
    private String email;

    @NotNull(groups = {StaffGroup.save.class, StaffGroup.updateInfo.class})
    private Integer roleId;

    @NotNull(groups = {StaffGroup.save.class, StaffGroup.updateInfo.class})
    private Integer cityId;

}
