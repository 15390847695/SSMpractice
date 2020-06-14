package com.heit.SSM.controller;


import com.heit.SSM.model.QUerydata;
import com.heit.SSM.model.QueryResp;
import com.heit.SSM.model.User;
import com.heit.SSM.req.AddReq;
import com.heit.SSM.resp.RespMsg;
import com.heit.SSM.service.AddService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
   @RequestMapping(value = "/show")
    public String ShowUser(){
        System.out.println("lond ye mian ");
        return "WEB-INF/view/user";
    }

    @Autowired
    private AddService addService;
   @Autowired
   private RespMsg respMsg;
    private static final Logger loger = LoggerFactory.getLogger(UserController.class);

   @ResponseBody
    @PostMapping(value = "/add")
    public RespMsg add(@RequestBody AddReq addReq){

        System.out.println("account:"+addReq.getAccount());
        RespMsg respMsg=new RespMsg();
      if(StringUtils.isEmpty(addReq.getAccount())){
          respMsg.setStatus(-1);
          respMsg.setMsg("用户名不能为空");
          return respMsg;
      }
        boolean flag=addService.add(addReq);
      if(flag==true){
          respMsg.setStatus(1);
          respMsg.setMsg("添加成功");
      }else{
          respMsg.setMsg("添加失败");
          respMsg.setStatus(-1);
      }
       loger.info("返回前端的数据：{}",respMsg);
       //返回json数据
       return respMsg;

    }
    //查询功能
    @ResponseBody
    @RequestMapping("/all")
    public QUerydata ShowQuery(){
       List<User> list=addService.query();//数据库数据
        System.out.println("输出查询结果"+list.size());
        QUerydata qUerydata =new QUerydata();
        qUerydata.setCode(0);
        qUerydata.setMsg("查询成功");
        List<QueryResp> datalist=new ArrayList<QueryResp>();

        if(CollectionUtils.isEmpty(list)){
            return qUerydata;
        }
        for(User u:list){
            QueryResp queryResp=new QueryResp();
            queryResp.setAccount(u.getUsername());
            queryResp.setInfo(u.getMassage());
            queryResp.setName(u.getName());
            queryResp.setPassword(u.getPassword());
            queryResp.setId(u.getId());
            datalist.add(queryResp);
        }

         qUerydata.setData(datalist);
       return qUerydata;
    }

    //删除功能
    @ResponseBody
    @RequestMapping(value = "/del/{id}")
    public RespMsg  ShowDelete(@PathVariable int id){
   loger.info("根据id删除用户:",id);
//       QueryResp queryResp=new QueryResp();
//     queryResp.setId(id);
int rel=   addService.delete(id);
        System.out.println("测试"+rel);

        if(rel>0){
            respMsg.setStatus(1);
            respMsg.setMsg("删除成功");
        }else{
            respMsg.setMsg("删除失败");
            respMsg.setStatus(-1);
        }
    return respMsg;
    }
    @ResponseBody
    @RequestMapping(value = "/update")
    public RespMsg ShowUpdate(@RequestBody AddReq addReq){
       loger.info("参数："+addReq.getAccount());
        loger.info("参数："+addReq.getId());

    int relu= addService.update(addReq);
        if(relu>0){
            respMsg.setStatus(1);
            respMsg.setMsg("修改成功");
        }else{
            respMsg.setMsg("修改失败");
            respMsg.setStatus(-1);
        }
        return respMsg;
    }


//    @RequestMapping("/in")
//    public String Show(){
//        System.out.println("测试");
//        addService.insert1();
//        return "user";
//    }
}
