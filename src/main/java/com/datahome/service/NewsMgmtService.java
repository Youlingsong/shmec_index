package com.datahome.service;

import com.datahome.bean.NewsMgmtBean;

public interface NewsMgmtService {

    String save(NewsMgmtBean nb);

    String find(NewsMgmtBean nb);

    String finds(NewsMgmtBean nb);

    String updates(NewsMgmtBean nb);

    String findClassesList(NewsMgmtBean nb);

    String delete(NewsMgmtBean nb);

    String saveNewsClasses(NewsMgmtBean nb);

    String findNewsClasses(NewsMgmtBean nb);

    String updateNewsClasses(NewsMgmtBean nb);

    String deleteNewsClasses(NewsMgmtBean nb);
}
