package cn.itcast.ssm.service.impl;

import cn.itcast.ssm.domain.SysLog;
import cn.itcast.ssm.mapper.SysLogMapper;
import cn.itcast.ssm.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;
    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogMapper.save(sysLog);
    }

    @Override
    public List<SysLog> findAll() throws Exception {
        return sysLogMapper.findAll();
    }
}
