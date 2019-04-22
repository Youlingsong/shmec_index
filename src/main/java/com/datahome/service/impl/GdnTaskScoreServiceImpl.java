package com.datahome.service.impl;

import com.datahome.bean.GdnTaskScoreBean;
import com.datahome.entity.GdnScoringTaskEntity;
import com.datahome.entity.GdnTaskScoreEntity;
import com.datahome.entity.IndexEntity;
import com.datahome.entity.StaffEntity;
import com.datahome.repository.GdnScoringTaskRepository;
import com.datahome.repository.GdnTaskScoreRepository;
import com.datahome.repository.IndexRepository;
import com.datahome.repository.StaffRepository;
import com.datahome.service.GdnTaskScoreService;
import com.datahome.util.CommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GdnTaskScoreServiceImpl implements GdnTaskScoreService {

	@Resource
	private GdnTaskScoreRepository gdnTaskScoreDao;

	@Resource
	private StaffRepository staffDao;


	@Resource
	private IndexRepository indexDao;

	@Resource
	private GdnScoringTaskRepository gdnScoringTaskDao;

	@Override
	@Transactional
	public String saveScore(List<GdnTaskScoreBean> listGdnTaskScoreBean) {
		for(GdnTaskScoreBean gdnTaskScoreBean:listGdnTaskScoreBean){
			Optional<StaffEntity> optionalStaffEntity = staffDao.findById(gdnTaskScoreBean.getStaffid());
			if (!optionalStaffEntity.isPresent()) {
				return CommonUtil.format(4200, "管理员查无数据！");
			}
			Optional<IndexEntity> optionalIndexEntity = indexDao.findById(gdnTaskScoreBean.getIndexid());
			if (!optionalIndexEntity.isPresent()) {
				return CommonUtil.format(4200, "指标查无数据！");
			}

			Optional<GdnScoringTaskEntity> optionGdnScoringTaskEntity = gdnScoringTaskDao.findById(gdnTaskScoreBean.getScoringtaskid());
			if (!optionGdnScoringTaskEntity.isPresent()) {
				return CommonUtil.format(4200, "打分任务不存在！");
			}
			StaffEntity staffEntity = optionalStaffEntity.get();
			IndexEntity indexEntity = optionalIndexEntity.get();
			GdnScoringTaskEntity gdnScoringTaskEntity = optionGdnScoringTaskEntity.get();

			GdnTaskScoreEntity gdnTaskScoreEntity = new GdnTaskScoreEntity();
			CommonUtil.exchangeObj(gdnTaskScoreBean,gdnTaskScoreEntity);
			gdnTaskScoreEntity.setCreateTime(new Date());
			gdnTaskScoreEntity.setUpdateTime(new Date());
			gdnTaskScoreEntity.setGdnScoringTaskEntity(gdnScoringTaskEntity);
			gdnTaskScoreEntity.setStaffEntity(staffEntity);
			gdnTaskScoreEntity.setIndexEntity(indexEntity);
			gdnTaskScoreDao.save(gdnTaskScoreEntity);
		}
		return CommonUtil.format(2000,"指标打分添加成功");
	}
}
