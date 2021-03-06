/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2019, Red Hat, Inc., and individual contributors
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

package org.wildfly.clustering.ee.immutable;

import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

import org.wildfly.clustering.ee.Immutability;

/**
 * Test for immutability using object identity.
 * @author Paul Ferraro
 */
public class IdentityImmutability implements Immutability {

    private final Set<Object> immutableObjects;

    public IdentityImmutability(Collection<Object> objects) {
        this.immutableObjects = !objects.isEmpty() ? Collections.newSetFromMap(new IdentityHashMap<>(objects.size())) : Collections.emptySet();
        for (Object object : objects) {
            this.immutableObjects.add(object);
        }
    }

    @Override
    public boolean test(Object object) {
        return (object == null) || this.immutableObjects.contains(object);
    }
}
