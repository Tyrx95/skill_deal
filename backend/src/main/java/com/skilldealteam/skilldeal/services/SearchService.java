package com.skilldealteam.skilldeal.services;

import com.skilldealteam.skilldeal.exceptions.ServiceException;
import com.skilldealteam.skilldeal.helpers.SearchFilter;
import com.skilldealteam.skilldeal.persistence.model.tables.Location;
import com.skilldealteam.skilldeal.persistence.model.tables.User;
import com.skilldealteam.skilldeal.persistence.model.tables.UserSkill;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Service
public class SearchService extends BaseService {

    public List<UserSkill> search(final SearchFilter searchFilter) throws ServiceException{

        Criteria userSkillCriteria = getSession().createCriteria(UserSkill.class);
        Criteria userCriteria = userSkillCriteria.createCriteria("user","u");
        if(!searchFilter.queryString.isEmpty()) {
            if (searchFilter.searchBy.equalsIgnoreCase("skill")) {
                Criteria skillCategoryCriteria = userSkillCriteria.createCriteria("skill");
                skillCategoryCriteria.add(Restrictions.ilike("name", searchFilter.queryString));
            } else if (searchFilter.searchBy.equalsIgnoreCase("location")) {
                Criteria locationCriteria = userCriteria.createCriteria("location");
                locationCriteria.add(Restrictions.ilike("name", searchFilter.queryString));
            } else if (searchFilter.searchBy.equalsIgnoreCase("user")) {
                userCriteria.add(Restrictions.disjunction()
                        .add(Restrictions.ilike("firstName", searchFilter.queryString))
                        .add(Restrictions.ilike("lastName", searchFilter.queryString)));
            }
        }

        if(searchFilter.liveMeeting == true) {
            userSkillCriteria.add(Restrictions.eq("givesLiveMeeting", searchFilter.liveMeeting));
        }

        if(searchFilter.videoLesson == true){
            userSkillCriteria.add(Restrictions.eq("givesVideoLesson", searchFilter.videoLesson));
        }
        userSkillCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<UserSkill> userSkills = userSkillCriteria.list();

        if(searchFilter.sortBy.equalsIgnoreCase("rating")){
            userSkills.sort((s1, s2) -> s1.getUser().getRating().compareTo(s2.getUser().getRating()));
        }

        else if(searchFilter.sortBy.equalsIgnoreCase("relevance")){
            //TODO implement relevance algorithm;
        }
        HashSet<User> seen=new HashSet<>();
        userSkills.removeIf(e->!seen.add(e.getUser()));
        return userSkills;
    }

}
