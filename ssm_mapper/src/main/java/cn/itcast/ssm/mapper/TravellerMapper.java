package cn.itcast.ssm.mapper;

import cn.itcast.ssm.domain.Traveller;

import java.util.List;

public interface TravellerMapper {

 public List<Traveller> findById(String ordersId) throws Exception;
}
