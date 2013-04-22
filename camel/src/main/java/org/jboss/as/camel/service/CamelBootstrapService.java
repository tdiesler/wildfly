/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.as.camel.service;

import static org.jboss.as.camel.CamelLogger.LOGGER;

import org.jboss.as.controller.ServiceVerificationHandler;
import org.jboss.modules.ModuleIdentifier;
import org.jboss.msc.service.AbstractService;
import org.jboss.msc.service.ServiceBuilder;
import org.jboss.msc.service.ServiceController;
import org.jboss.msc.service.ServiceName;
import org.jboss.msc.service.ServiceTarget;
import org.jboss.msc.service.StartContext;
import org.jboss.msc.service.StartException;
import org.jboss.msc.value.InjectedValue;
import org.jboss.osgi.framework.Services;
import org.osgi.framework.BundleContext;

/**
 * Service responsible for creating and managing the life-cycle of the Camel subsystem.
 *
 * @author Thomas.Diesler@jboss.com
 * @since 19-Apr-2013
 */
public class CamelBootstrapService extends AbstractService<Void> {

    public static final ModuleIdentifier CAMEL_MODULE_IDENTIFIER = ModuleIdentifier.create("org.apache.camel");
    public static final ServiceName CAMEL_NAME = ServiceName.JBOSS.append("as", "camel");

    private final InjectedValue<BundleContext> injectedSystemContext = new InjectedValue<BundleContext>();

    public static ServiceController<Void> addService(ServiceTarget serviceTarget, ServiceVerificationHandler verificationHandler) {
        CamelBootstrapService service = new CamelBootstrapService();
        ServiceBuilder<Void> builder = serviceTarget.addService(CAMEL_NAME, service);
        builder.addDependency(Services.FRAMEWORK_ACTIVE, BundleContext.class, service.injectedSystemContext);
        builder.addListener(verificationHandler);
        return builder.install();
    }

    // Hide ctor
    private CamelBootstrapService() {
    }

    @Override
    public void start(StartContext startContext) throws StartException {
        LOGGER.infoActivatingSubsystem();
    }
}
