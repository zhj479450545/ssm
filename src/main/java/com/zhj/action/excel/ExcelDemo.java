package com.zhj.action.excel;

import com.zhj.common.Bean.PageBean;
import com.zhj.service.LoginLogService;
import com.zhj.utils.ExcelUtils;
import com.zhj.vo.LoginLogVo;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/26.
 */
@Controller
@RequestMapping("excelDemo")
public class ExcelDemo {

    @Resource
    LoginLogService loginLogService;

    @RequestMapping("upDownExcelDemo")
    public String excelDemo() {
        return "system/upDownExcelDemo";
    }


    /**
     * 上传Excel表格
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("upLoadUsers")
    public Map<String, String> upLoadUsers(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> resMap = new HashMap<String, String>();
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest request2 = (MultipartHttpServletRequest) request;
            MultipartFile file = request2.getFile("usersExcel");
            String filename = file.getOriginalFilename();
//        判断文件类型
            String fileType = filename.substring(filename.lastIndexOf(".") + 1);
            try {
                ArrayList<ArrayList<String>> lists = ExcelUtils.readExcel(file.getInputStream(), fileType);
                if (null != lists && lists.size() > 0) {
                    for (ArrayList<String> list : lists) {
                        for (String str : list) {
                            System.out.print(str + "\t");
                        }
                        System.out.println();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return resMap;
    }

    /**
     * 下载Excel表格
     * @param request
     * @param response
     */
    @RequestMapping("downLoadUsers")
    public void downLoadUsers(HttpServletRequest request, HttpServletResponse response) {
        PageBean<LoginLogVo> userLoginLogs = loginLogService.findUserLoginLogs(new LoginLogVo(), 1, 1000);
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        String[] keys = {"id", "userId", "username", "loginIp", "loginTime"};
        String[] columnNames = {"ID", "用户id", "用户名称", "登录Ip", "登录时间"};
        if (null != userLoginLogs) {
            List<LoginLogVo> datas = userLoginLogs.getDatas();
            if (null != datas && datas.size() > 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
                Map<String, Object> map = null;
                for (LoginLogVo vo : datas) {
                    map = new HashMap<String, Object>();
                    map.put(keys[0], vo.getId());
                    map.put(keys[1], vo.getUserId());
                    map.put(keys[2], vo.getUsername());
                    map.put(keys[3], vo.getLoginIp());
                    map.put(keys[4], vo.getLoginTime() == null ? "" : sdf.format(vo.getLoginTime()));

                    lists.add(map);
                }
            }
        }
        Workbook workBook = ExcelUtils.createWorkBook(lists, keys, columnNames, null);
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            workBook.write(baos);
            ExcelUtils.setExcelRespons(response, baos, "用户登录日志记录");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != baos) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
