package com.lamine.InventoryManagement.dto;

import com.lamine.InventoryManagement.model.Utilisateur;
import lombok.Builder;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Builder
@Data
public class UtilisateurDto {

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
    private Integer idEntreprise;

    public static UtilisateurDto fromEntity(Utilisateur utilisateur){
        if (utilisateur == null ){
            //TODO an exception
            return null ;
        }
         return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .dateNaissance(utilisateur.getDateNaissance())
                .motDePasse(utilisateur.getMotDePasse())
                .photo(utilisateur.getPhoto())
                .mail((utilisateur.getMail()))
                .numTel(utilisateur.getNumTel())
                .build();
    }

    public static Utilisateur toEntity(UtilisateurDto utilisateurDto){
        if (utilisateurDto == null){
            // TODO an exception
            return null ;
        }
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setDateNaissance(utilisateurDto.getDateNaissance());
        utilisateur.setMotDePasse(utilisateurDto.getMotDePasse());
        utilisateur.setPhoto(utilisateurDto.getPhoto());
        utilisateur.setMail(utilisateurDto.getMail());
        utilisateur.setNumTel(utilisateurDto.getNumTel());

        return utilisateur;
    }
}
