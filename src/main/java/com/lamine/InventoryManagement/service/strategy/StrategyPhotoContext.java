package com.lamine.InventoryManagement.service.strategy;

import com.flickr4java.flickr.FlickrException;
import com.lamine.InventoryManagement.exception.ErrorCode;
import com.lamine.InventoryManagement.exception.InvalidOperationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class StrategyPhotoContext {

    private BeanFactory beanFactory;
    private Strategy strategy;
    String context ;

    @Autowired
    public StrategyPhotoContext(BeanFactory beanFactory) {
        this.beanFactory= beanFactory;
    }

    public Object save (String context ,Integer id , InputStream photo ,String titre) throws FlickrException {
        determineContext(context);
        return strategy.save(id, photo, titre);
    }

    private void determineContext (String context){
        final String beanName = context+"Strategy";
        switch (context) {
            case "article":
                strategy= beanFactory.getBean(beanName,SaveArticlePhoto.class);
                break;
            case "client":
                strategy= beanFactory.getBean(beanName,SaveClientPhoto.class);
                break;
            case "fornisseur":
                strategy= beanFactory.getBean(beanName,SaveFornisseurPhoto.class);
                break;
            case "entreprise":
                strategy= beanFactory.getBean(beanName,SaveEntreprisePhoto.class);
                break;
            case "utilisateur":
                strategy= beanFactory.getBean(beanName,SaveUtilisateurPhoto.class);
                break;
            default:
                throw new InvalidOperationException("unkown context for saving the photo", ErrorCode.UNKOWN_CONTEXT);

        }

    }
}
