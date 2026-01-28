package com.controller;

import java.util.*;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.ScenicinfoEntity;
import com.service.ScenicinfoService;
import com.utils.PageUtils;
import com.utils.R;

/**
 * 智能路线规划
 * 后端接口
 * @author
 * @date 2025-11-09
 */
@RestController
@RequestMapping("/smartroute")
public class SmartRouteController {

    @Autowired
    private ScenicinfoService scenicinfoService;

    /**
     * 智能路线规划接口
     * @param startAddress 起始地址
     * @param endAddress 目的地址
     * @param days 旅游天数
     * @param travelType 旅游方式（人文景观、自然景观）
     * @return 路线规划结果
     */
    @IgnoreAuth
    @RequestMapping(value = "/plan", method = RequestMethod.GET)
    public R planRoute(
            @RequestParam(required = false) String startAddress,
            @RequestParam(required = false) String endAddress,
            @RequestParam Integer days,
            @RequestParam String travelType,
            HttpServletRequest request) {

        Map<String, Object> result = new HashMap<>();
        List<ScenicinfoEntity> scenicList = new ArrayList<>();

        // 根据旅游类型获取景点列表
        if ("人文景观".equals(travelType)) {
            // 人文景观：筛选博物馆、古建筑、文化遗址等
            EntityWrapper<ScenicinfoEntity> ew = new EntityWrapper<>();
            if (startAddress != null && !startAddress.trim().isEmpty()) {
                ew.like("address", startAddress);
            } else if (endAddress != null && !endAddress.trim().isEmpty()) {
                ew.like("address", endAddress);
            }
            ew.like("scenicname", "博物馆")
              .or("scenicname", "故宫")
              .or("scenicname", "寺庙")
              .or("scenicname", "古镇")
              .or("introduce", "文化")
              .or("introduce", "历史")
              .or("introduce", "人文")
              .or("introduce", "古迹");
            ew.orderBy("clicknum", false);
            scenicList = scenicinfoService.selectList(ew);
        } else if ("自然景观".equals(travelType)) {
            // 自然景观：筛选山、水、公园等
            EntityWrapper<ScenicinfoEntity> ew = new EntityWrapper<>();
            if (startAddress != null && !startAddress.trim().isEmpty()) {
                ew.like("address", startAddress);
            } else if (endAddress != null && !endAddress.trim().isEmpty()) {
                ew.like("address", endAddress);
            }
            ew.like("scenicname", "山")
              .or("scenicname", "公园")
              .or("scenicname", "湖")
              .or("scenicname", "河")
              .or("introduce", "自然")
              .or("introduce", "风景")
              .or("introduce", "景观");
            ew.orderBy("clicknum", false);
            scenicList = scenicinfoService.selectList(ew);
        } else {
            // 综合体验 - 按点击量排序获取热门景点
            EntityWrapper<ScenicinfoEntity> ew = new EntityWrapper<>();
            if (startAddress != null && !startAddress.trim().isEmpty()) {
                ew.like("address", startAddress);
            } else if (endAddress != null && !endAddress.trim().isEmpty()) {
                ew.like("address", endAddress);
            }
            ew.orderBy("clicknum", false);
            scenicList = scenicinfoService.selectList(ew);
        }

        // 如果筛选结果不足，添加更多热门景点
        int scenicLimit = days * 2; // 每天推荐2个景点
        if (scenicList.size() < scenicLimit) {
            // 获取所有热门景点
            EntityWrapper<ScenicinfoEntity> allEw = new EntityWrapper<>();
            allEw.orderBy("clicknum", false);
            List<ScenicinfoEntity> allList = scenicinfoService.selectList(allEw);

            // 补充景点到足够的数量
            for (ScenicinfoEntity item : allList) {
                if (scenicList.size() >= scenicLimit) break;
                boolean exists = false;
                for (ScenicinfoEntity existing : scenicList) {
                    if (existing.getId().equals(item.getId())) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    scenicList.add(item);
                }
            }
        }

        // 根据天数限制景点数量
        if (scenicList.size() > scenicLimit) {
            scenicList = scenicList.subList(0, scenicLimit);
        }

        // 生成结果
        result.put("routeSummary", createRouteSummary(startAddress, endAddress, days, travelType, scenicList));
        result.put("dailyPlan", createDailyPlan(scenicList, days));
        result.put("allScenics", scenicList);

        return R.ok().put("data", result);
    }

