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

import com.entity.DiscusstrafficrouteEntity;
import com.entity.view.DiscusstrafficrouteView;

import com.service.DiscusstrafficrouteService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;

/**
 * 交通路线评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2025-11-09 18:31:56
 */
@RestController
@RequestMapping("/discusstrafficroute")
public class DiscusstrafficrouteController {
    @Autowired
    private DiscusstrafficrouteService discusstrafficrouteService;


    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscusstrafficrouteEntity discusstrafficroute,
		HttpServletRequest request){
        EntityWrapper<DiscusstrafficrouteEntity> ew = new EntityWrapper<DiscusstrafficrouteEntity>();

		PageUtils page = discusstrafficrouteService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusstrafficroute), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscusstrafficrouteEntity discusstrafficroute, 
		HttpServletRequest request){
        EntityWrapper<DiscusstrafficrouteEntity> ew = new EntityWrapper<DiscusstrafficrouteEntity>();

		PageUtils page = discusstrafficrouteService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discusstrafficroute), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscusstrafficrouteEntity discusstrafficroute){
       	EntityWrapper<DiscusstrafficrouteEntity> ew = new EntityWrapper<DiscusstrafficrouteEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discusstrafficroute, "discusstrafficroute")); 
        return R.ok().put("data", discusstrafficrouteService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscusstrafficrouteEntity discusstrafficroute){
        EntityWrapper< DiscusstrafficrouteEntity> ew = new EntityWrapper< DiscusstrafficrouteEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discusstrafficroute, "discusstrafficroute")); 
		DiscusstrafficrouteView discusstrafficrouteView =  discusstrafficrouteService.selectView(ew);
		return R.ok("查询交通路线评论表成功").put("data", discusstrafficrouteView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscusstrafficrouteEntity discusstrafficroute = discusstrafficrouteService.selectById(id);
        return R.ok().put("data", discusstrafficroute);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscusstrafficrouteEntity discusstrafficroute = discusstrafficrouteService.selectById(id);
        return R.ok().put("data", discusstrafficroute);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscusstrafficrouteEntity discusstrafficroute, HttpServletRequest request){
    	discusstrafficroute.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(discusstrafficroute);
        discusstrafficrouteService.insert(discusstrafficroute);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscusstrafficrouteEntity discusstrafficroute, HttpServletRequest request){
    	discusstrafficroute.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(discusstrafficroute);
        discusstrafficrouteService.insert(discusstrafficroute);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody DiscusstrafficrouteEntity discusstrafficroute, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discusstrafficroute);
        discusstrafficrouteService.updateById(discusstrafficroute);//全部更新
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discusstrafficrouteService.deleteBatchIds(Arrays.asList(ids));
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
		
		Wrapper<DiscusstrafficrouteEntity> wrapper = new EntityWrapper<DiscusstrafficrouteEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = discusstrafficrouteService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	









}
