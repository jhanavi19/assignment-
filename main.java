import java.util.*;
import org.json.*;
import org.json.JSONArray;
public class Main {
    public static class Database {
        String url;
        int unique_key;
        int count;

        Database(String url, int unique_key, int count) {
            this.url = url;
            this.count = count;
            this.unique_key = unique_key;
        }

    }
    static ArrayList<Database> list = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = 1;


        int start = 1000;
        int end = 9999;

        ArrayList<Integer> key = new ArrayList();
        int p = 0;
        while (t == 1) {
            System.out.println("Enter either of the commands: storeurl, list, count, get, exit");
            String command = sc.next();
            if (command.equals("exit")) {
                t = 0;
                break;
            }
            else if (command.equals("get")) {
                System.out.println("Enter the Url to get the unique key");
                String s = sc.next();
                int uq = get(s);
                System.out.println(uq);
            }
            else if (command.equals("count")) {
                System.out.println("Enter the Url to get the current usage count");
                String s = sc.next();
                int uc = usage_count(s);
                System.out.println(uc);
            }
            else if(command.equals("list")) {
                JSONObject detail = list();
                System.out.println(detail);
            }
            else if(command.equals("storeurl")) {
                System.out.println("Enter the URL to store into database");
                String url = sc.next();
                Random rand = new Random();
                int r = rand.nextInt(end - start) + start;
                while (key.contains(r)) {
                    r = rand.nextInt(end - start) + start;
                }
                key.add(r);
                Database db = new Database(url, r, 0);
                list.add(db);

            }
            else {
                System.out.println("Wrong Command!!! Enter correct one");
            }

        }

    }

    private static JSONObject list() {
        JSONObject rdj = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for(int i=0;i<list.size();i++) {
            JSONObject fdj = new JSONObject();
            fdj.put("Usage Count", list.get(i).count);
            fdj.put("Unique Key", list.get(i).unique_key);
            fdj.put("URL", list.get(i).url);

            jsonArray.join(String.valueOf(fdj));
        }



        rdj.put("URL_DATABASE", jsonArray);
        return rdj;


    }

    private static int usage_count(String s) {
        int p=0,q=0;
        for(int i=0;i<list.size();i++) {
            if((list.get(i).url).equals(s)) {
                p=1;
                q=list.get(i).count;
                break;
            }
        }
        if(p==0) {
            System.out.println("The Url is not found!!!");
            return 0;
        }
        else {
            return q;
        }

    }

    private static int get(String s) {
        int p=0,q=0;
        for(int i=0;i<list.size();i++) {
            if((list.get(i).url).equals(s)) {
                p=1;
                q=list.get(i).unique_key;
                list.get(i).count++;
                break;
            }
        }
        if(p==0) {
            System.out.println("The Url is not found!!!");
            return 0;
        }
        else {
            return q;
        }


    }

}
