package com.techelevator.controller;

import com.techelevator.dao.CatCardDao;
import com.techelevator.model.CatCard;
import com.techelevator.model.CatFact;
import com.techelevator.model.CatPic;
import com.techelevator.services.CatFactService;
import com.techelevator.services.CatPicService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.Path;
import java.util.List;


@RestController
public class CatController {

    private static final String API_URL = "api/cards";

    private CatCardDao catCardDao;
    private CatFactService catFactService;
    private CatPicService catPicService;

    public CatController(CatCardDao catCardDao, CatFactService catFactService, CatPicService catPicService) {
        this.catCardDao = catCardDao;
        this.catFactService = catFactService;
        this.catPicService = catPicService;
    }

    @RequestMapping(path = API_URL, method = RequestMethod.GET)
    public List<CatCard> getCatList (){

        return this.catCardDao.list();
    }

    @RequestMapping(path = API_URL + "/{id}", method = RequestMethod.GET)
    public CatCard getCatCardById (@PathVariable long id){

        return catCardDao.get(id); // ==> updated this
    }

    @RequestMapping(path = API_URL + "/random", method = RequestMethod.GET)
    public CatCard getCatCardRandom () {
        CatCard randomCatCard = new CatCard();
        randomCatCard.setCatFact(catFactService.getFact().getText());
        randomCatCard.setImgUrl(catPicService.getPic().getFile());
        randomCatCard.setCaption("");
        return randomCatCard;
    }

    @RequestMapping(path = API_URL, method = RequestMethod.POST)
    public boolean saveCatCard (@RequestBody CatCard catCard){

        return catCardDao.save(catCard);
    }

    @RequestMapping(path = API_URL + "/{id}", method = RequestMethod.PUT)
    public boolean updateCatCard (@RequestBody CatCard catCard, @PathVariable long id) {
        if(id != catCard.getCatCardId()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID not found!");
        }
        return  catCardDao.update(id, catCard); //updateCatCard(catCard,id); ==> updated this
    }

    @RequestMapping(path = API_URL + "/{id}", method = RequestMethod.DELETE)
    public void deleteCatCard (@PathVariable long id){
        catCardDao.delete(id);
        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "ID has been deleted!");
    }



}
