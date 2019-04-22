package com.datahome.service;

import com.datahome.bean.GdnScoringTaskBean;

public interface GdnScoringTaskService {


    String save(GdnScoringTaskBean gdnScoringTaskBean);

    String update(GdnScoringTaskBean gdnScoringTaskBean);

    String find(GdnScoringTaskBean gdnScoringTaskBean);

    String finds(GdnScoringTaskBean gdnScoringTaskBean);
}
