/*******************************************************************************
 * Copyright (c) 2016 ModelSolv, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    ModelSolv, Inc. - initial API and implementation and/or initial documentation
 *******************************************************************************/
package com.reprezen.swagedit.openapi3.hyperlinks;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.text.IRegion;

import com.fasterxml.jackson.core.JsonPointer;
import com.reprezen.swagedit.core.editor.JsonDocument;
import com.reprezen.swagedit.core.hyperlinks.JsonFileHyperlink;
import com.reprezen.swagedit.openapi3.editor.OpenApi3Document;

public class OpenApi3FileHyperlink extends JsonFileHyperlink {

	public OpenApi3FileHyperlink(IRegion linkRegion, String label, IFile file, JsonPointer pointer) {
		super(linkRegion, label, file, pointer);
	}

	@Override
	protected JsonDocument createDocument() {
		return new OpenApi3Document();
	}
	
}