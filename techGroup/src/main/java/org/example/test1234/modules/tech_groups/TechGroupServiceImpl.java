package org.example.test1234.modules.tech_groups;

import lombok.RequiredArgsConstructor;
import org.example.test1234.appError.AppException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TechGroupServiceImpl implements TechGroupService {
    private final TechGroupRepository techGroupRepository;

    @Override
    public TechGroup addTechGroup(TechGroup techGroup) throws AppException {
        if (this.techGroupRepository.existsById(techGroup.getId())) {
            throw new AppException(TechGroupError.TECH_GROUP_ALREADY_EXIST);
        }
        return this.techGroupRepository.save(techGroup);
    }

    @Override
    public List<TechGroup> getTechGroupList() {
        return this.techGroupRepository.findAll();
    }

    @Override
    public TechGroup getSingleTechGroup(int id) throws AppException {
        return this.techGroupRepository.findById(id).orElseThrow(() -> new AppException(TechGroupError.TECH_GROUP_NOT_FOUND));

    }
}
