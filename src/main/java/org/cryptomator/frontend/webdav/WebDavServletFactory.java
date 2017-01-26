/*******************************************************************************
 * Copyright (c) 2016 Sebastian Stenzel and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the accompanying LICENSE.txt.
 *
 * Contributors:
 *     Sebastian Stenzel - initial API and implementation
 *******************************************************************************/
package org.cryptomator.frontend.webdav;

import java.nio.file.Path;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.cryptomator.frontend.webdav.servlet.WebDavServletComponent;
import org.cryptomator.frontend.webdav.servlet.WebDavServletModule;

@Singleton
class WebDavServletFactory {

	private final WebDavServerComponent component;

	@Inject
	public WebDavServletFactory(WebDavServerComponent component) {
		this.component = component;
	}

	/**
	 * Creates a new Jetty ServletContextHandler, that can be be added to a servletCollection as follows:
	 * 
	 * <pre>
	 * ServletContextHandler context = factory.create(...);
	 * servletCollection.addHandler(context);
	 * servletCollection.mapContexts();
	 * </pre>
	 * 
	 * @param rootPath The location within a filesystem that shall be served via WebDAV.
	 * @param contextPath The servlet's context path.
	 * @return A new WebDAV servlet component.
	 */
	public WebDavServletComponent create(Path rootPath, String contextPath) {
		WebDavServletModule webDavServletModule = new WebDavServletModule(rootPath, contextPath);
		return component.newWebDavServletComponent(webDavServletModule);
	}

}
