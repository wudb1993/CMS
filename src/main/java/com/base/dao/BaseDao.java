package com.base.dao;


        import java.io.Serializable;
        import java.lang.reflect.ParameterizedType;
        import java.lang.reflect.Type;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

        import javax.annotation.Resource;

        import org.apache.ibatis.session.SqlSessionFactory;
        import org.mybatis.spring.support.SqlSessionDaoSupport;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository
public abstract class BaseDao<T, PK extends Serializable> {

   /* public static Logger logger = LoggerFactory.getLogger(BaseDao.class);

    private Class<T> entityClass = null;



    *//**
     * 创建默认构造方法，以取得真正的泛型类型
     *//*
    public BaseDaoImpl() {

        Class<?> c = getClass();
        Type type = c.getGenericSuperclass();

        if (type instanceof ParameterizedType) {

            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
            entityClass = (Class<T>) parameterizedType[0];

        }

    }


    public Class<T> getEntityClass() {
        return entityClass;
    }

    @Resource(name = "sqlSessionFactory")
    public void setSuperSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }


    public long generateId(T entity) throws Exception {
        String tableName = entity.getClass().getSimpleName();
        return idsDAO.generateId(tableName);
    }

    // 保存实体对象
    //@CacheEvict(value = "ehcache", allEntries = true)

    public T insert(T entity) {
        getSqlSession().insert(entity.getClass().getSimpleName() + ".insert", entity);
        return entity;
    }


    public void insert(String mapperId, Object obj){
        getSqlSession().insert(entityClass.getSimpleName() + "."+mapperId, obj);
    }
    //@CacheEvict(value = "ehcache", allEntries = true)
    @Override
    public List<T> insertList(List<T> entities) {
        return getSqlSession().selectList(entityClass.getSimpleName() + ".insertList", entities);
    }


    // 更新

    //@CacheEvict(value = "ehcache", allEntries = true)

    public void update(T entity) {

        getSqlSession().update(entity.getClass().getSimpleName() + ".update", entity);

    }

    //@CacheEvict(value = "ehcache", allEntries = true)

    public void updateNull(T entity) {
        getSqlSession().update(entityClass.getSimpleName() + ".updateNull", entity);
    }




    public List<T> updateBatch(List<T> entities) {
        return getSqlSession().selectList(entityClass.getSimpleName() + ".updateBatch", entities);
    }


    public void updateByConditon(String mapperId, T entity) {
        getSqlSession().update(entityClass.getSimpleName() + "."+mapperId, entity);
    }


    public void updateByConditon(String mapperId, Map<String, Object> condition) {
        getSqlSession().update(entityClass.getSimpleName() + "."+mapperId, condition);
    }

    // 根据id删除某个对象
    //@CacheEvict(value = "ehcache", allEntries = true)
    @Override
    public void deleteById(PK id) {

        getSqlSession().delete(entityClass.getSimpleName() + ".deleteById", id);

    }

    // 根据id加载某个对象
    //@Cacheable(value = "ehcache", key = "#id")

    public T fetch(PK id) {

        return getSqlSession().selectOne(entityClass.getSimpleName() + ".fetch", id);

    }


    public T fetchForUpdate(PK id) {

        return getSqlSession().selectOne(entityClass.getSimpleName() + ".fetchForUpdate", id);

    }

    // 查找所有的对象
    @SuppressWarnings("hiding")

    public <T>List<T> findAll() {

        return getSqlSession().selectList(entityClass.getSimpleName() + ".queryList", null);

    }


    public Object queryPage(String mapId, Map<String, Object> condition, Page<T> page) {
        if (condition != null && condition.containsKey("startTime") && condition.get("startTime").toString().length() == 10) {
            condition.put("startTime", condition.get("startTime") + " 00:00:00");
        }
        if (condition != null && condition.containsKey("endTime") && condition.get("endTime").toString().length() == 10) {
            condition.put("endTime", condition.get("endTime") + " 23:59:59");
        }
        if (condition != null && condition.containsKey("startTime") && condition.get("startTime").toString().length() == 9) {
            condition.put("startTime", condition.get("startTime") + " 00:00:00");
        }
        if (condition != null && condition.containsKey("endTime") && condition.get("endTime").toString().length() == 9) {
            condition.put("endTime", condition.get("endTime") + " 23:59:59");
        }
        if (condition != null && condition.containsKey("startDate") && condition.get("startDate").toString().length() == 10) {
            condition.put("startDate", condition.get("startDate") + " 00:00:00");
        }
        if (condition != null && condition.containsKey("endDate") && condition.get("endDate").toString().length() == 10) {
            condition.put("endDate", condition.get("endDate") + " 23:59:59");
        }
        if (condition != null && condition.containsKey("startDate") && condition.get("startDate").toString().length() == 9) {
            condition.put("startDate", condition.get("startDate") + " 00:00:00");
        }
        if (condition != null && condition.containsKey("endDate") && condition.get("endDate").toString().length() == 9) {
            condition.put("endDate", condition.get("endDate") + " 23:59:59");
        }
        return this.queryPage(entityClass.getSimpleName(), mapId, condition, page);
    }

    // 根据查询参数，当前页数，每页显示的数目得到分页列表
    //@Cacheable(value = "ehcache", key = "'queryPage-'+#condition+'-'+#currentPage+'-'+#pageSize")

    public Pager<T> queryPage(Map<String, Object> condition, Integer currentPage, Integer pageSize) {

        Pager<T> pager = new Pager<T>(pageSize, 0, currentPage);
        try {
            if (condition == null) {
                condition = new HashMap<String, Object>();
            }
            condition.put("beginRow", (pager.getCurrentPage() - 1) * pager.getPageSize());
            condition.put("pageSize", pager.getPageSize());
            PageHelper.startPage(currentPage, pageSize);
            List<T> dataList = this.getSqlSession().selectList(entityClass.getSimpleName() + ".queryList", condition);
            long total = ((com.github.pagehelper.Page<?>) dataList).getTotal();
            pager.setDataList(dataList);
            pager.setTotalCount(Long.valueOf(total).intValue());
            return pager;
        } catch (RuntimeException re) {
            logger.error("findList " + entityClass.getSimpleName() + "failed :{}", re);
        }
        return null;

    }


    public Page<T> queryPage(Map<String, Object> condition, Page<T> page) {
        try {
            if (condition == null) {
                condition = new HashMap<String, Object>();
            }
            Page<T> result = PageHelper.startPage(page.getPageNum(), page.getPageSize());

            this.getSqlSession().selectList(entityClass.getSimpleName() + ".queryList", condition);

            return result;
        } catch (RuntimeException re) {
            logger.error("findList " + entityClass.getSimpleName() + "failed :{}", re);
        }
        return null;
    }


    public Object queryPage(String nameSpace, String mapId, Map<String, Object> condition, Page<T> page) {

        try {
            if (condition == null) {
                condition = new HashMap<String, Object>();
            }
            Page<T> result = PageHelper.startPage(page.getPageNum(), page.getPageSize());

            this.getSqlSession().selectList( nameSpace + "."+mapId, condition);

            return result;
        } catch (RuntimeException re) {
            logger.error("findList " + entityClass.getSimpleName() + "failed :{}", re);
        }
        return null;
    }


    public int count(Map<String, Object> condition) {

        int count = getSqlSession().selectOne(entityClass.getSimpleName() + ".count", condition);

        return count;

    }

    //@Cacheable(value = "ehcache", key = "'queryList-'+#condition+'-orderBy-'+#orderBy+'-sortBy-'+#sortBy")
    @SuppressWarnings("hiding")

    public <T>List<T> queryList(Map<String, Object> condition, String orderBy,
                                String sortBy) {

        if (condition == null) {
            condition = new HashMap<String, Object>();
            condition.put("orderBy", orderBy);
            condition.put("sortBy", sortBy);
        }

        return getSqlSession().selectList(entityClass.getSimpleName() + ".queryList", condition);

    }



    //@CacheEvict(value = "ehcache", allEntries = true)

    public T updateOrSave(T t, PK id) {

        if (null != fetch(id)) {
            update(t);
        } else {
            return insert(t);
        }

        return t;

    }

    //@Cacheable(value = "ehcache", key = "'findOne-'+#property+'-'+#value")
    @SuppressWarnings("hiding")

    public <T>T findOne(String property, Object value) {

        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put(property, value);
        return getSqlSession().selectOne(entityClass.getSimpleName() + ".findOne", condition);

    }

    @SuppressWarnings("hiding")

    public <T> T findOne(Map<String, Object> condition) {
        return getSqlSession().selectOne(entityClass.getSimpleName() + ".findOneByMap", condition);
    }

    //@Cacheable(value = "ehcache", key = "'findList-'+#property+'-'+#value")
    @SuppressWarnings("hiding")

    public <T>List<T> findList(String property, Object value) {

        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put(property, value);
        return getSqlSession().selectList(entityClass.getSimpleName() + ".findList", condition);

    }

    @SuppressWarnings("hiding")

    public <T>List<T> findList(Map<String, Object> condition) {
        return getSqlSession().selectList(entityClass.getSimpleName() + ".findList", condition);

    }


    public Integer selectMaxId() {

        return getSqlSession().selectOne(entityClass.getSimpleName() + ".selectMaxId");

    }

    //@CacheEvict(value = "ehcache", allEntries = true)

    public void deleteByCondition(Map<String, Object> condition) {

        getSqlSession().delete(entityClass.getSimpleName() + ".deleteByCondition", condition);

    }

    //@CacheEvict(value = "ehcache", allEntries = true)

    public void deleteByProperty(String property, Object value) {

        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put(property, value);
        deleteByCondition(condition);

    }

    //@Cacheable(value = "ehcache", key = "'queryOne-'+#condition")

    public Object queryOne(String mapperId, Map<String, Object> condition ) {

        return getSqlSession().selectOne(entityClass.getSimpleName() + "."+mapperId, condition);

    }

    //@Cacheable(value = "ehcache", key = "'selectOne-'+#mapperId+'-'+#obj")
    @SuppressWarnings("hiding")

    public <T>T selectOne(String mapperId, Object obj) {

        return getSqlSession().selectOne(entityClass.getSimpleName() + "." + mapperId, obj);

    }

    //@Cacheable(value = "ehcache", key = "'selectList-'+#mapperId+'-'+#obj")
    @SuppressWarnings("hiding")

    public<T> List<T> selectList(String mapperId, Object obj) {

        return getSqlSession().selectList(entityClass.getSimpleName() + "." + mapperId, obj);

    }

    @SuppressWarnings("rawtypes")

    public List selectList(String namespace, String mapperId, Object obj) {

        return getSqlSession().selectList(namespace + "." + mapperId, obj);

    }

    public DetailsPager<T> queryDetailsPage(Map<String, Object> condition, PK id) {

        T currentObj = fetch(id);
        if (currentObj != null) {
            List<PK> ids = getSqlSession().selectList(entityClass.getSimpleName() + ".findIds", condition);
            int currentObjIndex = ids.indexOf(id);
            DetailsPager<T> page = new DetailsPager<T>(currentObj);
            if (currentObjIndex > 0)
                page.setPreObj(fetch(ids.get(currentObjIndex - 1)));
            if (currentObjIndex < ids.size() - 1)
                page.setNextObj(fetch(ids.get(currentObjIndex + 1)));
            return page;
        }

        return null;

    }


    public List<T> like(String property, Object value) {

        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put(property, value);

        return getSqlSession().selectList(entityClass.getSimpleName() + ".like", condition);

    }
*/
}