    /**
     * 创建路线总览
     */
    private Map<String, Object> createRouteSummary(String startAddress, String endAddress, Integer days, String travelType, List<ScenicinfoEntity> scenics) {
        Map<String, Object> routeSummary = new HashMap<>();
        routeSummary.put("startAddress", startAddress != null && !startAddress.trim().isEmpty() ? startAddress : "您的出发地");
        routeSummary.put("endAddress", endAddress != null && !endAddress.trim().isEmpty() ? endAddress : "目的地");
        routeSummary.put("days", days);
        routeSummary.put("travelType", travelType);
        routeSummary.put("totalScenics", scenics.size());
        routeSummary.put("estimatedBudget", calculateBudget(scenics, days));
        return routeSummary;
    }

    /**
     * 生成每日行程
     */
    private List<Map<String, Object>> createDailyPlan(List<ScenicinfoEntity> scenicList, int days) {
        List<Map<String, Object>> dailyPlan = new ArrayList<>();

        for (int day = 1; day <= days; day++) {
            Map<String, Object> dayPlan = new HashMap<>();
            dayPlan.put("day", day);
            dayPlan.put("title", "第" + day + "天");

            // 分配景点
            List<ScenicinfoEntity> dayScenics = new ArrayList<>();
            int startIndex = (day - 1) * 2;
            for (int i = 0; i < 2 && startIndex + i < scenicList.size(); i++) {
                dayScenics.add(scenicList.get(startIndex + i));
            }
            dayPlan.put("scenics", dayScenics);

            dailyPlan.add(dayPlan);
        }

        return dailyPlan;
    }

    /**
     * 计算预估费用
     */
    private Map<String, Object> calculateBudget(List<ScenicinfoEntity> scenics, int days) {
        Map<String, Object> budget = new HashMap<>();

        // 计算门票费用
        int ticketCost = 0;
        for (ScenicinfoEntity scenic : scenics) {
            if (scenic.getPrice() != null) {
                ticketCost += scenic.getPrice();
            }
        }

        // 计算住宿费用（假设平均300元/晚）
        int hotelCost = days > 1 ? 300 * (days - 1) : 0;

        // 计算餐饮费用（假设每天150元）
        int mealCost = days * 150;

        // 计算总费用
        int totalCost = ticketCost + hotelCost + mealCost;

        budget.put("ticketCost", ticketCost);
        budget.put("hotelCost", hotelCost);
        budget.put("mealCost", mealCost);
        budget.put("totalCost", totalCost);
        budget.put("description", "预估费用仅供参考，实际费用以当地消费为准");

        return budget;
    }

    /**
     * 获取热门景点推荐
     */
    @IgnoreAuth
    @RequestMapping("/hotScenics")
    public R getHotScenics(@RequestParam Map<String, Object> params) {
        params.put("sort", "clicknum");
        params.put("order", "desc");
        if (params.get("limit") == null) {
            params.put("limit", 10);
        }

        EntityWrapper<ScenicinfoEntity> ew = new EntityWrapper<>();
        PageUtils page = scenicinfoService.queryPage(params, ew);

        return R.ok().put("data", page);
    }

    /**
     * 根据类型搜索景点
     */
    @IgnoreAuth
    @RequestMapping("/searchByType")
    public R searchByType(@RequestParam String type) {
        EntityWrapper<ScenicinfoEntity> ew = new EntityWrapper<>();

        if ("人文".equals(type) || "人文景观".equals(type)) {
            ew.like("introduce", "文化")
              .or("introduce", "历史")
              .or("introduce", "博物馆")
              .or("introduce", "古迹")
              .or("scenicname", "博物馆")
              .or("scenicname", "故宫")
              .or("scenicname", "寺庙");
        } else if ("自然".equals(type) || "自然景观".equals(type)) {
            ew.like("introduce", "自然")
              .or("introduce", "风景")
              .or("introduce", "公园")
              .or("introduce", "山")
              .or("scenicname", "公园")
              .or("scenicname", "山")
              .or("scenicname", "湖");
        }

        ew.orderBy("clicknum", false);
        List<ScenicinfoEntity> list = scenicinfoService.selectList(ew);

        return R.ok().put("data", list);
    }
}
