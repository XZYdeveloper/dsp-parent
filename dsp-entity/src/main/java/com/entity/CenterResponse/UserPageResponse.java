package com.entity.CenterResponse;

/**
 * @author yangkun
 * @date 2020/2/9
 * 我的页面-自定义类
 */
public class UserPageResponse {
    private Long userId;
    private String username;
    private String userPic;
    //待评价商品数
    private Long goodsCommentNumber;
    //待收货商品数
    private Long goodsReceiveNumber;
    //收藏商品的个数
    private Long goodsFollowNumber;
    //关注种草的个数
    private Long preferNumber;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public Long getGoodsCommentNumber() {
        return goodsCommentNumber;
    }

    public void setGoodsCommentNumber(Long goodsCommentNumber) {
        this.goodsCommentNumber = goodsCommentNumber;
    }

    public Long getGoodsReceiveNumber() {
        return goodsReceiveNumber;
    }

    public void setGoodsReceiveNumber(Long goodsReceiveNumber) {
        this.goodsReceiveNumber = goodsReceiveNumber;
    }

    public Long getGoodsFollowNumber() {
        return goodsFollowNumber;
    }

    public void setGoodsFollowNumber(Long goodsFollowNumber) {
        this.goodsFollowNumber = goodsFollowNumber;
    }

    public Long getPreferNumber() {
        return preferNumber;
    }

    public void setPreferNumber(Long preferNumber) {
        this.preferNumber = preferNumber;
    }
}
