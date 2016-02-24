package com.reprezen.swagedit.tests;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static com.reprezen.swagedit.templates.SwaggerContextType.getContextType;

import org.junit.Test;

import com.reprezen.swagedit.templates.SwaggerContextType.ParameterContextType;
import com.reprezen.swagedit.templates.SwaggerContextType.ParametersContextType;
import com.reprezen.swagedit.templates.SwaggerContextType.PathItemContextType;
import com.reprezen.swagedit.templates.SwaggerContextType.ResponsesContextType;
import com.reprezen.swagedit.templates.SwaggerContextType.SchemaContextType;
import com.reprezen.swagedit.templates.SwaggerContextType.SecurityDefContextType;;

public class CodeTemplateContextTest {

	@Test
	public void testPathItem() throws Exception {
		assertThat(getContextType(":paths:/petstore"), equalTo(PathItemContextType.CONTEXT_ID));
		assertThat(getContextType(":paths:/pet-store"), equalTo(PathItemContextType.CONTEXT_ID));
		assertThat(getContextType(":paths:/pets/{id}"), equalTo(PathItemContextType.CONTEXT_ID));
		assertThat(getContextType(":paths:/pets/{pet-id}"), equalTo(PathItemContextType.CONTEXT_ID));
		assertThat(getContextType(":paths:/my-pets/{pet-id}"), equalTo(PathItemContextType.CONTEXT_ID));
		assertThat(getContextType(":paths:/my-pets/v1/{pet-id}"), equalTo(PathItemContextType.CONTEXT_ID));
		assertThat(getContextType(":paths:/pets"), equalTo(PathItemContextType.CONTEXT_ID));

		// tests for #Templates defined in the Path context show in other
		// contexts
		assertThat(getContextType(":paths:"), not(equalTo(PathItemContextType.CONTEXT_ID)));
		assertThat(getContextType(":paths:/pets:get"), not(equalTo(PathItemContextType.CONTEXT_ID)));
		assertThat(getContextType(":paths:/pets:get:responses"), not(equalTo(PathItemContextType.CONTEXT_ID)));
	}

	@Test
	public void testSecurityDef() throws Exception {
		assertThat(getContextType(":securityDefinitions"), equalTo(SecurityDefContextType.CONTEXT_ID));
	}

	@Test
	public void testResponses() throws Exception {
		assertThat(getContextType(":responses"), equalTo(ResponsesContextType.CONTEXT_ID));
		assertThat(getContextType(":paths:/resource:get:responses"), equalTo(ResponsesContextType.CONTEXT_ID));
	}

	@Test
	public void testParameter() throws Exception {
		assertThat(getContextType(":parameters:skipParam:"), equalTo(ParameterContextType.CONTEXT_ID));
	}

	@Test
	public void testParametersList() throws Exception {
		assertThat(getContextType(":paths:/taxFilings/{id}:get:parameters:@0:"),
				equalTo(ParametersContextType.CONTEXT_ID));
		assertThat(getContextType(":paths:/resource:parameters:"), equalTo(ParametersContextType.CONTEXT_ID));
		assertThat(getContextType(":paths:/taxFilings/{id}:get:parameters"), equalTo(ParametersContextType.CONTEXT_ID));
	}

	@Test
	public void testSchema() throws Exception {
		assertThat(getContextType(":definitions:Pet:"), equalTo(SchemaContextType.CONTEXT_ID));
		assertThat(getContextType(":paths:/pets/{id}:delete:responses:default:schema:"),
				equalTo(SchemaContextType.CONTEXT_ID));
		assertThat(getContextType(":paths:/pets/{id}:get:responses:200:schema:"),
				equalTo(SchemaContextType.CONTEXT_ID));
		assertThat(getContextType(":paths:/pets:post:parameters:@0:schema:"), equalTo(SchemaContextType.CONTEXT_ID));
		assertThat(getContextType(":paths:/pets:post:parameters:@0:schema:properties:name"),
				equalTo(SchemaContextType.CONTEXT_ID));
		assertThat(getContextType(":paths:/pets:post:parameters:@0:schema:items"),
				equalTo(SchemaContextType.CONTEXT_ID));
		assertThat(getContextType(":definitions:TaxFilingObject:additionalProperties"),
				equalTo(SchemaContextType.CONTEXT_ID));
	}

}
