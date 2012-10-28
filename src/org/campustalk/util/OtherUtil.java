package org.campustalk.util;

import java.util.UUID;

public class OtherUtil {

	final static public  String getRandomeString(){
		String uuid = (UUID.randomUUID().toString() + UUID.randomUUID().toString()) ;
		uuid=  uuid.replace("-", "").replace(" ", "");
		System.out.println(uuid);
		return uuid;
	}

}
