package com.lamine.InventoryManagement.service.impl;

import com.lamine.InventoryManagement.dto.ChangePasswordUtilisateurDto;
import com.lamine.InventoryManagement.dto.UtilisateurDto;
import com.lamine.InventoryManagement.exception.EntityInvalidException;
import com.lamine.InventoryManagement.exception.EntityNotFoundException;
import com.lamine.InventoryManagement.exception.ErrorCode;
import com.lamine.InventoryManagement.exception.InvalidOperationException;
import com.lamine.InventoryManagement.model.Utilisateur;
import com.lamine.InventoryManagement.repository.UtilisateurRepository;
import com.lamine.InventoryManagement.service.UtilisateurService;
import com.lamine.InventoryManagement.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {


    UtilisateurRepository utilisateurRepository;
    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository ) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDto getUtilisateur(Integer id) {

        if (id == null){
            log.error("utilisateur id is null");
            return null;
        }
        return Optional.of(UtilisateurDto.fromEntity(utilisateurRepository.findById(id).get())).
                orElseThrow(()->  new EntityNotFoundException(
                 "no utilisateur with this id", ErrorCode.UTILISATEUR_NOT_FOUND) );
    }

    @Override
    public List<UtilisateurDto> getAllUtilisateurs() {
        return utilisateurRepository.findAll().stream().map(
                UtilisateurDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public UtilisateurDto create(UtilisateurDto utilisateurDto) {

       List<String> errors = UtilisateurValidator.validate(utilisateurDto);
       if (!errors.isEmpty()){
           log.error("utilisateur not valid{}",utilisateurDto);
           throw  new EntityInvalidException("utilisateur not valid",ErrorCode.UTILISATEUR_NOT_VALID,errors);
       }
       utilisateurDto.setMotDePasse(passwordEncoder().encode(utilisateurDto.getMotDePasse()));
       return UtilisateurDto.fromEntity(utilisateurRepository.save(UtilisateurDto.toEntity(utilisateurDto)));

    }

    @Override
    public void delete(Integer id) {

        if (id == null){
            log.error("no utilisateur with this id");
            return ;
        }
        utilisateurRepository.deleteById(id);
    }

    @Override
    public UtilisateurDto getUtilisateurByEmail(String email) {

       Optional<Utilisateur> utilisateur = utilisateurRepository.findByMail(email);
        return utilisateur.map(UtilisateurDto::fromEntity)
                .orElseThrow(
                ()-> new EntityNotFoundException("No user with this Login",ErrorCode.UTILISATEUR_NOT_FOUND)
        );
    }

    @Override
    public UtilisateurDto changePassword(ChangePasswordUtilisateurDto dto) {

        if (dto ==  null){
            log.warn("impossible to update the password with null");
            throw new InvalidOperationException("no new password is given in order to update the old one ",ErrorCode.UTILISATEUR_PASSWORD_NOT_GIVEN);
        }
        if (dto.getId() ==  null){
            log.warn("impossible to update the password with an id utilisateur null");
            throw new InvalidOperationException("impossible to update password with an id utilisateur null ",ErrorCode.UTILISATEUR_PASSWORD_NOT_GIVEN);
        }
        if (!StringUtils.hasLength(dto.getPassword())||!StringUtils.hasLength(dto.getConfirmPassword())){
            log.warn("impossible to update the password with an empty one");
            throw new InvalidOperationException("impossible to update password with an empty one ",ErrorCode.UTILISATEUR_PASSWORD_NOT_GIVEN);
        }
        if (!dto.getPassword().equals(dto.getConfirmPassword())){
            log.warn("impossible to update the password with 2 deferent passwords");
            throw new InvalidOperationException("impossible to update password with 2 deferent passwords ",ErrorCode.UTILISATEUR_PASSWORD_NOT_GIVEN);
        }

        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(dto.getId());
        if (utilisateurOptional.isEmpty()){
            log.error("no utilisateur found with this id");
            throw  new EntityNotFoundException("no utilisateur found with this id",ErrorCode.UTILISATEUR_NOT_FOUND);
        }
        Utilisateur utilisateur = utilisateurOptional.get();
        utilisateur.setMotDePasse(passwordEncoder().encode(dto.getPassword()));

        return UtilisateurDto.fromEntity(utilisateurRepository.save(utilisateur));
    }

    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
