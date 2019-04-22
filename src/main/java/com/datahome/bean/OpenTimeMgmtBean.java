package com.datahome.bean;

import com.datahome.group.OpenTimeMgmtGroup;
import com.datahome.util.RegexUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/11/16 14:47
 */

@Setter
@Getter
public class OpenTimeMgmtBean {

    @NotNull(groups = {OpenTimeMgmtGroup.update.class})
    private Integer id;

    @NotNull(groups = {OpenTimeMgmtGroup.update.class})
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @NotNull(groups = {OpenTimeMgmtGroup.update.class})
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @NotNull(groups = {OpenTimeMgmtGroup.update.class})
    @Pattern(regexp = RegexUtil.COMMON_STATUS_CODE)
    private String status;

}
