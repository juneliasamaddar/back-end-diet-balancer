package com.klef.fsad.sdp.service;

import java.util.List;
import com.klef.fsad.sdp.entity.Diet;

public interface DietService {
    String addDiet(Diet diet);
    List<Diet> getUserDiets(int userId);
    Diet updateDiet(Diet diet); // <-- only this one
    String deleteDiet(int id);
    List<Diet> getAllDiets();
}