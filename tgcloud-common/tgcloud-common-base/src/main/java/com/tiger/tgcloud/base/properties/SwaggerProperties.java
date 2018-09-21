package com.tiger.tgcloud.base.properties;

import lombok.Data;

/**
 * The class Async task properties.
 *
 * @author tgcloud.net @gmail.com
 */
@Data
public class SwaggerProperties {

	private String title;

	private String description;

	private String version = "1.0";

	private String license = "Apache License 2.0";

	private String licenseUrl = "http://www.apache.org/licenses/LICENSE-2.0";

	private String contactName = "tiger";

	private String contactUrl = "http://www.xixi624.com";

	private String contactEmail = "tgcloud.net@gmail.com";
}
