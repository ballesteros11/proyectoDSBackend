package com.rdai.data.repository.custom;

import com.rdai.api.validations.SearchValidation;
import com.rdai.data.models.entities.CasaEntity;
import java.util.List;

public interface CriterioCasaRepository {

    List<CasaEntity> findAllByCriterial(SearchValidation searchValidation);


}
