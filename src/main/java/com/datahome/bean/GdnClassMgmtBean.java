package com.datahome.bean;

import com.datahome.group.GdnClassMgmtGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

//
@Setter
@Getter
public class GdnClassMgmtBean {
    @NotNull(groups = {GdnClassMgmtGroup.delete.class, GdnClassMgmtGroup.find.class, GdnClassMgmtGroup.update.class})
    private String id;

    @NotNull(groups = {GdnClassMgmtGroup.save.class,GdnClassMgmtGroup.update.class})
    private String gdnGardenid;
    //名称

    private String name;
    //班号

    private String code;
    //年级，词汇表

    private String grade;
    //班级封面（图片路径）

    private String picture;
    //备注

    private String remarks;
    //状态，词汇表

    private String status;

    //创建年份

    private String CJNF;
    //毕业年份

    private String BYNF;
    //是否少数民族双语教学班，词汇表

    private String SSMZSY;
    //少数民族双语教学模式，词汇表

    private String SSMZSYMS;
    //最大班级人数

    private Integer ZDRS;
    //排序号

    private Integer sort;
    //班号 //Todo 有两个班号相同

    private Integer num;
    //昵称

    private String nickName;
    //毕业后班级名称

    private String graduatedName;
    //论坛BBSID

    private String BBSID;
    //网站主页

    private String homePageUrl;
    //栏目Id

    private String ipsCategoryId;
    //论坛信息

    private String BBSAuth;

    private String order;

    //Todo 排序命名重复了，用sorts
    private String sorts;

    private Integer pageSize;

    private Integer pageNumber;
}
