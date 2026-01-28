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

import com.entity.DiscussscenicinfoEntity;
import com.entity.view.DiscussscenicinfoView;

import com.service.DiscussscenicinfoService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;

/**
 * 景点信息评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2025-11-09 18:31:56
 */
@RestController
@RequestMapping("/discussscenicinfo")
public class DiscussscenicinfoController {
    @Autowired
    private DiscussscenicinfoService discussscenicinfoService;


    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussscenicinfoEntity discussscenicinfo,
		HttpServletRequest request){
        EntityWrapper<DiscussscenicinfoEntity> ew = new EntityWrapper<DiscussscenicinfoEntity>();

		PageUtils page = discussscenicinfoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussscenicinfo), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,DiscussscenicinfoEntity discussscenicinfo, 
		HttpServletRequest request){
        EntityWrapper<DiscussscenicinfoEntity> ew = new EntityWrapper<DiscussscenicinfoEntity>();

		PageUtils page = discussscenicinfoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussscenicinfo), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussscenicinfoEntity discussscenicinfo){
       	EntityWrapper<DiscussscenicinfoEntity> ew = new EntityWrapper<DiscussscenicinfoEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussscenicinfo, "discussscenicinfo")); 
        return R.ok().put("data", discussscenicinfoService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussscenicinfoEntity discussscenicinfo){
        EntityWrapper< DiscussscenicinfoEntity> ew = new EntityWrapper< DiscussscenicinfoEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussscenicinfo, "discussscenicinfo")); 
		DiscussscenicinfoView discussscenicinfoView =  discussscenicinfoService.selectView(ew);
		return R.ok("查询景点信息评论表成功").put("data", discussscenicinfoView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussscenicinfoEntity discussscenicinfo = discussscenicinfoService.selectById(id);
        return R.ok().put("data", discussscenicinfo);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussscenicinfoEntity discussscenicinfo = discussscenicinfoService.selectById(id);
        return R.ok().put("data", discussscenicinfo);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscussscenicinfoEntity discussscenicinfo, HttpServletRequest request){
    	discussscenicinfo.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(discussscenicinfo);
        discussscenicinfoService.insert(discussscenicinfo);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscussscenicinfoEntity discussscenicinfo, HttpServletRequest request){
    	discussscenicinfo.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(discussscenicinfo);
        discussscenicinfoService.insert(discussscenicinfo);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody DiscussscenicinfoEntity discussscenicinfo, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussscenicinfo);
        discussscenicinfoService.updateById(discussscenicinfo);//全部更新
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discussscenicinfoService.deleteBatchIds(Arrays.asList(ids));
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
		
		Wrapper<DiscussscenicinfoEntity> wrapper = new EntityWrapper<DiscussscenicinfoEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = discussscenicinfoService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	









}
