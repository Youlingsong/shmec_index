package com.datahome.service;

import com.datahome.bean.GdnMiddleDataBean;

public interface GdnMiddleDataService {


    String save(GdnMiddleDataBean gdnMiddleDataBean);

    String update(GdnMiddleDataBean gdnMiddleDataBean);

    String find(GdnMiddleDataBean gdnMiddleDataBean);

    String finds(GdnMiddleDataBean gdnMiddleDataBean);
}
