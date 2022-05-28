package com.lamine.InventoryManagement.interceptor;

import org.hibernate.EmptyInterceptor;
import org.springframework.util.StringUtils;

public class Interceptor extends EmptyInterceptor {

    @Override
    public String onPrepareStatement(String sql) {

       /* if (StringUtils.hasLength(sql) && sql.toLowerCase().contains("select")){
            if (sql.toLowerCase().contains("where")){
                sql = sql + " AND id_enteprise = 1 ";
            }else
                sql = sql+" WHERE id_entreprise =1";
        }*/
        return super.onPrepareStatement(sql);
    }
}
