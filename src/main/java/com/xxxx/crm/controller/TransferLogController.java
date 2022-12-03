package com.xxxx.crm.controller;

import com.xxxx.crm.base.BaseController;
import com.xxxx.crm.base.ResultInfo;
import com.xxxx.crm.model.TransferLogModel;
import com.xxxx.crm.query.TransferLogQuery;
import com.xxxx.crm.service.TransferLogService;
import com.xxxx.crm.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("transferLog")
public class TransferLogController extends BaseController {

    @Resource
    private TransferLogService transferLogService;

    @RequestMapping("index")
    public String index(){
        return "transferLog/transfer_log";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryTransferLogByParams(HttpServletRequest request, TransferLogQuery transferLogQuery){
        Integer userId= LoginUserUtil.releaseUserIdFromCookie(request);
        transferLogQuery.setUserId(userId);
        System.out.println(transferLogQuery.getTransferDate());
        return transferLogService.queryTransferLogByParams(transferLogQuery);
    }

    @RequestMapping("openTransferPage")
    public String openTransferPage(HttpServletRequest request,String transferMode){
        request.setAttribute("transferMode",transferMode);
        return "transferLog/transfer_money";
    }

    @PostMapping("transfer_money")
    @ResponseBody
    public ResultInfo transferMoney(HttpServletRequest request, TransferLogModel transferLogModel){
        Integer userId= LoginUserUtil.releaseUserIdFromCookie(request);
        transferLogService.transferMoney(userId,transferLogModel);

        return success("转账成功");
    }
}
