package com.lianjia.didi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

/**
 * Created by fengxiao on 16/9/7.
 */
@Controller
@RequestMapping("invoice")
public class InvoiceController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ModelAndView edit(){
        return new ModelAndView("voice_edit");
    }


    @RequestMapping(value = "save", method = RequestMethod.POST)
    public ModelAndView save(@RequestParam("file") CommonsMultipartFile file,
                             @RequestParam("name") String name) {
        try{
            logger.info("saved param is {}, {}", file.getName(), name);
            return new ModelAndView("success");
        }catch (final Exception e){
            logger.error(e.getMessage(), e);
            return new ModelAndView("error", new HashMap<String, Object>(){{
                put("message", e.getMessage());
            }});
        }
    }



}
