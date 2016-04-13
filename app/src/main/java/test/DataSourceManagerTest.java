package test;

import android.content.Context;

import com.example.ramjee.myapplication.DataSourceManager;
import com.example.ramjee.myapplication.LoginRecord;

import junit.framework.TestCase;

/**
 * Created by Ramjee on 4/13/2016.
 */
public class DataSourceManagerTest extends TestCase {
    private Context context;
    LoginRecord loginRecord = new LoginRecord("ramjee", "Ramjee", "123456", "ramjinational@gmail.com", 165, 75, "Male", "04/08/1984");
    DataSourceManager dataSourceManager = new DataSourceManager(context);

    public void testInsertUserLogin(){
        dataSourceManager.addLoginRecord(loginRecord);
    }

    public void testFetchUserLogin(){
        dataSourceManager.getAllLoginRecords();
    }

}
