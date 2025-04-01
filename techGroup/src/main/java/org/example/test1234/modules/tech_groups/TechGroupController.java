package org.example.test1234.modules.tech_groups;

import lombok.RequiredArgsConstructor;
import org.example.test1234.appError.AppException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/techGroup")
@CrossOrigin("*")
@RequiredArgsConstructor
public class TechGroupController {
    private final TechGroupService techGroupService;

    @PostMapping
    public TechGroup addTechGroup(@RequestBody TechGroup techGroup) throws AppException {
        return techGroupService.addTechGroup(techGroup);
    }

    @GetMapping
    public List<TechGroup> getTechGroupList() {
        return techGroupService.getTechGroupList();
    }

    @GetMapping("/{id}")
    public TechGroup getSingleTechGroup(@PathVariable int id) throws AppException {
        return techGroupService.getSingleTechGroup(id);
    }
}
