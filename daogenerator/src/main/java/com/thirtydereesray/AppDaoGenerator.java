

package com.thirtydereesray;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class AppDaoGenerator {

    public static void main(String...args){
        Schema rootSchema = new Schema(3, "com.thirtydegreesray.openhub.dao");
        addAuthUser(rootSchema);
        addTraceUser(rootSchema);
        addTraceRepo(rootSchema);
        addBookMarkUser(rootSchema);
        addBookMarkRepo(rootSchema);
        System.out.println("AppDaoGenerator");
        try {
            new DaoGenerator().generateAll(rootSchema, "E:/Work/Android/github/OpenHub/OpenHub/app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * add user
     * @param schema
     */
    private static void addAuthUser(Schema schema){
        Entity entity = schema.addEntity("AuthUser");
        entity.addStringProperty("accessToken").primaryKey().notNull();
        entity.addDateProperty("authTime").notNull();
        entity.addIntProperty("expireIn").notNull();
        entity.addStringProperty("scope").notNull();
        entity.addBooleanProperty("selected").notNull();

        entity.addStringProperty("loginId").notNull();
        entity.addStringProperty("name");
        entity.addStringProperty("avatar");
    }

    private static void addTraceUser(Schema schema){
        Entity entity = schema.addEntity("TraceUser");
        entity.addStringProperty("login").primaryKey().notNull();
        entity.addStringProperty("name");
        entity.addStringProperty("avatarUrl");
        entity.addIntProperty("followers");
        entity.addIntProperty("following");

        entity.addDateProperty("startTime");
        entity.addDateProperty("latestTime");
        entity.addIntProperty("traceNum");
    }

    private static void addTraceRepo(Schema schema){
        Entity entity = schema.addEntity("TraceRepo");
        entity.addLongProperty("id").primaryKey().notNull();
        entity.addStringProperty("name").notNull();
        entity.addStringProperty("description");
        entity.addStringProperty("language");
        entity.addIntProperty("stargazersCount");
        entity.addIntProperty("watchersCount");
        entity.addIntProperty("forksCount");
        entity.addBooleanProperty("fork");

        entity.addStringProperty("ownerLogin");
        entity.addStringProperty("ownerAvatarUrl");

        entity.addDateProperty("startTime");
        entity.addDateProperty("latestTime");
        entity.addIntProperty("traceNum");
    }

    private static void addBookMarkUser(Schema schema){
        Entity entity = schema.addEntity("BookMarkUser");
        entity.addStringProperty("login").primaryKey().notNull();
        entity.addStringProperty("name");
        entity.addStringProperty("avatarUrl");
        entity.addIntProperty("followers");
        entity.addIntProperty("following");

        entity.addDateProperty("markTime");
    }

    private static void addBookMarkRepo(Schema schema){
        Entity entity = schema.addEntity("BookMarkRepo");
        entity.addLongProperty("id").primaryKey().notNull();
        entity.addStringProperty("name").notNull();
        entity.addStringProperty("description");
        entity.addStringProperty("language");
        entity.addIntProperty("stargazersCount");
        entity.addIntProperty("watchersCount");
        entity.addIntProperty("forksCount");
        entity.addBooleanProperty("fork");

        entity.addStringProperty("ownerLogin");
        entity.addStringProperty("ownerAvatarUrl");

        entity.addDateProperty("markTime");
    }

}
