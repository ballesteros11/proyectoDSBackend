package com.rdai.domain.services;


import com.rdai.api.validations.SearchValidation;
import com.rdai.data.models.entities.CasaEntity;
import com.rdai.data.repository.CasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BusquedaCasaService {


    @Autowired
    private CasaRepository casaRepository;

    public List<CasaEntity> getByPais(String pais){
        return casaRepository.findAllByPais(pais);
    }

    public List<CasaEntity> getByEstado(String estado){
        return casaRepository.findAllByPais(estado);
    }

    public List<CasaEntity> getByCiudad(String ciudad){
        return casaRepository.findAllByPais(ciudad);
    }

    public List<CasaEntity> getAll(){
        return casaRepository.findAll();
    }

    public Optional<CasaEntity> getById(Integer id){
        return casaRepository.findById(id);
    }

    public List<CasaEntity> getByCriterial(SearchValidation searchValidation){
        return casaRepository.findAllByCriterial(searchValidation);
    }


}
