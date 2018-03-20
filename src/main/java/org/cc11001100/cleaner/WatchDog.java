package org.cc11001100.cleaner;

import org.cc11001100.entity.Proxy;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.BitSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author CC11001100
 */
@Component
public class WatchDog {

	private ConcurrentHashMap<String, Object> alreadyHave = new ConcurrentHashMap<>();

	public void eat(String name, List<Proxy> food) {

	}

	public void score(Double score){

	}

	public void test(Proxy proxy){

	}

	public static void main(String[] args) {



	}

}
