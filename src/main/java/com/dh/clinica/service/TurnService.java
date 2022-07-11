package com.dh.clinica.service;

import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.persistence.entity.Turn;
import com.dh.clinica.persistence.repository.TurnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnService {

    @Autowired
    TurnRepository turnRepository;

    public List<Turn> findAllTurns(){
        return turnRepository.findAll();
    }

    public Turn saveTurn(Turn turn){
        return turnRepository.save(turn);
    }

    public Optional<Turn> findTurnById(Long id){
        return turnRepository.findById(id);
    }

    public void deleteTurnById(Long id) throws ResourceNotFoundException {
        turnRepository.deleteById(id);
    }

    public Turn updateTurn(Turn turn){
        return turnRepository.save(turn);
    }
}
