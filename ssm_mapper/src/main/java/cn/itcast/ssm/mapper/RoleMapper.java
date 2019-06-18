package cn.itcast.ssm.mapper;

import cn.itcast.ssm.domain.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {

    public List<Role> findById(String userId) throws Exception;
}
