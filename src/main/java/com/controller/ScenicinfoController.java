package com.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import com.service.TicketreserveService;
import org.apache.commons.lang3.StringUtils;
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

import com.entity.ScenicinfoEntity;
import com.entity.view.ScenicinfoView;

import com.service.ScenicinfoService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.service.StoreupService;

/**
 * 景点信息
 * 后端接口
 * @author 
 * @email 
 * @date 2025-11-09 18:31:55
 */
@RestController
@RequestMapping("/scenicinfo")
public class ScenicinfoController {
    @Autowired
    private ScenicinfoService scenicinfoService;
     @Autowired
    private TicketreserveService ticketreserveService;



    @Autowired
    private StoreupService storeupService;

    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ScenicinfoEntity scenicinfo,
		HttpServletRequest request){
        EntityWrapper<ScenicinfoEntity> ew = new EntityWrapper<ScenicinfoEntity>();
        String tableName = request.getSession().getAttribute("tableName").toString();
        if(tableName.equals("merchant")) {
            scenicinfo.setMerchantusername((String)request.getSession().getAttribute("username"));
        }

		PageUtils page = scenicinfoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, scenicinfo), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ScenicinfoEntity scenicinfo, 
		HttpServletRequest request){
        EntityWrapper<ScenicinfoEntity> ew = new EntityWrapper<ScenicinfoEntity>();

		PageUtils page = scenicinfoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, scenicinfo), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ScenicinfoEntity scenicinfo){
       	EntityWrapper<ScenicinfoEntity> ew = new EntityWrapper<ScenicinfoEntity>();
      	ew.allEq(MPUtil.allEQMapPre( scenicinfo, "scenicinfo")); 
        return R.ok().put("data", scenicinfoService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ScenicinfoEntity scenicinfo){
        EntityWrapper< ScenicinfoEntity> ew = new EntityWrapper< ScenicinfoEntity>();
 		ew.allEq(MPUtil.allEQMapPre( scenicinfo, "scenicinfo")); 
		ScenicinfoView scenicinfoView =  scenicinfoService.selectView(ew);
		return R.ok("查询景点信息成功").put("data", scenicinfoView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ScenicinfoEntity scenicinfo = scenicinfoService.selectById(id);
		scenicinfo.setClicknum(scenicinfo.getClicknum()+1);
		scenicinfo.setClicktime(new Date());
        Double rating = ticketreserveService.calculateRating(id);
        scenicinfo.setRating(rating);
		scenicinfoService.updateById(scenicinfo);
        return R.ok().put("data", scenicinfo);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ScenicinfoEntity scenicinfo = scenicinfoService.selectById(id);
		scenicinfo.setClicknum(scenicinfo.getClicknum()+1);
		scenicinfo.setClicktime(new Date());
        Double rating = ticketreserveService.calculateRating(id);
        scenicinfo.setRating(rating);
		scenicinfoService.updateById(scenicinfo);
        return R.ok().put("data", scenicinfo);
    }
    


    /**
     * 赞或踩
     */
    @RequestMapping("/thumbsup/{id}")
    public R vote(@PathVariable("id") String id,String type){
        ScenicinfoEntity scenicinfo = scenicinfoService.selectById(id);
        if(type.equals("1")) {
        	scenicinfo.setThumbsupnum(scenicinfo.getThumbsupnum()+1);
        } else {
        	scenicinfo.setCrazilynum(scenicinfo.getCrazilynum()+1);
        }
        scenicinfoService.updateById(scenicinfo);
        return R.ok("投票成功");
    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ScenicinfoEntity scenicinfo, HttpServletRequest request){
    	scenicinfo.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(scenicinfo);
        scenicinfoService.insert(scenicinfo);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ScenicinfoEntity scenicinfo, HttpServletRequest request){
    	scenicinfo.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(scenicinfo);
        scenicinfoService.insert(scenicinfo);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ScenicinfoEntity scenicinfo, HttpServletRequest request){
        //ValidatorUtils.validateEntity(scenicinfo);
        scenicinfoService.updateById(scenicinfo);//全部更新
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        scenicinfoService.deleteBatchIds(Arrays.asList(ids));
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
		
		Wrapper<ScenicinfoEntity> wrapper = new EntityWrapper<ScenicinfoEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = scenicinfoService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,ScenicinfoEntity scenicinfo, HttpServletRequest request,String pre){
        EntityWrapper<ScenicinfoEntity> ew = new EntityWrapper<ScenicinfoEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicknum");
        params.put("order", "desc");
		PageUtils page = scenicinfoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, scenicinfo), params), params));
        return R.ok().put("data", page);
    }









}
