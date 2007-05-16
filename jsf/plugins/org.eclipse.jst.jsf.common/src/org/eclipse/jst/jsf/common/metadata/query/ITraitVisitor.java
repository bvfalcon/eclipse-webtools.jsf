/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.common.metadata.query;

import org.eclipse.jst.jsf.common.metadata.Trait;

/**
 * Visitor interface for Traits
 * NOT to implemented by clients directly.   Clients should subclass AbstractTraitVisitor instead.
 */
public interface ITraitVisitor extends IMetaDataVisitor {
	/**
	 * Visit the Trait.
	 * Implementer cannot assume ordering of trait visiting. 
	 * @param trait - must not be null
 	 */
	public void visit(final Trait trait);
}
