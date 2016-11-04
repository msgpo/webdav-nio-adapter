/*******************************************************************************
 * Copyright (c) 2016 Sebastian Stenzel and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the accompanying LICENSE.txt.
 *
 * Contributors:
 *     Sebastian Stenzel - initial API and implementation
 *******************************************************************************/
package org.cryptomator.frontend.webdav.servlet;

import org.eclipse.jetty.servlet.ServletContextHandler;

import dagger.Subcomponent;

@PerServlet
@Subcomponent(modules = {WebDavServletModule.class})
public interface WebDavServletComponent {

	ServletContextHandler servletContext();

}