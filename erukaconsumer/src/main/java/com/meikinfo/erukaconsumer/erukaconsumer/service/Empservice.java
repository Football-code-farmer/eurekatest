package com.meikinfo.erukaconsumer.erukaconsumer.service;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * description:
 * empservice
 *
 * @author hongjw
 * @create 2020-09-30 14:22
 */
@Service
public class Empservice {
    @Autowired
    EmpServiceRemote empServiceRemote;


    @LcnTransaction//分布式事务
    @Transactional //本地事务
    public void txmanagerTest() {
        empServiceRemote.txmanagerTest();
//        System.out.println(1/0);
    }
}
