/*******************************************************************************
* Copyright (c) 2018 IBM Corp.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*******************************************************************************/
package com.acmeair.health;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import com.acmeair.web.HealthCheckRest;

/**
 * 
 * @author jagraj
 *
 */
@Health
@ApplicationScoped
public class FailedHealthCheck implements HealthCheck{
	
	@Inject HealthCheckBean healthCheckBean;
	@Inject
	private HealthCheckRest healthCheckRest;
	
    @Override
    public HealthCheckResponse call() {
        
		try {
			if(healthCheckRest.checkStatus().getStatus()!=200) {
				return HealthCheckResponse.named("FlightService:failed-check").down().build();
			}
			else if(healthCheckBean.getIsAppDown()!=null && healthCheckBean.getIsAppDown().booleanValue()==true) {
				return HealthCheckResponse.named("FlightService:failed-check").down().build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return HealthCheckResponse.named("FlightService:successful-check").up().build();
	}

        

}
