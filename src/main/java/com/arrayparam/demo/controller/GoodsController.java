package com.arrayparam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;

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

}
