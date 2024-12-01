package com.czechtutor.repository.crud;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.czechtutor.model.CesModel;

public interface CesCrudRepository extends CrudRepository<CesModel, Integer> {

        ArrayList<CesModel> findByLevel(String Level);

}
