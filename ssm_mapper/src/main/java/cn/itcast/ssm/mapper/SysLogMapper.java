package cn.itcast.ssm.mapper;

import cn.itcast.ssm.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysLogMapper {
    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public void save(SysLog sysLog)throws Exception;
    @Select("select * from sysLog")
    List<SysLog> findAll()throws Exception;

}
