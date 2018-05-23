package com.skilldealteam.skilldeal.services;
/* Copied and modified
Source com.skilldealteam.skilldeal.services.UserSkillService;
*/
import com.skilldealteam.skilldeal.persistence.model.tables.Location;
/*
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
*/
import com.skilldealteam.skilldeal.persistence.model.tables.SkillCategory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
/*
import java.util.UUID;
*/

@Service
public class LocationService extends BaseService {

    /**
    public List<Location> getAllLocationsImmutable() {

        return Collections.unmodifiableList(getAllLocations().clone());
    }
     */
    public List<Location> getAllLocations() {

        return (List<Location>) getSession().createCriteria(Location.class).list();
    }

    public Boolean createLocation(final Location location) throws Exception {
        getSession().save(location);
        return true;
    }
}