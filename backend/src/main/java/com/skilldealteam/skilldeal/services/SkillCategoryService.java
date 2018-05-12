package com.skilldealteam.skilldeal.services;

import com.skilldealteam.skilldeal.persistence.model.tables.SkillCategory;
import com.skilldealteam.skilldeal.persistence.model.tables.UserSkill;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SkillCategoryService extends BaseService {

    public List<SkillCategory> getSkillCategories() {
        return (List<SkillCategory>) getSession().createCriteria(SkillCategory.class).list();
    }

    public Boolean createSkillCategory(final SkillCategory skillCategory) throws Exception {
        getSession().save(skillCategory);
        return true;
    }

    public List<UserSkill> getUserSkills(UUID skillCategoryId) {

        Criteria userSkillCriteria = getSession().createCriteria(UserSkill.class);
        userSkillCriteria.add(Restrictions.eq("skill.id", skillCategoryId));

        return (List<UserSkill>) userSkillCriteria.list();
    }
}
