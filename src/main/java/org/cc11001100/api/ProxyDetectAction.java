package org.cc11001100.api;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author CC11001100
 */
@RequestMapping("/proxyDetect")
@Controller
public class ProxyDetectAction {


	@RequestMapping("/")
	@ResponseBody
	public Map detect(HttpRequest request){

		request.getHeaders();


		return null;
	}


}
