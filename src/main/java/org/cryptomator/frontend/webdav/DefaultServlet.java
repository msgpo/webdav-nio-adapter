/*******************************************************************************
 * Copyright (c) 2016 Sebastian Stenzel and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the accompanying LICENSE.txt.
 *
 * Contributors:
 *     Sebastian Stenzel - initial API and implementation
 *******************************************************************************/
package org.cryptomator.frontend.webdav;

import java.io.IOException;
import java.util.EnumSet;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.DispatcherType;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cryptomator.frontend.webdav.servlet.LoopbackFilter;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

@Singleton
class DefaultServlet extends HttpServlet {

	private static final String ROOT_PATH = "/";
	private static final String WILDCARD = "/*";

	@Inject
	public DefaultServlet() {
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.service(req, resp);
	}

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.addHeader("DAV", "1, 2");
		resp.addHeader("MS-Author-Via", "DAV");
		resp.addHeader("Allow", "OPTIONS, GET, HEAD");
		resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}

	public ServletContextHandler createServletContextHandler() {
		final ServletContextHandler servletContext = new ServletContextHandler(null, ROOT_PATH, ServletContextHandler.NO_SESSIONS);
		final ServletHolder servletHolder = new ServletHolder(ROOT_PATH, this);
		servletContext.addServlet(servletHolder, ROOT_PATH);
		servletContext.addFilter(LoopbackFilter.class, WILDCARD, EnumSet.of(DispatcherType.REQUEST));
		return servletContext;
	}

}
