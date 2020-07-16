package com.itguigu.ucenter.mapper;

import com.itguigu.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author shenqi
 * @since 2020-07-01
 */
@Mapper
public interface MemberMapper extends BaseMapper<Member> {

    Integer getUserNum(String date);
}
