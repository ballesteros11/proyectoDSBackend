package com.rdai.domain.services;

import com.rdai.data.models.entities.CasaEntity;
import com.rdai.data.repository.CasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CasaService {

    @Autowired
    private CasaRepository casaRepository;

    public CasaEntity publicarCasa(CasaEntity casa){
        return casaRepository.save(casa);
    }

    public List<CasaEntity> findAllByPais(String pais){
        return casaRepository.findAllByPais(pais);
    }

    public List<CasaEntity> findAllByEstado(String estado){
        return casaRepository.findAllByEstado(estado);
    }

    public List<CasaEntity> findAllByCiudad(String ciudad){
        return casaRepository.findAllByCiudad(ciudad);
    }

    public List<CasaEntity> findAllByPropietario(String nombreUsuario){
        return casaRepository.findAllByUsuarioEntity_NombreUsuario(nombreUsuario);
    }


}
