package cn.colg.controller;

import static cn.colg.util.ResultVoUtil.success;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.colg.core.BaseController;
import cn.colg.vo.ResultVo;

/**
 * 买家商品 Controller
 *
 * @author colg
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController extends BaseController {

    /**
     * 买家商品列表（包含类目）
     *
     * @return
     */
    @GetMapping("/list")
    public ResultVo list() {
        return success(productCategoryService.list());
    }
}
