package com.today.todayproject.domain.user.service;

import com.today.todayproject.domain.user.dto.*;
import com.today.todayproject.global.BaseException;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    Long signUp(UserSignUpRequestDto userSignUpRequestDto, MultipartFile profileImg) throws Exception;

    void updateUser(UserUpdateRequestDto userUpdateRequestDto, MultipartFile profileImg) throws Exception;

    void withdraw(UserWithdrawRequestDto userWithdrawRequestDto) throws Exception;

    UserGetPagingDto searchUsers(Pageable pageable, UserSearchDto userSearchDto);

    UserGetThisMonthMyCropDto getThisMonthMyCrop(Long userId) throws BaseException;

    void initPostWriteCount();

    void initThisMonthHarvestCount();
}
