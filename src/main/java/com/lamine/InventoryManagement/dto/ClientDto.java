package com.lamine.InventoryManagement.dto;

import com.lamine.InventoryManagement.model.Adresse;
import com.lamine.InventoryManagement.model.Client;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Builder
@Data
public class ClientDto {

    private Integer id ;
    private String name;
    private String prenom ;
    private Adresse adresse ;
    private String photo;
    private String mail ;
    private String numTel ;
    private List<CommandeClientDto> commandeClients ;
    private Integer idEntreprise;

    public static ClientDto fromEntity(Client client){

        if (client == null){
            // do an exception
            return null;
        }
       return  ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .prenom(client.getPrenom())
                .adresse(client.getAdresse())
                .mail(client.getMail())
                .numTel(client.getNumTel())
                .photo(client.getPhoto())
               .idEntreprise(client.getIdEntreprise())
                .build();
    }
    public static Client toEntity(ClientDto clientDto){

        if (clientDto== null){
            // to do an exception
            return null;
        }
        Client client = new Client();
        client.setId(clientDto.getId()) ;
        client.setName(clientDto.getName());
        client.setPrenom(clientDto.getPrenom());
        client.setAdresse(clientDto.getAdresse());
        client.setMail(clientDto.getMail());
        client.setPhoto(clientDto.getPhoto());
        client.setNumTel(clientDto.getNumTel());
        client.setIdEntreprise(clientDto.getIdEntreprise());
        return client;
    }
}
