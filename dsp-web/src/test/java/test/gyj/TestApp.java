package test.gyj;

import com.service.HistoryService;
import org.junit.Test;

import java.util.*;

public class TestApp {
    @Test
    public void getByUserId() {
        HistoryService service = new HistoryService();
        service.getHistoryById("1");
    }

    @Test
    public void test2() {
        HistoryService service = new HistoryService();
        for(int i=1; i<=10; i++) {
            for(int j=1; j<=10; j++) {
                service.addHistory(String.valueOf(i),String.valueOf(j));
            }
        }
    }

    @Test
    public void insert() {
        HistoryService service = new HistoryService();
        service.addHistory("1","1");
        service.addHistory("1","2");
    }

    @Test
    public void maptest() {
        Map<String,String> map = new LinkedHashMap<>();
        map.put("3","3");
        map.put("2","2");
        map.put("1","1");
        PrintMap(map);
    }
    private void PrintMap(Map map) {
        Set<Map.Entry> set = map.entrySet();
        Iterator<Map.Entry> iterator = set.iterator();
        while(iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            System.out.println("key:"+entry.getKey() + "   value:" +entry.getValue());
        }
    }
}
