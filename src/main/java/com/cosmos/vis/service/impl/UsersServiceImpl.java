package com.cosmos.vis.service.impl;

import com.cosmos.vis.pojo.Users;
import com.cosmos.vis.mapper.UsersMapper;
import com.cosmos.vis.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CosmosBackpacker
 * @since 2024-10-20
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
