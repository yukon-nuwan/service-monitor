/**
 * 
 */
package com.yukon.service.monitor.service;

import org.springframework.scheduling.annotation.SchedulingConfigurer;

import com.yukon.service.monitor.dto.CallerServiceDTO;

/**
 * @author Nuwan
 *
 */
public interface SchedulerService extends SchedulingConfigurer{

	void stop(String id);

	void setupFutureTask(CallerServiceDTO callerServiceDTO);


}
