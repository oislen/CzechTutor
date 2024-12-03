package com.czechtutor.repository.crud;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.czechtutor.model.CesModel;

/**
 * <p>
 * The ces crud repository class defines the available processes to interact
 * with the ces table with</p>
 *
 * @author oislen
 */
public interface CesCrudRepository extends CrudRepository<CesModel, Integer> {

    /**
     * <p>
     * Finds all ces models with a specified level as an array list</p>
     *
     * @param Level the Level to find by
     * @returns the ces models as an array list
     */
    ArrayList<CesModel> findByLevel(String Level);

}
