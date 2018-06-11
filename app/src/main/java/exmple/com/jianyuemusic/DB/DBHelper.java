package exmple.com.jianyuemusic.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @Author: LKL
 * @Date: 2018/6/6 14:25
 * @CodeInfo:数据库中相关的操作
 */
public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper dbHelper = null;//静态对象引用
    private static final String DATABASE_NAME="JianYue.db";
    private static final int DATABASE_VERSION=1;
    private static final String SQL_ThreadTable_CREATE =
            "create table  `thread_info` ("+
                    "`id` int primary key,"+
                    "`url` varchar(100)  ,"+
                    "`start` int  ,"+
                    "`end` int  ,"+
                    "`finished` int )";
    private static final String SQL_ThreadTable_DROP =
            "drop table if exists thread_info";
    /**
     *获得单例对象,解决数据库中的锁的问题！
     * @param context 上下文
     * @return 单例对象
     */
    public  static DBHelper getInstancere(Context context){
        if (dbHelper==null){
            dbHelper = new DBHelper(context);
        }
        return  dbHelper;
    }
    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        /**
         * @Author: LKL
         * @Date: 2018/6/4 17:11
         * @CodeInfo:线程下载的数据表
         */
        db.execSQL(SQL_ThreadTable_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_ThreadTable_DROP);
        db.execSQL(SQL_ThreadTable_CREATE);
    }
}
