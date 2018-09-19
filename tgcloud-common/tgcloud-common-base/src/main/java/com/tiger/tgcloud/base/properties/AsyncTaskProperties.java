package com.tiger.tgcloud.base.properties;

import lombok.Data;

/**
 * The class Async task properties.
 *
 * @author tgcloud.net @gmail.com
 */
@Data
public class AsyncTaskProperties {

	private int corePoolSize = 50;

	private int maxPoolSize = 100;

	private int queueCapacity = 10000;

	private int keepAliveSeconds = 3000;

	private String threadNamePrefix = "tgcloud-task-executor-";
}
