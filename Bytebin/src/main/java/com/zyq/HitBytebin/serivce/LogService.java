package com.zyq.HitBytebin.serivce;

import com.zyq.HitBytebin.model.pojo.WebLog;

public interface LogService {
    void addLogToQueue(WebLog webLog);

    void saveLogTask();

    void delInfoRecordTask();
}
