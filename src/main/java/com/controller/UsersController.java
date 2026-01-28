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

import com.entity.UsersEntity;
import com.entity.view.UsersView;

import com.service.UsersService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;

/**
 * 用户
 * 后端接口
 * @author 
 * @email 
 * @date 2025-11-09 18:31:55
 */
@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;


    
	@Autowired
	private TokenService tokenService;
	
	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		UsersEntity u = usersService.selectOne(new EntityWrapper<UsersEntity>().eq("username", username));
		if(u==null || !u.getPassword().equals(password)) {
			return R.error("账号或密码不正确");
		}
		
		String token = tokenService.generateToken(u.getId(), username,"users",  "用户" );
		return R.ok().put("token", token);
	}

	
	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody UsersEntity users){
    	//ValidatorUtils.validateEntity(users);
    	UsersEntity u = usersService.selectOne(new EntityWrapper<UsersEntity>().eq("username", users.getUsername()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
		Long uId = new Date().getTime();
		users.setId(uId);
        usersService.insert(users);
        return R.ok();
    }

	
	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}
	
	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        UsersEntity u = usersService.selectById(id);
        return R.ok().put("data", u);
    }
    
    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	UsersEntity u = usersService.selectOne(new EntityWrapper<UsersEntity>().eq("username", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        u.setPassword("123456");
        usersService.updateById(u);
        return R.ok("密码已重置为：123456");
    }


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,UsersEntity users,
		HttpServletRequest request){
        EntityWrapper<UsersEntity> ew = new EntityWrapper<UsersEntity>();

		PageUtils page = usersService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, users), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,UsersEntity users, 
		HttpServletRequest request){
        EntityWrapper<UsersEntity> ew = new EntityWrapper<UsersEntity>();

		PageUtils page = usersService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, users), params), params));
        return R.ok().put("data", page);
    }

	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( UsersEntity users){
       	EntityWrapper<UsersEntity> ew = new EntityWrapper<UsersEntity>();
      	ew.allEq(MPUtil.allEQMapPre( users, "users")); 
        return R.ok().put("data", usersService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(UsersEntity users){
        EntityWrapper< UsersEntity> ew = new EntityWrapper< UsersEntity>();
 		ew.allEq(MPUtil.allEQMapPre( users, "users")); 
		UsersView usersView =  usersService.selectView(ew);
		return R.ok("查询用户成功").put("data", usersView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        UsersEntity users = usersService.selectById(id);
        return R.ok().put("data", users);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        UsersEntity users = usersService.selectById(id);
        return R.ok().put("data", users);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UsersEntity users, HttpServletRequest request){
    	users.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(users);
    	UsersEntity u = usersService.selectOne(new EntityWrapper<UsersEntity>().eq("username", users.getUsername()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		users.setId(new Date().getTime());
        usersService.insert(users);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody UsersEntity users, HttpServletRequest request){
    	users.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
    	//ValidatorUtils.validateEntity(users);
    	UsersEntity u = usersService.selectOne(new EntityWrapper<UsersEntity>().eq("username", users.getUsername()));
		if(u!=null) {
			return R.error("用户已存在");
		}
		users.setId(new Date().getTime());
        usersService.insert(users);
        return R.ok();
    }



    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody UsersEntity users, HttpServletRequest request){
        //ValidatorUtils.validateEntity(users);
        usersService.updateById(users);//全部更新
        return R.ok();
    }


    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        usersService.deleteBatchIds(Arrays.asList(ids));
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
		
		Wrapper<UsersEntity> wrapper = new EntityWrapper<UsersEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = usersService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	









}
