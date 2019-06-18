package cn.itcast.ssm.mapper;

import cn.itcast.ssm.domain.Member;

public interface MemberMapper {

    public Member findById(String id) throws Exception;
}
