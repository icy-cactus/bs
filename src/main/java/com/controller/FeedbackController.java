package com.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.FeedbackEntity;
import com.entity.view.FeedbackView;

import com.service.FeedbackService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;

/**
 * 反馈意见
 * 后端接口
 * @author 
 * @email 
 * @date 2025-11-28 00:27:23
 */
@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;


    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,FeedbackEntity feedback,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("users")) {
			feedback.setUserid((String)request.getSession().getAttribute("id"));
		}
        EntityWrapper<FeedbackEntity> ew = new EntityWrapper<FeedbackEntity>();

		PageUtils page = feedbackService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, feedback), params), params));

        return R.ok().put("data", page);
    }

    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,FeedbackEntity feedback,
		HttpServletRequest request){
        EntityWrapper<FeedbackEntity> ew = new EntityWrapper<FeedbackEntity>();

        System.out.println(params);
		PageUtils page = feedbackService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, feedback), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( FeedbackEntity feedback){
       	EntityWrapper<FeedbackEntity> ew = new EntityWrapper<FeedbackEntity>();
      	ew.allEq(MPUtil.allEQMapPre( feedback, "feedback"));
        return R.ok().put("data", feedbackService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(FeedbackEntity feedback){
        EntityWrapper< FeedbackEntity> ew = new EntityWrapper< FeedbackEntity>();
 		ew.allEq(MPUtil.allEQMapPre( feedback, "feedback"));
		FeedbackView feedbackView =  feedbackService.selectView(ew);
		return R.ok("查询反馈意见成功").put("data", feedbackView);
    }

    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        FeedbackEntity feedback = feedbackService.selectById(id);
        return R.ok().put("data", feedback);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        FeedbackEntity feedback = feedbackService.selectById(id);
        return R.ok().put("data", feedback);
    }




    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody FeedbackEntity feedback, HttpServletRequest request){
        feedback.setPicture(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue()+"");
        feedback.setFeedbacktime(new Date());

        feedbackService.insert(feedback);
        return R.ok();
    }

    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody FeedbackEntity feedback, HttpServletRequest request){
    	feedback.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(feedback);
        feedbackService.insert(feedback);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody FeedbackEntity feedback, HttpServletRequest request){
        //ValidatorUtils.validateEntity(feedback);
        feedbackService.updateById(feedback);//全部更新
        return R.ok();
    }




    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        feedbackService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 提醒接口
     */
	@RequestMapping("/remind/{columnName}/{type}")
	public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request,
						 @PathVariable("type") String type,@RequestParam Map<String, Object> map) {
		map.put("column", columnName);
		map.put("type", type);

		if(type.equals("2")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date remindStartDate = null;
			Date remindEndDate = null;
			if(map.get("remindstart")!=null) {
				Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH,remindStart);
				remindStartDate = c.getTime();
				map.put("remindstart", sdf.format(remindStartDate));
			}
			if(map.get("remindend")!=null) {
				Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH,remindEnd);
				remindEndDate = c.getTime();
				map.put("remindend", sdf.format(remindEndDate));
			}
		}

		Wrapper<FeedbackEntity> wrapper = new EntityWrapper<FeedbackEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("users")) {
			wrapper.eq("userid", (String)request.getSession().getAttribute("username"));
		}

		int count = feedbackService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	









}
