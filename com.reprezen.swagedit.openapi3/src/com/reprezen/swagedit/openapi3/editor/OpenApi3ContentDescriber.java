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
package com.reprezen.swagedit.openapi3.editor;

import java.util.regex.Pattern;

import com.reprezen.swagedit.core.editor.TextContentDescriber;

public class OpenApi3ContentDescriber extends TextContentDescriber {
	
	public static final String CONTENT_TYPE_ID = "com.reprezen.swagedit.contenttype.openapi3.yaml";
	private final Pattern openApiV3Regex = Pattern.compile(".*openapi:\\s+([\"']?)3\\.0\\..+\\1.+", Pattern.DOTALL);

    @Override
    protected boolean isSupported(String content) {
        // should support arbitrary patch versions, e.g. `openapi: "3.0.0-RC0"`
        return openApiV3Regex.matcher(content).matches();
    }

}
