package com.fagawee.mvp.kit;

import android.database.sqlite.SQLiteDatabase;

import com.fagawee.mvp.BaseApp;
import com.fagawee.mvp.log.XLog;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBaseConfig;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.SQLiteHelper;

import java.util.List;

/**
 * Created by Mr.Tian on 2019/12/17.
 */

public class OrmDBHelper {


    private static LiteOrm mLiteOrm;
    private boolean isOpenOrmLog=true;// whether open the log

    //orm数据库相关
    private final String ORM_DB_NAME="hykd.db";//orm数据库文件名，默认地址
    private final int ORM_DB_VERSION=1;//orm数据库版本，默认地址
//    private final String ORM_DB_NAME= SDCardUtil.getInnerSDCardPath()+"student.db";//orm数据库文件名，自定义地址

    private OrmDBHelper() {

    }

    private static class Holder {
        private static OrmDBHelper instance = new OrmDBHelper();
    }

    public static OrmDBHelper getInstance() {
        return OrmDBHelper.Holder.instance;
    }


    /**获取orm数据库对象**/
    public LiteOrm getOrmDataBase(){
        if(mLiteOrm==null){
            //mLiteOrm = LiteOrm.newCascadeInstance(RApp.getInstance(), ORM_DB_NAME);
            DataBaseConfig dataBaseConfig=new DataBaseConfig(BaseApp.getInstance(), ORM_DB_NAME, isOpenOrmLog, ORM_DB_VERSION, new SQLiteHelper.OnUpdateListener() {
                @Override
                public void onUpdate(SQLiteDatabase sqLiteDatabase, int i, int i1) {
                    XLog.d("数据库更新了：oldVersion:"+i+" newVersion:"+i1);

                }
            });
            mLiteOrm = LiteOrm.newCascadeInstance(dataBaseConfig);
        }

        return mLiteOrm;
    }

    //==================================================================
    //插入
    public <T> long insert(T t) {
        return getOrmDataBase().insert(t);
    }

    //插入or更新
    public <T> long save(T t) {
        return getOrmDataBase().save(t);
    }

    /***
     * 更新
     */
    public <T> void update(T t){
        getOrmDataBase().update(t);
    }

    /**
     * 插入所有记录
     * @param list
     */
    public <T> void insertAll(List<T> list) {
        getOrmDataBase().save(list);
    }

    /**
     * 查询所有
     * @param cla
     * @return
     */
    public <T> List<T> getQueryAll(Class<T> cla) {
        return getOrmDataBase().query(cla);
    }

    /**
     * 查询  某字段 等于 Value的值
     * @param cla
     * @param field
     * @param value
     * @return
     */
    public <T> List<T> getQueryByWhere(Class<T> cla, String field, Object[] value) {
        return getOrmDataBase().<T>query(new QueryBuilder(cla).where(field + "=?", value));
    }

    /**
     * 查询  某字段 等于 Value的值  可以指定从1-20，就是分页
     * @param cla
     * @param field
     * @param value
     * @param start
     * @param length
     * @return
     */
    public <T> List<T> getQueryByWhereLength(Class<T> cla, String field, Object[] value, int start, int length) {
        return getOrmDataBase().<T>query(new QueryBuilder(cla).where(field + "=?", value).limit(start, length));
    }

    /**
     * 删除一个数据
     * @param t
     * @param <T>
     */
    public <T> void delete( T t){
        getOrmDataBase().delete( t ) ;
    }

    /**
     * 删除一个表
     * @param cla
     * @param <T>
     */
    public <T> void delete( Class<T> cla ){
        getOrmDataBase().delete( cla );
    }

    /**
     * 删除集合中的数据
     * @param list
     * @param <T>
     */
    public <T> void deleteList( List<T> list ){
        getOrmDataBase().delete( list ) ;
    }

    /**
     * 删除数据库
     */
    public void deleteDatabase(){
        getOrmDataBase().deleteDatabase() ;
    }

}
