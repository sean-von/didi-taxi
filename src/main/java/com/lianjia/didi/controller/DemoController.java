package com.lianjia.didi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fengxiao on 16/7/1.
 */
@Controller
@RequestMapping("demo")
public class DemoController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public Object info(){
        Map<String, Object> map = new HashMap<String, Object>(){{
            put("info", "世界你好");
            put("time", new Date());
        }};
        logger.debug("returned value is {}", map);
        return map;
    }

}
