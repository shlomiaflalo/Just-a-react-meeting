package org.example.test1234.modules.tech_groups;

import org.example.test1234.appError.AppException;

import java.util.List;

public interface TechGroupService {
    TechGroup addTechGroup(TechGroup techGroup) throws AppException;

    List<TechGroup> getTechGroupList();

    TechGroup getSingleTechGroup(int id) throws AppException;
}
