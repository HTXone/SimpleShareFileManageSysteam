package com.Service;

import com.entity.History;

import java.util.List;

public interface HistoryService {

    boolean AddNewHistory();

    History SearchHistory();

    List<History> SearchHistoryByUid();

    List<History> SearchHistoryByFileName();

    boolean HistoryDelete();

}
