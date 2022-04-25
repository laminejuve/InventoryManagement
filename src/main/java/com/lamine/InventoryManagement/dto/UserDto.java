package com.lamine.InventoryManagement.dto;

import com.lamine.InventoryManagement.model.User;
import lombok.Builder;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Builder
@Data
public class UserDto {

    private Integer id ;
    private String nom;
    private String prenom ;
    private Date dateNaissance;
    private String motDePasse ;
    private AdresseDto adresse ;
    private String photo;
    private String mail ;
    private String numTel ;
    private List<RolesDto> roles ;
    private EntrepriseDto entreprise;

    public UserDto fromEntity (User user){
        if (user == null ){
            //TODO an exception
            return null ;
        }
         return UserDto.builder()
                .id(user.getId())
                .nom(user.getNom())
                .prenom(user.getPrenom())
                .dateNaissance(user.getDateNaissance())
                .motDePasse(user.getMotDePasse())
                .photo(user.getPhoto())
                .mail((user.getMail()))
                .numTel(user.getNumTel())
                .build();
    }

    public User toEntity (UserDto userDto){
        if (userDto == null){
            // TODO an exception
            return null ;
        }
        User user = new User();
        user.setId(userDto.getId());
        user.setNom(userDto.getNom());
        user.setPrenom(userDto.getPrenom());
        user.setDateNaissance(userDto.getDateNaissance());
        user.setMotDePasse(userDto.getMotDePasse());
        user.setPhoto(userDto.getPhoto());
        user.setMail(userDto.getMail());
        user.setNumTel(userDto.getNumTel());

        return user;
    }
}
