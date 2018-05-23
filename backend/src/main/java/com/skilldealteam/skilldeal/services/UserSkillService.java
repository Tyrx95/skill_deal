package com.skilldealteam.skilldeal.services;

import com.skilldealteam.skilldeal.helpers.forms.UserSkillForm;
import com.skilldealteam.skilldeal.persistence.model.tables.SkillCategory;
import com.skilldealteam.skilldeal.persistence.model.tables.User;
import com.skilldealteam.skilldeal.persistence.model.tables.UserSkill;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserSkillService extends BaseService {

    public boolean createUserSkill(final UserSkillForm userSkillForm) {
        try {
            SkillCategory skillCategory = (SkillCategory) getSession().createCriteria(SkillCategory.class)
                    .add(Restrictions.eq("id", userSkillForm.getSkillId()))
                    .uniqueResult();
            User user = (User) getSession().createCriteria(User.class)
                    .add(Restrictions.eq("id", userSkillForm.getUserId()))
                    .uniqueResult();
            UserSkill userSkill = userSkillForm.createUserSkill(user,skillCategory);
            getSession().save(userSkill);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean deleteUserSkill(UUID skillId) {

        Criteria criteria = getSession().createCriteria(UserSkill.class).add(Restrictions.eq("id", skillId));
        UserSkill userSkillToDelete = (UserSkill) criteria.uniqueResult();
        getSession().delete(userSkillToDelete);
        return true;
    }

    public List<UserSkill> getAllUserSkills(UUID userId) {

        Criteria criteria = getSession().createCriteria(UserSkill.class);
        criteria.add(Restrictions.eq("user.id", userId));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<UserSkill>) criteria.list();

    }


}
