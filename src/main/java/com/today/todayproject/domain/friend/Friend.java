package com.today.todayproject.domain.friend;

import com.today.todayproject.domain.BaseTimeEntity;
import com.today.todayproject.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Friend extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String nickname; // 닉네임

    private String profileImgUrl; // 프로필 사진

   private String recentFeeling; // 최근 감정 상태


    /**
     * 연관관계 메소드
     */
    public void confirmUser(User user) {
        this.user = user;
        user.getFriendList().add(this);
    }
}
