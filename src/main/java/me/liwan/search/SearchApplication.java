package me.liwan.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SearchApplication {

	public static void main(String[] args) {
//		TrieBuilder tb = new TrieBuilder();
//		tb.buildFromFile("./data/test");
//		System.out.println(tb.getTopKQueriesByPrefix("手机"));
		SpringApplication.run(SearchApplication.class, args);
	}

}
