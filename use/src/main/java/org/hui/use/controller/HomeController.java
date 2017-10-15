package org.hui.use.controller;

import org.hui.smart.framework.annotation.Action;
import org.hui.smart.framework.annotation.Controller;
import org.hui.smart.framework.annotation.Inject;
import org.hui.smart.framework.bean.Param;
import org.hui.smart.framework.bean.View;
import org.hui.use.service.HomeService;

/**
 * Created by Admin on 2017/10/15.
 */
@Controller
public class HomeController {

    @Inject
    private HomeService homeService;

    @Action("get:/index")
    public View index(Param param) {
        View view = new View("index.jsp");
        return view.addModel("service", homeService.test());
    }
}
