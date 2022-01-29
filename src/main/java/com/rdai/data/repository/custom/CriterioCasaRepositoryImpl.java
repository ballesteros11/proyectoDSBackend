package com.rdai.data.repository.custom;

import com.rdai.api.validations.SearchValidation;
import com.rdai.data.models.entities.CasaEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CriterioCasaRepositoryImpl implements CriterioCasaRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<CasaEntity> findAllByCriterial(SearchValidation searchValidation){

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<CasaEntity> cq = cb.createQuery(CasaEntity.class);

        Root<CasaEntity> casa = cq.from(CasaEntity.class);

        List<Predicate> predicateList = new ArrayList<>();

        if(searchValidation == null){
            cq.select(casa);
            return entityManager.createQuery(cq).getResultList();
        }

        if(!searchValidation.getPais().isEmpty() || !searchValidation.getPais().isBlank()){
            predicateList.add(cb.like(casa.get("pais"), searchValidation.getPais()));

            if(!searchValidation.getEstado().isEmpty() || !searchValidation.getEstado().isBlank()){
                predicateList.add(cb.like(casa.get("estado"), searchValidation.getEstado()));

                if(!searchValidation.getCiudad().isEmpty() || !searchValidation.getCiudad().isBlank()){
                    predicateList.add(cb.like(casa.get("ciudad"), searchValidation.getCiudad()));

                }

            }

        }
        cq.select(casa)
                .where(cb.and(predicateList.toArray(new Predicate[predicateList.size()])));

        return entityManager.createQuery(cq).getResultList();


    }


}
