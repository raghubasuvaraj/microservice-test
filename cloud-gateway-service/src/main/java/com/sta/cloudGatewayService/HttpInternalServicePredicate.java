package com.sta.cloudGatewayService;

import java.util.function.Predicate;

import org.springframework.web.server.ResponseStatusException;

/**
 * Find out the SERVER_ERROR. Return true if it is SERVER_ERROR(500-599)
 * @author r@ghu
 *
 */
public class HttpInternalServicePredicate implements Predicate<ResponseStatusException> {

	@Override
	public boolean test(ResponseStatusException t) {
		return t.getStatus().is5xxServerError();
	}

}
