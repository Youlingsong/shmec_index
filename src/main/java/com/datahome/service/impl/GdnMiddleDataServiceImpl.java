package com.datahome.service.impl;

import com.datahome.bean.GdnMiddleDataBean;
import com.datahome.entity.GdnMiddleDataEntity;
import com.datahome.repository.GdnMiddleDataRepository;
import com.datahome.service.GdnMiddleDataService;
import com.datahome.util.CommonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GdnMiddleDataServiceImpl implements GdnMiddleDataService {

	@Resource
	private GdnMiddleDataRepository gdnMiddleDataDao;

	@Override
	public String save(GdnMiddleDataBean gdnMiddleDataBean) {
		GdnMiddleDataEntity gdnMiddleDataEntity = new GdnMiddleDataEntity();
		CommonUtil.exchangeObj(gdnMiddleDataBean,gdnMiddleDataEntity);
		gdnMiddleDataEntity.setCreateTime(new Date());
		gdnMiddleDataEntity.setUpdateTime(new Date());
		gdnMiddleDataDao.save(gdnMiddleDataEntity);
		return CommonUtil.format(2000, "中间数添加成功！");
	}

	@Override
	public String update(GdnMiddleDataBean gdnMiddleDataBean) {
		Optional<GdnMiddleDataEntity> optionGdnMiddleDataEntity = gdnMiddleDataDao.findById(gdnMiddleDataBean.getId());
		if (!optionGdnMiddleDataEntity.isPresent()) {
			return CommonUtil.format(4200, "中间数不存在");
		}
		GdnMiddleDataEntity gdnMiddleDataEntity = optionGdnMiddleDataEntity.get();
		CommonUtil.exchangeObj(gdnMiddleDataBean,gdnMiddleDataEntity);
		gdnMiddleDataEntity.setUpdateTime(new Date());
		gdnMiddleDataDao.save(gdnMiddleDataEntity);
		return CommonUtil.format(2000, "中间数更新成功！");
	}

	@Override
	public String find(GdnMiddleDataBean gdnMiddleDataBean) {
		Optional<GdnMiddleDataEntity> optionGdnMiddleDataEntity = gdnMiddleDataDao.findById(gdnMiddleDataBean.getId());
		if (!optionGdnMiddleDataEntity.isPresent()) {
			return CommonUtil.format(4200, "中间数不存在");
		}
		GdnMiddleDataEntity gdnMiddleDataEntity = optionGdnMiddleDataEntity.get();
		return CommonUtil.format(2000, gdnMiddleDataEntity);
	}

	@Override
	public String finds(GdnMiddleDataBean gdnMiddleDataBean) {
		//jpa模糊查询
		List<GdnMiddleDataEntity> gdnMiddleDataEntities = gdnMiddleDataDao.findByRemakeLike("%"+gdnMiddleDataBean.getRemake()+"%");
		return CommonUtil.format(2000, gdnMiddleDataEntities);
	}
}
