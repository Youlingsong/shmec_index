package com.datahome.service.impl;

import com.datahome.bean.GdnScoringTaskBean;
import com.datahome.entity.GdnScoringTaskEntity;
import com.datahome.entity.IndexEntity;
import com.datahome.entity.StaffEntity;
import com.datahome.repository.GdnScoringTaskRepository;
import com.datahome.repository.IndexRepository;
import com.datahome.repository.StaffRepository;
import com.datahome.service.GdnScoringTaskService;
import com.datahome.util.CommonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GdnScoringTaskServiceImpl implements GdnScoringTaskService {

	@Resource
	private GdnScoringTaskRepository gdnScoringTaskDao;

	@Resource
	private IndexRepository indexDao;
	
	@Resource
	private StaffRepository staffDao;

	@Override
	public String save(GdnScoringTaskBean gdnScoringTaskBean) {
		Optional<IndexEntity> optionIndexEntity = indexDao.findById(gdnScoringTaskBean.getIndexid());
		if (!optionIndexEntity.isPresent()) {
			return CommonUtil.format(4200, "打分任务所选指标不存在！");
		}
		IndexEntity indexEntity = optionIndexEntity.get();

		Optional<StaffEntity> optionStaffEntity = staffDao.findById(gdnScoringTaskBean.getStaffid());
		if (!optionStaffEntity.isPresent()) {
			return CommonUtil.format(4200, "打分任务所选管理员不存在！");
		}

		StaffEntity staffEntity = optionStaffEntity.get();

		GdnScoringTaskEntity gdnScoringTaskEntity = new GdnScoringTaskEntity();
		CommonUtil.exchangeObj(gdnScoringTaskBean,gdnScoringTaskEntity);
		gdnScoringTaskEntity.setIndexEntity(indexEntity);
		gdnScoringTaskEntity.setStaffEntity(staffEntity);
		gdnScoringTaskEntity.setUpdateTime(new Date());
		gdnScoringTaskEntity.setCreateTime(new Date());
		gdnScoringTaskDao.save(gdnScoringTaskEntity);
		return CommonUtil.format(2000, "打分任务添加成功！");
	}

	@Override
	public String update(GdnScoringTaskBean gdnScoringTaskBean) {
		Optional<GdnScoringTaskEntity> optionGdnScoringTaskEntity = gdnScoringTaskDao.findById(gdnScoringTaskBean.getId());
		if (!optionGdnScoringTaskEntity.isPresent()) {
			return CommonUtil.format(4200, "打分任务不存在！");
		}

		Optional<IndexEntity> optionIndexEntity = indexDao.findById(gdnScoringTaskBean.getIndexid());
		if (!optionIndexEntity.isPresent()) {
			return CommonUtil.format(4200, "打分任务所选指标不存在！");
		}
		IndexEntity indexEntity = optionIndexEntity.get();

		Optional<StaffEntity> optionStaffEntity = staffDao.findById(gdnScoringTaskBean.getStaffid());
		if (!optionStaffEntity.isPresent()) {
			return CommonUtil.format(4200, "打分任务所选管理员不存在！");
		}
		StaffEntity staffEntity = optionStaffEntity.get();

		GdnScoringTaskEntity gdnScoringTaskEntity = optionGdnScoringTaskEntity.get();
		CommonUtil.exchangeObj(gdnScoringTaskBean,gdnScoringTaskEntity);
		gdnScoringTaskEntity.setIndexEntity(indexEntity);
		gdnScoringTaskEntity.setStaffEntity(staffEntity);
		gdnScoringTaskEntity.setUpdateTime(new Date());
		gdnScoringTaskDao.save(gdnScoringTaskEntity);
		return CommonUtil.format(2000, "打分任务更新成功！");
	}

	@Override
	public String find(GdnScoringTaskBean gdnScoringTaskBean) {
		Optional<GdnScoringTaskEntity> optionGdnScoringTaskEntity = gdnScoringTaskDao.findById(gdnScoringTaskBean.getId());
		if (!optionGdnScoringTaskEntity.isPresent()) {
			return CommonUtil.format(4200, "打分任务不存在！");
		}
		GdnScoringTaskEntity gdnScoringTaskEntity = optionGdnScoringTaskEntity.get();
		return CommonUtil.format(2000, gdnScoringTaskEntity);
	}

	@Override
	public String finds(GdnScoringTaskBean gdnScoringTaskBean) {
		//jpa模糊查询
		List<GdnScoringTaskEntity> gdnMiddleDataEntities = gdnScoringTaskDao.findByNameLike("%"+gdnScoringTaskBean.getName()+"%");
		return CommonUtil.format(2000, gdnMiddleDataEntities);
	}
}
