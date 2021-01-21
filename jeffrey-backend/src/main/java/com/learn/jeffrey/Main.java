package com.learn.jeffrey;

import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.springframework.security.crypto.codec.Hex;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/7/28 11:03
 **/
public class Main {
    public static void main(String[] args) throws IOException {
        SM3Digest sm3 = new SM3Digest();
        byte[] md = new byte[32];
        sm3.update("123456".getBytes(), 0, "123456".getBytes().length);
        sm3.doFinal(md, 0);
        System.out.print(new String(Hex.encode(md)));
    }

    public static int test_7() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextLine().length();
        return 26 * (n + 1) - 1;
    }

    public static void test_6() {
        for (int i = 0; i < 10000; i++) {
            System.out.print((char) (i + 'a') + " ");
            if (i % 10 == 0) {
                System.out.println();
            }
        }
    }

    public static void test_5() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        int[] r = new int[M];
        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            r[i] = Integer.parseInt(s.substring(s.length() - 6));
        }
        Arrays.sort(r);
        for (int i = 0; i < M; i++) {
            System.out.println(r[i]);
        }
    }

    public static void test_4() {
        Scanner scanner = new Scanner(System.in);
        int M = scanner.nextInt();
        int[] r = new int[M];
        int i = 0;
        scanner.nextLine();
        do {
            String s = scanner.nextLine();
            r[i] = Integer.parseInt(s.substring(s.length() - 6));
            i++;
        } while (!scanner.hasNext("q"));

        Arrays.sort(r);
        for (int j = 0; j < M; j++) {
            System.out.println(r[j]);
        }
    }

    public static void test_1() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine().trim();
        char[] ch = str.toCharArray();
        int[] a = new int[26];
        for (int i = 0; i < ch.length; i++) {
            a[ch[i] - 'a']++;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < a.length; i++) {
            if (a[i] != 0) {
                stringBuffer.append((char) (i + 'a')).append(a[i]);
            }
        }
        System.out.println(stringBuffer.toString());
    }

    public static void test_2() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        char[] ch = str.toCharArray();
        int[] a = new int[26];
        for (int i = 0; i < ch.length; i++) {
            a[ch[i] - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            if (a[i] != 0) {
                sb.append((char) (i + 'a')).append(a[i]);
            }
        }
        System.out.println(sb.toString());
    }

    public static void test_3() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(reader.readLine());
        int[] res = new int[M];
        int l = 0;
        for (int i = 0; i < M; ++i) {
            String t = reader.readLine();
            res[l++] = Integer.parseInt(t.substring(t.length() - 6));

        }
        reader.close();
        Arrays.sort(res);
        for (int j : res) {
            System.out.println(j);
        }
    }


}
