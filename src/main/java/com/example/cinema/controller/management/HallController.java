package com.example.cinema.controller.management;

import com.example.cinema.bl.management.HallService;
import com.example.cinema.vo.HallVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**影厅管理
 * @author fjj
 * @date 2019/4/12 1:59 PM
 */
@RestController
@RequestMapping("/hall")
public class HallController {
    @Autowired
    private HallService hallService;

    @GetMapping("/all")
    public ResponseVO searchAllHall(){
        return hallService.searchAllHall();
    }

    @PostMapping("/addHall")
    public ResponseVO addHall(HallVO hallVO){
        // TODO: 录入新影厅
        return ResponseVO.buildSuccess();
    }

    @PostMapping("/updateHall")
    public ResponseVO updateHall(HallVO hallVO){
        // TODO: 修改影厅信息
        return ResponseVO.buildSuccess();
    }

}
