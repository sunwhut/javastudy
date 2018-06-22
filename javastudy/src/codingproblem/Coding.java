package codingproblem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/9/28.
 */
public class Coding {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(isInRange("172.24.5.1", "172.24.0.0/17"));
        int k = Integer.parseInt(scanner.nextLine());
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < k; i++) {
            String str = scanner.nextLine();
            String ip = str.split("/")[0];
//            System.out.println(ip);
            if (i == 0){
                list.add(str);
                continue;
            }
            Iterator<String> iterator = list.iterator();
            boolean add = false;
            while (iterator.hasNext()){
                String cidr = iterator.next();
                String cidr_ip = cidr.split("/")[0];
                if (isInRange(ip, cidr)){
                    add = false;
                    continue;
                } else if (isInRange(cidr_ip, str)){
                    add = true;
                    iterator.remove();
                    continue;
                } else {
                    add = true;
                }
            }
            if (add) list.add(str);
        }
        System.out.println(list.size());
        for (String str : list) {
            System.out.println(str);
        }
    }

    public static boolean isInRange(String ip, String cidr) {
        String[] ips = ip.split("\\.");
        int ipAddr = (Integer.parseInt(ips[0]) << 24)
                | (Integer.parseInt(ips[1]) << 16)
                | (Integer.parseInt(ips[2]) << 8) | Integer.parseInt(ips[3]);
        int type = Integer.parseInt(cidr.replaceAll(".*/", ""));
        int mask = 0xFFFFFFFF << (32 - type);
        String cidrIp = cidr.replaceAll("/.*", "");
        String[] cidrIps = cidrIp.split("\\.");
        int cidrIpAddr = (Integer.parseInt(cidrIps[0]) << 24)
                | (Integer.parseInt(cidrIps[1]) << 16)
                | (Integer.parseInt(cidrIps[2]) << 8)
                | Integer.parseInt(cidrIps[3]);

        return (ipAddr & mask) == (cidrIpAddr & mask);
    }
}