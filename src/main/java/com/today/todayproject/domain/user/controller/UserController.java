package com.today.todayproject.domain.user.controller;

import com.today.todayproject.domain.user.dto.*;
import com.today.todayproject.domain.user.service.UserService;
import com.today.todayproject.global.BaseException;
import com.today.todayproject.global.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * 회원 정보 수정 API
     */
    @PatchMapping("/update")
    public BaseResponse<String> update(
            @RequestPart(required = false) UserUpdateRequestDto userUpdateRequestDto,
            @RequestPart(required = false) MultipartFile profileImg) throws Exception {
        userService.updateUser(userUpdateRequestDto, profileImg);
        return new BaseResponse<>("회원 수정 성공");
    }

    /**
     * 회원 탈퇴 API
     */
    @PostMapping("/withdraw")
    public BaseResponse<String> withdraw(
            @RequestBody UserWithdrawRequestDto userWithdrawRequestDto) throws Exception {
        userService.withdraw(userWithdrawRequestDto);
        return new BaseResponse<>("회원 탈퇴 성공");
    }

    /**
     * 유저 검색 API
     */
    @GetMapping("/search")
    public BaseResponse<UserGetPagingDto> searchUsers(@RequestParam(name = "loginUserId") Long loginUserId,
                                                      @RequestParam(required = false, name = "lastFriendUserId") Long lastFriendUserId,
                                                      @RequestParam(required = false, name = "lastUserId") Long lastUserId,
                                                      @RequestParam("searchUserNickname") String searchUserNickname,
                                                      Pageable pageable) {
        UserSearchDto userSearchDto = new UserSearchDto(loginUserId, lastFriendUserId, lastUserId, searchUserNickname);
        UserGetPagingDto userGetPagingDto = userService.searchUsers(pageable, userSearchDto);
        return new BaseResponse<>(userGetPagingDto);
    }

    /**
     * 이번 달 유저 작물들 조회 API(메인 페이지)
     */
    @GetMapping("/this-month-crops/{userId}")
    public BaseResponse<UserGetThisMonthMyCropDto> getThisMonthMyCrops(
            @PathVariable("userId") Long userId) throws BaseException {
        UserGetThisMonthMyCropDto thisMonthMyCrop = userService.getThisMonthMyCrop(userId);
        return new BaseResponse<>(thisMonthMyCrop);
    }
}
