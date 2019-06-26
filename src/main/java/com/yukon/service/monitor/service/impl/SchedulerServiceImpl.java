/**
 * 
 */
package com.yukon.service.monitor.service.impl;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Service;

import com.yukon.service.monitor.controller.CallerRestController;
import com.yukon.service.monitor.dto.CallerServiceDTO;
import com.yukon.service.monitor.dto.ServiceMessageDTO;
import com.yukon.service.monitor.service.CallerService;
import com.yukon.service.monitor.service.CallerServiceService;
import com.yukon.service.monitor.service.SchedulerService;
import com.yukon.service.monitor.util.DateTimeConstant;

/**
 * @author Nuwan
 *
 */

@Service
@EnableScheduling
public class SchedulerServiceImpl implements SchedulerService {
	
	private static final Logger logger = LoggerFactory.getLogger(SchedulerServiceImpl.class);

	@Autowired
	CallerServiceService callerServiceService;

	@Autowired
	CallerService callerService;

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	@Autowired
	private TaskScheduler scheduler;

	private Map<String, ScheduledFuture<?>> futureMap = new HashMap<String, ScheduledFuture<?>>();

	/**
	 * Start Service
	 */
	
	public void start() {
		List<CallerServiceDTO> callerServiceDTOList = callerServiceService.findAlCallerService();

		callerServiceDTOList.stream().forEach(callerServiceDTO -> {
			setupFutureTask(callerServiceDTO);
		});

	}

	/**
	 * Setup future task
	 * 
	 * @param callerServiceDTO
	 * @return
	 */
	@Override
	public void setupFutureTask(CallerServiceDTO callerServiceDTO) {
		
		Runnable task = new Runnable() {
			
			@Override
			public void run() {
				setupTaskData(callerServiceDTO);
				
			}
		};
		
		ScheduledFuture<?> future = scheduler.schedule(task, new Trigger() {
			@Override
			public Date nextExecutionTime(TriggerContext triggerContext) {

				Date nextExecutionTime = new Date();
				Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
				if(lastActualExecutionTime != null) {
					nextExecutionTime.setTime(lastActualExecutionTime.getTime()+ (callerServiceDTO.getPollingFrequency() * 1000));
				}else {
					nextExecutionTime.setTime(nextExecutionTime.getTime() + (callerServiceDTO.getPollingFrequency() * 1000));
				}
				//nextExecutionTime.setTime(callerServiceDTO.get);
				return nextExecutionTime;
			}
		});
		
		futureMap.put(callerServiceDTO.getCallerId() + "_" + callerServiceDTO.getServiceId(), future);
	}

	/**
	 * Setup task data
	 * 
	 * @param callerServiceDTO
	 */
	private void setupTaskData(CallerServiceDTO callerServiceDTO) {
		LocalTime time = LocalTime.now(ZoneId.of(DateTimeConstant.ZONE_ID_ASIA_COLOMBO));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

		if ((callerServiceDTO.getServiceDTO().getOutageStart() == null 
				|| callerServiceDTO.getServiceDTO().getOutageEnd() == null)
				|| (LocalTime.parse(time.format(formatter)).isBefore(callerServiceDTO.getServiceDTO().getOutageStart())
				|| LocalTime.parse(time.format(formatter)).isAfter(callerServiceDTO.getServiceDTO().getOutageEnd()))) {

			ServiceMessageDTO serviceMessageDTO = callerServiceService.runCallerServices(callerServiceDTO);

			sendMessage(callerServiceDTO, serviceMessageDTO);
		}
	}

	/**
	 * Send message to the topic
	 * 
	 * @param callerServiceDTO
	 * @param serviceMessageDTO
	 */
	private void sendMessage(CallerServiceDTO callerServiceDTO, ServiceMessageDTO serviceMessageDTO) {
		if (serviceMessageDTO.getIsAlive()) {
			messagingTemplate.convertAndSend("/topic/service-status/" + callerServiceDTO.getCallerId(),
					serviceMessageDTO);
		} else {
			messagingTemplate.convertAndSend("/topic/service-status/" + callerServiceDTO.getCallerId(),
					serviceMessageDTO);
			try {
				if(callerServiceDTO.getGraceTime() != null) {
					Thread.sleep(callerServiceDTO.getGraceTime() * 1000);
				}
			} catch (InterruptedException e) {
				logger.warn(e.getMessage());
			}
		}
	}

	/**
	 * Get future task by callerId_serviceId and cancel
	 */
	@Override
	public void stop(String id) {
		ScheduledFuture<?> future = futureMap.get(id);
		try {
			if(future != null) {
				future.get();
				future.cancel(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		

	}

	/**
	 * Configure scheduler task
	 */
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		start();
	}

}
