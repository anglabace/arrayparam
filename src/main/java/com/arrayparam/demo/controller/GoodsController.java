package com.arrayparam.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.arrayparam.demo.form.AttrType;
import com.arrayparam.demo.form.FormFile;
import com.arrayparam.demo.form.Sku;
import com.arrayparam.demo.form.SkuForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;
import java.util.Iterator;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    //显示商品图片
    @GetMapping("/image")
    public String goodsimage(Model model,@Min(value = 1,message = "商品id需要>0")@RequestParam("goodsId") Long goodsId) {

        model.addAttribute("goodsId", goodsId);
        return "goods/image";
    }

    //保存各图片的排序值
    @PostMapping("/contentsaveorder")
    @ResponseBody
    public String goodsContentSaveOrder(HttpServletRequest request,
                                       @Min(value = 1,message = "商品id需要>0")@RequestParam("goodsId") Long goodsId,
                                            @RequestParam(value = "imgids[]") Long[] imgids,
                                            @RequestParam(value = "orders[]") int[] orders
    ) {
        String res = "当前商品id:"+goodsId+"\n";
        for (int i = 0; i < imgids.length; i++) {
            Long imageId = imgids[i];
            int orderNum = orders[i];
            //System.out.println("update cur imageId:"+imageId+",cur orderNum:"+orderNum);
            res += "当前图片id:"+imageId+";排序值:"+orderNum+"\n";
        }
        return res;
    }

    //显示商品图片
    @GetMapping("/sku")
    public String sku(Model model,@Min(value = 1,message = "商品id需要>0")@RequestParam("goodsId") Long goodsId) {

        model.addAttribute("goodsId", goodsId);
        return "goods/sku";
    }

    //保存各图片的排序值
    @PostMapping("/skuadded")
    @ResponseBody
    //public String skuadded(@RequestBody SkuForm skuform,HttpServletRequest request) {
    public String skuadded(@RequestParam("json") String json,HttpServletRequest request) {
        //@RequestParam("json") String json,
        //String res = "skuadded";
        System.out.println(json);

        SkuForm skuform = JSONObject.parseObject(json, SkuForm.class);


        String res = "goodsId:"+skuform.getGoodsId()+"\n";

        for (Sku skuOne : skuform.getSkuList()) {
            //System.out.println(s.getId()+"  "+s.getTitle()+"  "+s.getAuthor());
            res+="key:"+skuOne.getKey()+";price:"+skuOne.getPrice()+";stock:"+skuOne.getStock()+"\n";
        }

        for (AttrType typeone :skuform.getTypeList()) {
            res+="typename:"+typeone.getTypeName()+";isimage:"+typeone.getIsImage()+";attrlist:"+typeone.getAttrList()+"\n";
        }

        for (FormFile fileOne : skuform.getFileList()) {
            res+="filelist:fileName:"+fileOne.getFileName()+"\n";
        }


        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());

         // 判断是否是多数据段提交格式
        if (multipartResolver.isMultipart(request)) {
            System.out.println("isMultipart");
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            Iterator<String> iter = multiRequest.getFileNames();
            //logger.info("iter.hasNext(): "+iter.hasNext());
            Integer fileCount = 0;
            while (iter.hasNext()) {
                MultipartFile multipartFile = multiRequest.getFile(iter.next());
                String varName = multipartFile.getName();
                String fileName = multipartFile.getOriginalFilename();
                //logger.info("upload filename: " + fileName );
                if(fileName == null || fileName.trim().equals("")){
                    continue;
                } else {
                    //System.out.println("fileName:"+fileName+";varName:"+varName);
                    res+="file:fileName:"+fileName+";varName:"+varName+"\n";
                }
            }
        } else {
            System.out.println("not isMultipart");
        }






       return res;


        /*
        String res = "当前商品id:"+goodsId+"\n";
        for (int i = 0; i < imgids.length; i++) {
            Long imageId = imgids[i];
            int orderNum = orders[i];
            //System.out.println("update cur imageId:"+imageId+",cur orderNum:"+orderNum);
            res += "当前图片id:"+imageId+";排序值:"+orderNum+"\n";
        }
        return res;
         */
    }
}
