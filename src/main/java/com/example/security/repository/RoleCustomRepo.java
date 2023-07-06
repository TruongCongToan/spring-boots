package com.example.security.repository;

import com.example.security.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Queue;

@Repository
public class RoleCustomRepo {
    @PersistenceContext
    EntityManager entityManager;

    public List<String> getRoles(User user){
        StringBuilder sql = new StringBuilder();
        sql.append("select r.name as name from user us join user_role ur on us.id = ur.user_id join role r on ur.role_id = r.id where 1=1 and ");
        if(user.getEmail() != null){
            sql.append("email =:email");
        }
        NativeQuery<String> query = ((Session) entityManager.getDelegate()).createNativeQuery(sql.toString());
        if(user.getEmail() != null){
            query.setParameter("email",user.getEmail());
        }
        query.addScalar("name",StandardBasicTypes.STRING);
        query.setResultListTransformer(Transformers.aliasToBean(String.class));
        return query.list();
        }
}
