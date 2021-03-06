package com.ant.web;

import com.ant.config.MyProperties1;
import com.ant.config.MyProperties2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lilj
 * @date 18/08/21
 */
@RequestMapping("/properties")
@RestController
@Api(value = "PropertiesController相关的api", description = "properties文件注入与获取", tags = "1.2")
public class PropertiesController {
    private static final Logger log = LoggerFactory.getLogger(PropertiesController.class);

    private final MyProperties1 myProperties1;
    private final MyProperties2 myProperties2;


    /**
     * Spring4.x以后，推荐使用构造函数的形式注入属性
     * @param myProperties1
     * @param myProperties2
     */
    @Autowired
    public PropertiesController(MyProperties1 myProperties1, MyProperties2 myProperties2) {
        this.myProperties1 = myProperties1;
        this.myProperties2 = myProperties2;
    }

    @GetMapping("/1")
    @ApiOperation(value = "value是啥", notes = "获取myProperties1")
    public MyProperties1 myProperties1() {
        log.info("=================================================================================================");
        log.info(myProperties1.toString());
        log.info("=================================================================================================");
        return myProperties1;
    }

    @GetMapping("/2")
    @ApiOperation(value = "value是啥？？？", notes = "获取myProperties2")
    public MyProperties2 myProperties2() {
        log.info("=================================================================================================");
        log.info(myProperties2.toString());
        log.info("=================================================================================================");
        return myProperties2;
    }
}
