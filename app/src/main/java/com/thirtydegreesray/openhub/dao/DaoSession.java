package com.thirtydegreesray.openhub.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.thirtydegreesray.openhub.dao.AuthUser;
import com.thirtydegreesray.openhub.dao.TraceUser;
import com.thirtydegreesray.openhub.dao.TraceRepo;
import com.thirtydegreesray.openhub.dao.BookMarkUser;
import com.thirtydegreesray.openhub.dao.BookMarkRepo;

import com.thirtydegreesray.openhub.dao.AuthUserDao;
import com.thirtydegreesray.openhub.dao.TraceUserDao;
import com.thirtydegreesray.openhub.dao.TraceRepoDao;
import com.thirtydegreesray.openhub.dao.BookMarkUserDao;
import com.thirtydegreesray.openhub.dao.BookMarkRepoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig authUserDaoConfig;
    private final DaoConfig traceUserDaoConfig;
    private final DaoConfig traceRepoDaoConfig;
    private final DaoConfig bookMarkUserDaoConfig;
    private final DaoConfig bookMarkRepoDaoConfig;

    private final AuthUserDao authUserDao;
    private final TraceUserDao traceUserDao;
    private final TraceRepoDao traceRepoDao;
    private final BookMarkUserDao bookMarkUserDao;
    private final BookMarkRepoDao bookMarkRepoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        authUserDaoConfig = daoConfigMap.get(AuthUserDao.class).clone();
        authUserDaoConfig.initIdentityScope(type);

        traceUserDaoConfig = daoConfigMap.get(TraceUserDao.class).clone();
        traceUserDaoConfig.initIdentityScope(type);

        traceRepoDaoConfig = daoConfigMap.get(TraceRepoDao.class).clone();
        traceRepoDaoConfig.initIdentityScope(type);

        bookMarkUserDaoConfig = daoConfigMap.get(BookMarkUserDao.class).clone();
        bookMarkUserDaoConfig.initIdentityScope(type);

        bookMarkRepoDaoConfig = daoConfigMap.get(BookMarkRepoDao.class).clone();
        bookMarkRepoDaoConfig.initIdentityScope(type);

        authUserDao = new AuthUserDao(authUserDaoConfig, this);
        traceUserDao = new TraceUserDao(traceUserDaoConfig, this);
        traceRepoDao = new TraceRepoDao(traceRepoDaoConfig, this);
        bookMarkUserDao = new BookMarkUserDao(bookMarkUserDaoConfig, this);
        bookMarkRepoDao = new BookMarkRepoDao(bookMarkRepoDaoConfig, this);

        registerDao(AuthUser.class, authUserDao);
        registerDao(TraceUser.class, traceUserDao);
        registerDao(TraceRepo.class, traceRepoDao);
        registerDao(BookMarkUser.class, bookMarkUserDao);
        registerDao(BookMarkRepo.class, bookMarkRepoDao);
    }
    
    public void clear() {
        authUserDaoConfig.clearIdentityScope();
        traceUserDaoConfig.clearIdentityScope();
        traceRepoDaoConfig.clearIdentityScope();
        bookMarkUserDaoConfig.clearIdentityScope();
        bookMarkRepoDaoConfig.clearIdentityScope();
    }

    public AuthUserDao getAuthUserDao() {
        return authUserDao;
    }

    public TraceUserDao getTraceUserDao() {
        return traceUserDao;
    }

    public TraceRepoDao getTraceRepoDao() {
        return traceRepoDao;
    }

    public BookMarkUserDao getBookMarkUserDao() {
        return bookMarkUserDao;
    }

    public BookMarkRepoDao getBookMarkRepoDao() {
        return bookMarkRepoDao;
    }

}
