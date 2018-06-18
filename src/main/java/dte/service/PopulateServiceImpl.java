package dte.service;

import dte.dao.AnimalTypeDao;
import dte.model.Animal;
import dte.model.AnimalType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Eliza on 18.06.2018.
 */
public class PopulateServiceImpl implements PopulateService {

    @Autowired
    AnimalTypeDao animalTypeDao;

    @Override
    public void populateInitialData(Boolean useMock) {

        populateAnimalTypes();

        if(useMock){
            populatePersonsWithMockData();
        }
    }

    private void populateAnimalTypes(){
        animalTypeDao.saveOrUpdate(AnimalType.createBuilder().setName("Dog").build());
        animalTypeDao.saveOrUpdate(AnimalType.createBuilder().setName("Cat").build());
        animalTypeDao.saveOrUpdate(AnimalType.createBuilder().setName("Hamster").build());
    }

    private void populatePersonsWithMockData(){

    }


}
