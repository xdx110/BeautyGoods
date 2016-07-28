package com.xdx.dllo.beautygoodsdemo.goods;

/**
 * 获得喜欢和不喜欢的百分比
 * Created by Muguoqiang on 16/7/26.
 */
public class GetPercentage {
    public static double getLikeHigh(double likeNum, double uLikeNum) {
        double likeHigh = (likeNum / (likeNum + uLikeNum)) * 500;
        return likeHigh;
    }

    public static double getNoLikeCount(double likeNum, double uLikeNum) {
        double noLike = (uLikeNum / (likeNum + uLikeNum)) * 100;

        return noLike;
    }

    public static double getNoLikeHigh(double likeNum, double uLikeNum) {
        double noLikeHigh = (uLikeNum / (likeNum + uLikeNum)) * 500;
        return noLikeHigh;
    }

    public static double getLikeCount(double likeNum, double uLikeNum) {
        double LikeCount = (likeNum / (likeNum + uLikeNum)) * 100;

        return LikeCount;
    }
}

