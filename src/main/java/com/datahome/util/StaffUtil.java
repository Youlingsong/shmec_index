package com.datahome.util;

import com.datahome.entity.StaffEntity;
import com.datahome.repository.StaffRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

/**
 * @Author xl
 * @Description:
 * @Date: Create in 2018/11/12 9:41
 */

@Component
public class StaffUtil {

    @Resource
    private HttpSession session;

    @Resource
    private StaffRepository staffDao;

    public StaffEntity getCurrentStaff() {
        Map<String, Object> staff = (Map<String, Object>) session.getAttribute("staff");
        Optional<StaffEntity> optionalStaffEntity = staffDao.findById(Integer.parseInt(staff.get("id").toString()));
        if (!optionalStaffEntity.isPresent()) {
            return null;
        }
        return optionalStaffEntity.get();
    }
}
