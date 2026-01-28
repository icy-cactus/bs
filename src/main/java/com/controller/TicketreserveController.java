package com.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
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

import com.entity.TicketreserveEntity;
import com.entity.view.TicketreserveView;

import com.service.TicketreserveService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;

/**
 * 门票预订
 * 后端接口
 * @author 
 * @email 
 * @date 2025-11-09 18:31:55
 */
@RestController
@RequestMapping("/ticketreserve")
public class TicketreserveController {
    @Autowired
    private TicketreserveService ticketreserveService;


    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,TicketreserveEntity ticketreserve,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("users")) {
			ticketreserve.setUsername((String)request.getSession().getAttribute("username"));
		}
        if(tableName.equals("merchant")) {
			ticketreserve.setMerchantusername((String)request.getSession().getAttribute("username"));
		}
        EntityWrapper<TicketreserveEntity> ew = new EntityWrapper<TicketreserveEntity>();

		PageUtils page = ticketreserveService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ticketreserve), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,TicketreserveEntity ticketreserve, 
		HttpServletRequest request){
        EntityWrapper<TicketreserveEntity> ew = new EntityWrapper<TicketreserveEntity>();

		PageUtils page = ticketreserveService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, ticketreserve), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( TicketreserveEntity ticketreserve){
       	EntityWrapper<TicketreserveEntity> ew = new EntityWrapper<TicketreserveEntity>();
      	ew.allEq(MPUtil.allEQMapPre( ticketreserve, "ticketreserve")); 
        return R.ok().put("data", ticketreserveService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(TicketreserveEntity ticketreserve){
        EntityWrapper< TicketreserveEntity> ew = new EntityWrapper< TicketreserveEntity>();
 		ew.allEq(MPUtil.allEQMapPre( ticketreserve, "ticketreserve")); 
		TicketreserveView ticketreserveView =  ticketreserveService.selectView(ew);
		return R.ok("查询门票预订成功").put("data", ticketreserveView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        TicketreserveEntity ticketreserve = ticketreserveService.selectById(id);
        return R.ok().put("data", ticketreserve);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        TicketreserveEntity ticketreserve = ticketreserveService.selectById(id);
        return R.ok().put("data", ticketreserve);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody TicketreserveEntity ticketreserve, HttpServletRequest request){
    	ticketreserve.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(ticketreserve);
        ticketreserveService.insert(ticketreserve);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody TicketreserveEntity ticketreserve, HttpServletRequest request){
    	ticketreserve.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(ticketreserve);
        ticketreserveService.insert(ticketreserve);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody TicketreserveEntity ticketreserve, HttpServletRequest request){
        //ValidatorUtils.validateEntity(ticketreserve);
        ticketreserveService.updateById(ticketreserve);//全部更新
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        ticketreserveService.deleteBatchIds(Arrays.asList(ids));
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
		
		Wrapper<TicketreserveEntity> wrapper = new EntityWrapper<TicketreserveEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}

		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("users")) {
			wrapper.eq("username", (String)request.getSession().getAttribute("username"));
		}

		int count = ticketreserveService.selectCount(wrapper);
		return R.ok().put("count", count);
	}


    @RequestMapping("/getCharts")
    public R getHomeCharts(@RequestParam Map<String, String> params){
        int paramsYear = Integer.parseInt(params.get("year"));
        Map<String,Integer> fwgdcsmap = new HashMap<>();
        for (TicketreserveEntity entity: ticketreserveService.selectList(null)) {

            Date createTime = entity.getAddtime(); // 假设 createtime 是字符串类型
            // 提取年份
            SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
            Integer year = Integer.parseInt(yearFormat.format(createTime));
            // 提取月份
            SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
            Integer month = Integer.parseInt(monthFormat.format(createTime));

            if (year==paramsYear){

                EntityWrapper<TicketreserveEntity> ew = new EntityWrapper<>();
                ew.eq("scenicname",entity.getScenicname());
                TicketreserveEntity ticketreserveEntity = ticketreserveService.selectOne(ew);
                //加值
                int num = 0;
                if(fwgdcsmap.get(ticketreserveEntity.getScenicname())!=null){
                    num = fwgdcsmap.get(ticketreserveEntity.getScenicname());
                }
                fwgdcsmap.put(ticketreserveEntity.getScenicname(),num+1);
            }
        }
        return R.ok().put("data", fwgdcsmap);
    }







}
