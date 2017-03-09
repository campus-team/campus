package com.campus.foundation.service.impl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campus.core.service.impl.CommonServiceImpl;
import com.campus.foundation.domain.User;
import com.campus.foundation.service.IUserService;

@Service
public class UserServiceImpl extends CommonServiceImpl<User> implements IUserService{

}
