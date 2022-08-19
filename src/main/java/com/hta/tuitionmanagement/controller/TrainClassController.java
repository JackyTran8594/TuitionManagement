package com.hta.tuitionmanagement.controller;

import com.hta.tuitionmanagement.dto.response.TrainClassDTO;
import com.hta.tuitionmanagement.service.TrainClassService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/trainClass")
public class TrainClassController extends BaseController{
    @Autowired
    private TrainClassService service;

    @GetMapping("")
    public Page<TrainClassDTO> pageable(@RequestParam Map<String, Object> params, @RequestParam int pageNumber, @RequestParam int pageSize) {
        if(pageNumber > 0) {
            pageNumber = pageNumber - 1;
        }
        params.put("pageSize", pageSize);
        params.put("pageNumber", pageNumber);
        Pageable page = pageRequest(new ArrayList<>(), pageNumber, pageSize);
        List<TrainClassDTO> listData = service.search(params);
        Long totalElement = service.count(params);
        return new PageImpl<>(listData,page,totalElement);
    }
    @PostMapping("")
    public TrainClassDTO addTrainClass(@RequestBody TrainClassDTO item) {
        return service.save(item);
    }

    @PutMapping("/{id}")
    public TrainClassDTO updateTrainClass(@RequestBody TrainClassDTO item,@PathVariable final Long id) {
        item.setId(id);
        return service.save(item);
    }

    @GetMapping("/{id}")
    public TrainClassDTO getById(@PathVariable(value="id") Long id) {
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

