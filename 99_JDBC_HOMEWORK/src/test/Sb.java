package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



public class Sb {
	public static void main(String[] args) throws IOException {
		String text = "홍길동&이수홍,박연수-최명호";

		String[] names = text.split("&|,|-");

		for (int i = 0; i < names.length; i++) {
			System.out.println(names[i]);
		}
			System.out.print("숫자 2개 입력 : ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str, " ");
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
		
			
			StringBuilder sb = new StringBuilder();
			
			sb.append(a+b);
			System.out.println(sb.toString());
			
			//ArrayList<String> colors 
			
		}
	}

