package com.datahome.service;

import com.datahome.bean.NewsBean;

public interface NewsService {
    
    String find(NewsBean newsBean);

    String finds(NewsBean newsBean);

    String findNewsClasses(NewsBean newsBean);
}
