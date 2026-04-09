package com.klef.fsad.sdp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.klef.fsad.sdp.entity.Diet;
import com.klef.fsad.sdp.repository.DietRepository;

@Service
public class DietServiceImpl implements DietService {

    @Autowired
    private DietRepository dietRepository;

    @Override
    public String addDiet(Diet diet) {
        dietRepository.save(diet);
        return "Diet added successfully";
    }

    @Override
    public List<Diet> getUserDiets(int userId) {
        return dietRepository.findByUserId(userId);
    }

    @Override
    public Diet updateDiet(Diet diet) {
        return dietRepository.save(diet); // updates if ID exists
    }

    @Override
    public String deleteDiet(int id) {
        dietRepository.deleteById(id);
        return "Diet deleted successfully";
    }

    @Override
    public List<Diet> getAllDiets() {
        return dietRepository.findAll();
    }
}