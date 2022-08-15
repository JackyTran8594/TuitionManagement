package com.hta.tuitionmanagement.controller;

import com.hta.tuitionmanagement.dto.response.FunctionDTO;
import com.hta.tuitionmanagement.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/function")
public class FunctionController extends BaseController {

    @Autowired
    private FunctionService service;

    @GetMapping("")
    public Page<FunctionDTO> pageable(@RequestParam Map<String, Object> mapParam, @RequestParam int pageSize, @RequestParam int pageNumber) {
        if(pageNumber > 0) {
            pageNumber = pageNumber - 1;
        }
        mapParam.put("pageSize", pageSize);
        mapParam.put("pageNumber", pageNumber);
        Pageable page = pageRequest(new ArrayList<>(), pageNumber, pageSize);
        List<FunctionDTO> listData = service.search(mapParam);
        Long totalElement = service.count(mapParam);
        return new PageImpl<>(listData,page,totalElement);
    }

    @PostMapping("")
    public FunctionDTO add(@RequestBody FunctionDTO item) {
        return service.save(item);
    }

    @PutMapping("/{id}")
    public FunctionDTO update(@RequestBody FunctionDTO item) {
        item.setId(item.getId());
        return service.save(item);
    }

    @GetMapping("/{id}")
    public FunctionDTO getById(@PathVariable(value="id") Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable(value="id") Long id) {
        return service.deleteById(id);
    }

    @PostMapping("/deleteAll")
    public boolean deleteById(@RequestBody  List<Long> listId) {
        return service.deleteAll(listId);
    }

}
