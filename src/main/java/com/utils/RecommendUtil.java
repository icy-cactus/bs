package com.utils;

import com.entity.StoreupEntity;
import java.util.*;
import java.util.stream.Collectors;

public class RecommendUtil {
    /**
     * 基于用户的协同过滤算法
     * @param allStoreups 所有用户的收藏数据
     * @param currentUserId 当前登录用户ID
     * @return 推荐的景点ID列表
     */
    public static List<Long> userBasedRecommend(List<StoreupEntity> allStoreups, Long currentUserId) {
        // 1. 构建用户-项目映射表 (UserId -> Set of ScenicIds)
        Map<Long, Set<Long>> userItemsMap = new HashMap<>();
        for (StoreupEntity store : allStoreups) {
            userItemsMap.computeIfAbsent(store.getUserid(), k -> new HashSet<>()).add(store.getRefid());
        }

        Set<Long> currentUserItems = userItemsMap.getOrDefault(currentUserId, new HashSet<>());
        if (currentUserItems.isEmpty()) return new ArrayList<>();

        // 2. 计算当前用户与其他用户的相似度 (简单交集法)
        Map<Long, Double> userSimilarities = new HashMap<>();
        for (Map.Entry<Long, Set<Long>> entry : userItemsMap.entrySet()) {
            Long otherUserId = entry.getKey();
            if (otherUserId.equals(currentUserId)) continue;

            Set<Long> otherUserItems = entry.getValue();
            Set<Long> intersection = new HashSet<>(currentUserItems);
            intersection.retainAll(otherUserItems); // 计算交集

            if (intersection.size() > 0) {
                // 相似度 = 交集大小 / (当前用户收藏数 * 其他用户收藏数)的平方根 (余弦相似度简化版)
                double similarity = (double) intersection.size() / Math.sqrt(currentUserItems.size() * otherUserItems.size());
                userSimilarities.put(otherUserId, similarity);
            }
        }

        // 3. 找出最相似的用户，并推荐其收藏过但当前用户未收藏的景点
        return userSimilarities.entrySet().stream()
                .sorted(Map.Entry.<Long, Double>comparingByValue().reversed()) // 按相似度降序
                .limit(5) // 取前5名相似用户
                .flatMap(entry -> userItemsMap.get(entry.getKey()).stream())
                .filter(scenicId -> !currentUserItems.contains(scenicId)) // 过滤掉已收藏的
                .distinct()
                .collect(Collectors.toList());
    }
}